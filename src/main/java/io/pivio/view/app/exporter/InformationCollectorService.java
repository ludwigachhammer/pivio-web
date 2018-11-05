package io.pivio.view.app.exporter;

import java.io.IOException;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.pivio.view.app.exporter.model.BusinessApplicationModel;

@Controller
public class InformationCollectorService {
	
	private final Logger log = LoggerFactory.getLogger(InformationCollectorService.class);
	
	//@Value("${elasticsearch.host}")
    private String esHost = "http://192.168.99.100";

    //@Value("${elasticsearch.port}")
    private String esPort = "9123";
    
    private String serverConfig = esHost+":"+esPort;
    
    private String query = "fields=short_name,id,description,name,owner,context,lastUpdate,lastUpload,type&sort=name:asc";
    private String matchAllQuery = "&query={\"query\":{\"match_all\":{}}}";
    
    @Autowired
    ExporterService exportController;
    
    private <T> ResponseEntity get(String url, Class<T> type) throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();
        log.debug("Asking for :" + url);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("", getHeaders()), type);
        log.debug("Response :" + response.getBody().toString());
        return response;
    }
    
    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
    
    public <T> ResponseEntity list(String path, Class<T> type) throws UnsupportedEncodingException {
        return get(path, type);
    }
	
    @RequestMapping(value = "/export", method = RequestMethod.GET)
	public String getBusinessApplications() throws IOException {
    	System.out.println("***************Endpoint Export accessed**************");
		String encodedQuery = URLEncoder.encode(matchAllQuery, "UTF-8");
		
        String path = "/document?"+encodedQuery;
        log.debug("SERVERCONFIG: "+serverConfig);
        String url = serverConfig+path;
        System.out.println("********************Server url: "+url+"*******************");
        RestTemplate restTemplate = new RestTemplate();
        ParameterizedTypeReference<List<BusinessApplicationModel>> typeRef = new ParameterizedTypeReference<List<BusinessApplicationModel>>() {
        };
        
        List<BusinessApplicationModel> result;
        try {
            ResponseEntity<List<BusinessApplicationModel>> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>("", getHeaders()), typeRef);
            result = response.getBody();
            System.out.println("******** result.get(0).toString() ******************"+result.get(0).toString());
            for(BusinessApplicationModel ba: result) {
            	String b = ba.toFormattedIteraplanJsonString();
            	System.out.println(b);
            	exportController.makeHTTPPOSTRequest(ba);
            }
        } catch (Exception e) {
            log.error("Pivio Server at {} does not respond (Exception=\n{}\n).", url, e.getMessage());
            throw new IOException("Unable to connect to " + url + ".");
        }
        return "redirect:/app/overview";
    }

}
