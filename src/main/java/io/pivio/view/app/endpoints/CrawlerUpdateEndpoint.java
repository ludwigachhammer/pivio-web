package io.pivio.view.app.endpoints;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser.Feature;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import io.pivio.view.app.overview.detail.serverresponse.Document;
import io.pivio.view.PivioServerConnector;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.pivio.view.app.exporter.util.MySimpleClientHttpRequestFactory;
import io.pivio.view.app.exporter.util.NullHostnameVerifier;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONObject;


@RestController
@RequestMapping("/update")
public class CrawlerUpdateEndpoint{
	
	@Autowired
    PivioServerConnector pivioServerConnector;
	
	@RequestMapping(value = "/microservice", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> lastUpdate(@RequestBody String str){
		try {
			System.out.println("******* Updating document ***************************");
			Document doc = null;
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
			doc = new ObjectMapper().readValue(str, Document.class);
			System.out.println("Document: "+str);
			System.out.println("*****************************************************");
			//Get existing document
			Document docToBeUpdated = pivioServerConnector.getDocumentById(doc.id);
			
			docToBeUpdated.id = doc.id;
			docToBeUpdated.status = doc.status;
			docToBeUpdated.url = doc.url;
			docToBeUpdated.runtime = doc.runtime;
			docToBeUpdated.lastUpdate = doc.lastUpdate;
			
			//Update document
			String url = "http://192.168.99.100:9123/document";
			HttpMethod httpMethod = HttpMethod.POST;
	    	String docString = docToBeUpdated.toString();
	    	docString.replaceAll("\"", "\\\"");
	    	System.out.println("docToBeUpdated: "+docString);
	    	
	    	HttpClient httpClient = HttpClientBuilder.create().build();
	    	HttpPost request = new HttpPost(url);
	        StringEntity params = new StringEntity(docString);
	        request.addHeader("content-type", "application/json;charset=UTF-8");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	    	System.out.println("*****************************************************");
		} catch (JsonParseException e) {
			System.out.println("JsonParseException");
		} catch (JsonMappingException e) {
			System.out.println("JsonMappingException");
		} catch (IOException e) {
			System.out.println("IOException");
		}catch(Exception e) {
			System.out.println("Exception");
		}
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}    
	
}