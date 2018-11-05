package io.pivio.view.app.endpoints;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import io.pivio.view.app.endpoints.Date;
import java.io.PrintWriter;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@RestController
@RequestMapping("/endpoint")
public class LastUpdateEndpoint{
	
	@RequestMapping(value = "/lastUpdateOfCrawler", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> lastUpdate(@RequestBody Date date){
		//System.out.println("New date "+date.getDate());
		try {
			PrintWriter writer = new PrintWriter("last-update.yaml", "UTF-8");
			writer.println(date.getDate());
			writer.close();
		}catch(Exception e) {
			
		}
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}    
	
	@RequestMapping(value = "/lastUpdate", method = RequestMethod.GET)
	public String lastUpdate(){
    	String text = "";
    	String line = null;
		try {
			FileReader fileReader = new FileReader("last-update.yaml");
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                text = text+ line;
            }  
	        bufferedReader.close();
		}
		catch(Exception e) {
			return "";
		}
        return "last update: "+text;
	}  
	
	
}