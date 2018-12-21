package io.pivio.view.app.overview.detail.serverresponse;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.joda.time.DateTime;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import io.pivio.view.app.monitoring.*;
import org.json.JSONObject; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class Document {

    public String owner;
    public String data_format_version;
    public String description;
    public String type;
    public String vcsroot;
    public String name;
    public String short_name;
    public String id;
    public String contact;
    public String created;
    public String lastUpload;
    public String lastUpdate;
    public String status;
    public String product_context;
    public List<String> tags = new ArrayList<>();
    public Map<String, String> links = new HashMap<>();
    public Map<String, String> other_attributes = new HashMap<>();
    
    //Links
    public String github;
    public String jira;
    public String jenkins;
    public String cloudfoundry;
    public String iteraplan;
    
    //
    public String domain;
    public String subdomain;
    public String product;
    public String url;
    //TODO lastdeployment
    
    public Runtime runtime = new Runtime();
    public List<SoftwareDependency> software_dependencies = new ArrayList<>();
    public Service service = new Service();
    public Context context = new Context();

    public GithubMonitoring githubMonitoring;
    
    public JenkinsMonitoring jenkinsMonitoring;
    
    public GovernanceMonitoring governanceMonitoring;
    
    public JiraMonitoring jiraMonitoring;
    
	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}
	@JsonProperty("tags")
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
    
	@JsonAnyGetter
	public Map<String, String> any() {
		return other_attributes;
	}

	@JsonAnySetter
	public void set(String name, String value) {
		this.other_attributes.put(name, value);
	}
	
	
    public String formatAdditionalAttributes(Map<String, String> other_attributes_arg) {
		
		if(other_attributes_arg.isEmpty()) {
			return ", other_attributes = EMPTY";
		}else {
			String formattedAdditionalAttributesString = ",";
			for (Map.Entry<String, String> entry : other_attributes_arg.entrySet()) {
				formattedAdditionalAttributesString = formattedAdditionalAttributesString+"\""+entry.getKey()+"\":\""+entry.getValue()+"\",";
			}
			//remove last comma:
			formattedAdditionalAttributesString = formattedAdditionalAttributesString.substring(0, formattedAdditionalAttributesString.length()-1);
			System.out.println(formattedAdditionalAttributesString);
			return formattedAdditionalAttributesString;
		}
	}

    @Override
    public String toString() {
        return "{" +
                "\"owner\":\"" + owner + "\"" +
                ",\"description\":\"" + description + "\"" +
                ",\"type\":\"" + type + "\"" +
                ",\"name\":\"" + name + "\"" +
                ",\"short_name\":\"" + short_name + "\"" +
                ",\"id\":\"" + id + "\"" +
                ",\"domain\":\"" + domain + "\"" +
                ",\"subdomain\":\"" + subdomain + "\"" +
                ",\"product\":\"" + product + "\"" +
                ",\"created\":\"" + created + "\"" +
                ",\"lastUpload\":\"" + lastUpload + "\"" +
                ",\"lastUpdate\":\"" + lastUpdate + "\"" +
                ",\"status\":\"" + status + "\"" +
                ",\"url\":\"" + url + "\"" +
                ",\"runtime\":" + runtime.toString() +
                ",\"service\":" + service.toString() +
                //Nico
                formatAdditionalAttributes(other_attributes)+
                ",\"github\":\"" + github + "\"" +
                ",\"jira\":\"" + jira + "\"" +
                ",\"jenkins\":\"" + jenkins + "\"" +
                ",\"cloudfoundry\":\"" + cloudfoundry + "\"" +
                ",\"iteraplan\":\"" + iteraplan + "\"" +
                '}';
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getPrettyUploadDate() {
        return new PrettyTime().format(new DateTime(lastUpload).toDate());
    }

    public String getPrettyUpdateDate() {
        return new PrettyTime().format(new DateTime(lastUpdate).toDate());
    }

    public boolean ofTypeService() {
        return type.equalsIgnoreCase("service");
    }
    
    public String getGithubMonitoringData() {
    	//String value = other_attributes.get("github");
    	System.out.println("*****************************************************************");
    	String value = this.github;
    	System.out.println("Github-link: "+value);
    	githubMonitoring = new GithubMonitoring();
    	System.out.println("GithubMonitoring: "+githubMonitoring);
    	if(value != null) {
    		governanceMonitoring = new GovernanceMonitoring(value, runtime.getInstances(), getUrl(), service.toString());
    		return githubMonitoring.getMonitoringData(value);
    	}else {
    		governanceMonitoring = new GovernanceMonitoring();
    		return "No github information could be retrieved";
    	}
    }
    
    public String getCommitActivity() {
		System.out.println("******** Github Commit activity ******************");
		String repo = github.substring(url.lastIndexOf('/')+1, url.length());
		String tmpUrl = github.substring(0, url.lastIndexOf('/'));
		String user = github.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length());
		String githubapi = "https://api.github.com/";
		String path = "/stats/contributors";
		String composedUrl = githubapi+user+"/"+repo+"/"+path;
		String response = "";		
		try {
			response = getInfo(composedUrl);
		}catch(Exception e) {
			System.out.println("Server does not respond:" + e.getMessage());
			return "Error retrieving commit-activity";
		}
		return response;
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
    
    public String getJiraMonitoringData() {
    	System.out.println("*****************************************************************");
    	String value = this.jira;
    	//String value = other_attributes.get("jira");
    	String key = other_attributes.get("key");
    	System.out.println("Jira-link: "+value);
    	if(value != null) {
    		jiraMonitoring = new JiraMonitoring();
    		return jiraMonitoring.getMonitoringData(value, key);
    	}else {
    		return "No jira information could be retrieved";
    	}
    }
    
    public String getStatusIcon() {
    	if(status.equals("running")) {
    		return "circle green";
    	}
    	if(status.equals("stopped") || status.equals("crashed")) {
    		return "circle red";
    	}
    	else {
    		return "circle yellow";
    	}
    }

}
