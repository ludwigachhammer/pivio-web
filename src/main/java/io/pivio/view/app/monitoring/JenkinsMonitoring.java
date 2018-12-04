package io.pivio.view.app.monitoring;

import org.springframework.stereotype.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import java.util.Base64;

import org.joda.time.DateTime;
import org.ocpsoft.prettytime.PrettyTime;

public class JenkinsMonitoring {
	
	private String BuildNumber;
	private String Duration;
	private String EstimatedDuration;
	private String Result;
	private String Timestamp;
	private String Url;
	
	public JenkinsMonitoring() {
		this.BuildNumber = "";
		this.Duration = "";
		this.EstimatedDuration = "";
		this.Result = "";
		this.Timestamp = "";
		this.Url = "";
	}
	
	public JenkinsMonitoring(String name) {
		System.out.println("******** getJenkinsMonitoringData ******************");
		String jenkinsUrl = "http://131.159.30.173:8081/job/"+name+"/lastBuild/api/json";
		JSONObject jenkinsMonitoringData;
		try {
			jenkinsMonitoringData = getInformation(jenkinsUrl);
			System.out.println("JSONObject: "+jenkinsMonitoringData.toString());
			this.BuildNumber = String.valueOf(jenkinsMonitoringData.getInt("number"));
			this.Duration = String.valueOf(jenkinsMonitoringData.getInt("duration"));
			this.EstimatedDuration = String.valueOf(jenkinsMonitoringData.getInt("estimatedDuration"));
			this.Result = jenkinsMonitoringData.getString("result");
			this.Timestamp = String.valueOf(jenkinsMonitoringData.getInt("timestamp"));
			this.Url = jenkinsMonitoringData.getString("url");
		}
		catch(Exception e) {
			System.out.println(e);
			this.BuildNumber = "Error";
			this.Duration = "Error";
			this.EstimatedDuration = "Error";
			this.Result = "Error";
			this.Timestamp = "Error";
			this.Url = "Error";
		}
	}
	
	
	public String getBuildNumber() {
		return this.BuildNumber;
	}
	
	public String getDuration() {
		return this.Duration;
	}
	
	public String getEstimatedDuration() {
		return this.EstimatedDuration;
	}
	
	public String getResult() {
		return this.Result;
	}
	
	public String getTimestamp() {
		//return new PrettyTime().format(new DateTime(this.Timestamp).toDate());
		return this.Timestamp;
	}
	
	public String getUrl() {
		return this.Url;
	}
	
	
	public JSONObject getInformation(String url) throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		String userpass = "admin" + ":" + "admin";
	    System.out.println("userpass: "+userpass);
	    String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
	    System.out.println(basicAuth);
		// add request header
		request.addHeader("Authorization", basicAuth);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		System.out.println(responseString);
		
		JSONObject myResponse = new JSONObject(responseString);
	    return myResponse;
	}
	
	public String getIcon(String status) {
    	if(status.equals("Error")) {
    		return "circle red";
    	}
    	else {
    		return "circle green";
    	}
    }
	
	public String getStatusIcon(int x) {
		String status;
		switch (x) {
	        case 1:  status = getBuildNumber();
	        		 return getIcon(status);
	        case 2:  status = getDuration();
   		 			 return getIcon(status);
	        case 3:  status = getEstimatedDuration();
   		 			 return getIcon(status);
	        case 4:  status = getResult();
   		 			 return getIcon(status);
	        case 5:  status = getTimestamp();
   		 			 return getIcon(status);
	        case 6:  status = getUrl();
   		 			 return getIcon(status);
	        default: status = "circle red";
   		 			 return status;
	    }
    }
}
