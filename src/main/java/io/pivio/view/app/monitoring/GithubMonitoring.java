package io.pivio.view.app.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.springframework.stereotype.*;
import org.apache.commons.lang3.StringUtils;

@Component
public class GithubMonitoring {
	
	public String getContributors(String url) {
		String path = "/stats/contributors";
		String response;
		int numberOfContributors;
		try {
			response = getInfo(url+path);
			numberOfContributors = StringUtils.countMatches(response, "author");
		}catch(Exception e) {
			System.out.println("Server does not respond:" + e.getMessage());
			return "Error retrieving contributors";
		}
		return String.valueOf(numberOfContributors);
	}
	
	public String getMonitoringData(String url) {
		System.out.println("******** getGithubMonitoringData ******************");
		String repo = url.substring(url.lastIndexOf('/')+1, url.length());
		String tmpUrl = url.substring(0, url.lastIndexOf('/'));
		String user = url.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length());
		String githubapi = "https://api.github.com/";
		String composedUrl = githubapi+"repos/"+user+"/"+repo;
		return getContributors(composedUrl);
	}
	
	public static String getInfo(String url) throws Exception {
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
	     return response.toString();
	}
}
