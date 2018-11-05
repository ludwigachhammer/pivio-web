package io.pivio.view.app.exporter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.pivio.view.app.exporter.model.BusinessApplicationModel;
import io.pivio.view.app.exporter.util.MySimpleClientHttpRequestFactory;
import io.pivio.view.app.exporter.util.NullHostnameVerifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;


@Service
public class ExporterService {
	
	private String iteraplanHost = "http://localhost";
	private String iteraplanPort = "1234";
	
	private String serverConfig = iteraplanHost+":"+iteraplanPort+"/iteraplan/api/element/InformationSystem";
	 
    public static final Logger logger = LoggerFactory.getLogger(ExporterService.class);
    
    public void makeHTTPPOSTRequest(BusinessApplicationModel ba) {
    	
    	String url = this.serverConfig;
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println("makeHTTPPOSTRequest to URL: "+url);
    	HttpMethod httpMethod = HttpMethod.POST;
    	
    	//TODO: Convert BusinessApplication in HttpEntity
    	String baString = ba.toFormattedIteraplanJsonString();
    	HttpEntity<String> entity = new HttpEntity<String>(baString);
    	
    	try {
    		ResponseEntity<String> response = add(url, httpMethod, entity);
    		String responseBody = response.getBody();
    		
    		System.out.println("Response: "+responseBody);
    		System.out.println("--------------------------------------------------------------------------------------");
    	}
        catch(Exception e) {
            System.out.println("Exception: "+e);
        }
    	finally {
    		
    	}
                              
    } 
    
    public ResponseEntity<String> add(String url, HttpMethod httpMethod, HttpEntity<String> entity) {
        return send(url, httpMethod, entity);
    }
    
    private ResponseEntity<String> send(String url, HttpMethod httpMethod, HttpEntity<String> entity) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(getCustomRequestFactory());
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));

        return restTemplate.exchange(url, httpMethod, entity, String.class);
    }
    
    private ClientHttpRequestFactory getCustomRequestFactory() {
        return new MySimpleClientHttpRequestFactory(new NullHostnameVerifier(), null);
    }    
    
    // ------------------- MASSDATA BusinessApplications in iteraplan  ------------------------------------------------
	//TODO

	 
	}
