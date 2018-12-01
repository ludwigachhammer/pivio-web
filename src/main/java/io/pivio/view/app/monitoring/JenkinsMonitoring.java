package io.pivio.view.app.monitoring;

import org.springframework.stereotype.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import org.json.JSONObject; 

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
		String jenkinsUrl = "http://localhost:8081/job/"+name+"/lastBuild/api/json";
		JSONObject jenkinsMonitoringData;
		try {
			jenkinsMonitoringData = getLastJobInformation(jenkinsUrl);
			this.BuildNumber = jenkinsMonitoringData.getString("number");
			this.Duration = jenkinsMonitoringData.getString("duration");
			this.EstimatedDuration = jenkinsMonitoringData.getString("estimatedDuration");
			this.Result = jenkinsMonitoringData.getString("result");
			this.Timestamp = jenkinsMonitoringData.getString("timestamp");
			this.Url = jenkinsMonitoringData.getString("url");
		}
		catch(Exception e) {
			System.out.println("******** getJenkinsMonitoringData ******************");
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
}
