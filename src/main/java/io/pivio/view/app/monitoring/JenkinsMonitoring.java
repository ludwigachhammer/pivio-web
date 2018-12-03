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
		String jenkinsUrl = "http://localhost:8081/job/"+name+"/lastBuild/api/json";
		JSONObject jenkinsMonitoringData;
		try {
			jenkinsMonitoringData = getInformation(jenkinsUrl);
			System.out.println("JSONObject: "+jenkinsMonitoringData.toString());
			this.BuildNumber = jenkinsMonitoringData.getString("number");
			this.Duration = jenkinsMonitoringData.getString("duration");
			this.EstimatedDuration = jenkinsMonitoringData.getString("estimatedDuration");
			this.Result = jenkinsMonitoringData.getString("result");
			this.Timestamp = jenkinsMonitoringData.getString("timestamp");
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
	
	public JSONObject getLastJobInformation(String url) throws Exception {
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     try {
			con.setRequestMethod("GET");
			System.setProperty("http.agent", "Chrome");
			con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	     int responseCode = con.getResponseCode();
	     System.out.println("Sending 'GET' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     System.out.println(response.toString());
	     JSONObject myResponse = new JSONObject(response.toString());
	     return myResponse;
	}
	
	public JSONObject getInformation(String url) throws Exception {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		//request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = client.execute(request);

		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());
		//System.out.println("myResponse: " + response.getEntity().getContent());
		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println("myResponse: " + line);
		JSONObject myResponse = new JSONObject();
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
