package io.pivio.view.app.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject; 
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.*;
import java.util.Base64;

public class JiraMonitoring {
	
	private int totalIssues;
	private int openIssues;
	public Map<String, String> components = new HashMap<>();
	public Map<String, String> issues = new HashMap<>();
	
	@Value("${jira.username}")
	private String username;
	@Value("${jira.password}")
	private String password;
	
	public JiraMonitoring() {
		System.out.println("******** JiraMonitoringData ******************");
		this.totalIssues = 0;
		this.openIssues = 0;
	}
	
	public int getTotalIssues() {
		return totalIssues;
	}

	public void setTotalIssues(int totalIssues) {
		this.totalIssues = totalIssues;
	}
	
	public int getOpenIssues() {
		return openIssues;
	}

	public void setOpenIssues(int openIssues) {
		this.openIssues = openIssues;
	}

	public void setComponents(String name, String value) {
		this.components.put(name, value);
	}
	
	public void setIssues(String name, String value) {
		this.issues.put(name, value);
	}
	
	public String getRequestInformation(String url) throws Exception {
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     String userpass = "nico" + ":" + "Nico123";
	     System.out.println("userpass: "+userpass);
	     String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
	     System.out.println(basicAuth);
	     con.setRequestProperty("Authorization", basicAuth);
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
	     return response.toString();
	}
	
	public String getMonitoringData(String url, String key) {
		System.out.println("KEY : " + key);
		String issuesUrl = "http://vmmatthes32.informatik.tu-muenchen.de:6000/rest/api/2/search?jql=project="+key;
		System.out.println(issuesUrl);
		int totalIssues = 0;
		int toDoIssues = 0;
		try {
			//Components
			JSONObject responseComponents = new JSONObject(getRequestInformation(url));
			JSONArray arr1 = responseComponents.getJSONArray("components");
			System.out.println(arr1);
			//TODO
			if(arr1.length() > 0){
				for (int i = 0; i < arr1.length(); i++) {
					setComponents("Component"+(i+1), arr1.getJSONObject(i).getString("name"));
				}
			}
			//Issues
			JSONObject responseIssues = new JSONObject(getRequestInformation(issuesUrl));
			totalIssues = responseIssues.getInt("total");
			JSONArray issuesArray = responseIssues.getJSONArray("issues");
			//TODO
			if(issuesArray.length() > 0){
				for (int i = 0; i < issuesArray.length(); i++) {
					String name = issuesArray.getJSONObject(i).getString("key");
					JSONObject fields = issuesArray.getJSONObject(i).getJSONObject("fields");
					String status = fields.getJSONObject("status").getJSONObject("statusCategory").getString("name");
					System.out.println("Name of Issue: " +name);
					System.out.println("Status of Issue: " +status);
					System.out.println("toDoIssues: " +toDoIssues);
					setIssues(name, status);
					if(status.equals("To Do")) {
						toDoIssues = toDoIssues + 1;
					}
				}
			}
			setTotalIssues(totalIssues);
			setOpenIssues(toDoIssues);
			return "";
		}catch(Exception e) {
			System.out.println("Exception : " + e);
			return "Jira information could not be retrieved";
		}
	}
}
