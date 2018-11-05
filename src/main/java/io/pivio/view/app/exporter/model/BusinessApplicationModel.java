package io.pivio.view.app.exporter.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.pivio.view.app.overview.detail.serverresponse.Runtime;
import io.pivio.view.app.overview.detail.serverresponse.*;

import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessApplicationModel {
	
	//Pivio
	private String id;
	private String name;
	private String short_name;
	private String type; //enum
	private String owner;
	private String description;
	//Jira
	private String businessDomain;
	private String critial; //enum
	private String jiraURL;
	//Cloud
	private String status; //enum
	private String technicalResponsible;
	private String jenkinsURL;
	private String deploymentDate;
	private String lastChanges;
	private String deploymentUser;
	private String platform; //enum
	private String ipAddress;
	private String port;
	//Application Performance metrics
	private String http_requests;
	private String http_request_duration;
	//pivio
	public List<String> tags = new ArrayList<>();
    public Runtime runtime = new Runtime();
    public List<SoftwareDependency> software_dependencies = new ArrayList<>();
    public Service service = new Service();
    public Context context = new Context();
    //Additional attributes
	private Map<String, String> additional_attributes = new HashMap<String, String>(); //
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBusinessDomain() {
		return businessDomain;
	}

	public void setBusinessDomain(String businessDomain) {
		this.businessDomain = businessDomain;
	}

	public String getCritial() {
		return critial;
	}

	public void setCritial(String critial) {
		this.critial = critial;
	}

	public String getJiraURL() {
		return jiraURL;
	}

	public void setJiraURL(String jiraURL) {
		this.jiraURL = jiraURL;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTechnicalResponsible() {
		return technicalResponsible;
	}

	public void setTechnicalResponsible(String technicalResponsible) {
		this.technicalResponsible = technicalResponsible;
	}

	public String getJenkinsURL() {
		return jenkinsURL;
	}

	public void setJenkinsURL(String jenkinsURL) {
		this.jenkinsURL = jenkinsURL;
	}

	public String getDeploymentDate() {
		return deploymentDate;
	}

	public void setDeploymentDate(String deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

	public String getLastChanges() {
		return lastChanges;
	}

	public void setLastChanges(String lastChanges) {
		this.lastChanges = lastChanges;
	}

	public String getDeploymentUser() {
		return deploymentUser;
	}

	public void setDeploymentUser(String deploymentUser) {
		this.deploymentUser = deploymentUser;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHttp_requests() {
		return http_requests;
	}

	public void setHttp_requests(String http_requests) {
		this.http_requests = http_requests;
	}

	public String getHttp_request_duration() {
		return http_request_duration;
	}

	public void setHttp_request_duration(String http_request_duration) {
		this.http_request_duration = http_request_duration;
	}
	
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
		return additional_attributes;
	}

	@JsonAnySetter
	public void set(String name, String value) {
		this.additional_attributes.put(name, value);
	}
	
	public boolean hasUnknowProperties() {
		return !additional_attributes.isEmpty();
	}
	
	public String formatAdditionalAttributes(Map<String, String> additionalAttributesMap) {
		
		if(additionalAttributesMap.isEmpty()) {
			return "EMPTY";
		}else {
			String formattedAdditionalAttributesString = ",";
			for (Map.Entry<String, String> entry : additionalAttributesMap.entrySet()) {
				formattedAdditionalAttributesString = formattedAdditionalAttributesString+"\""+entry.getKey()+"\":\""+entry.getValue()+"\",";
			}
			//remove last comma:
			formattedAdditionalAttributesString = formattedAdditionalAttributesString.substring(0, formattedAdditionalAttributesString.length()-1);
			System.out.println(formattedAdditionalAttributesString);
			return formattedAdditionalAttributesString;
		}
	}
	
	public String getLayer() {
		Random rand = new Random();
		int  n = rand.nextInt(2);
		
		if(n==0) {
			return "Inner";
		}else{
			return "Outer";
		}
	}
	
	public String toFormattedIteraplanJsonString() {
		
		return "{ "
					+"\"External ID\": [\""
					+this.id+"\"], "
					+" \"name\": [\""
					+this.name+"\"], "
					//+" \"short_name\": [\""
					//+this.short_name+"\"], "
					//+" \"type\": [\""
					//+this.type+"\"], "
					//+ "\"Owner\": [\""
					//+this.owner+"\"],"
					+" \"description\": [\""
					+this.description+"\"], "
					+" \"Pivio link\": [\"http://localhost:8080/app/overview/"
					+this.id+"\"], "
					+" \"Cloud\": [\"ADP\"], "
					+" \"Layer\": [\""+getLayer()+"\"], "
					+" \"Status\": [\""
					+this.status+"\"] "
					/*
					+" \"business-domain\": [\""
					+this.businessDomain+"\"], "
					+" \"critial\": [\""
					+this.critial+"\"], "
					+" \"jiraURL\": [\""
					+this.jiraURL+"\"], "
					+" \"status\": [\""
					+this.status+"\"], "
					+" \"technical-responsible\": [\""
					+this.technicalResponsible+"\"], "
					+" \"jenkinsURL\": [\""
					+this.jenkinsURL+"\"], "
					+" \"deployment-date\": [\""
					+this.deploymentDate+"\"], "
					+" \"last-changes\": [\""
					+this.lastChanges+"\"], "
					+" \"deployment-user\": [\""
					+this.deploymentUser+"\"], "
					+" \"platform\": [\""
					+this.platform+"\"], "
					+" \"ip-address\": [\""
					+this.ipAddress+"\"], "
					+" \"port\": [\""
					+this.port+"\"], "		
					//APM
					+" \"http_requests\": [\""
					+this.http_requests+"\"], "
					+" \"http_request_duration\": [\""
					+this.http_requests+"\"]"
					//TODO: implement to string for types
					Pivio
			   		+" \"tags\": [\""
					+this.tags+"\"], "
					+" \"runtime\": [\""
					+this.runtime+"\"], "
					+" \"software_dependencies\": [\""
					+this.software_dependencies+"\"], "
					+" \"service\": [\""
					+this.service+"\"], "
					+" \"context\": [\""
					+this.context+"\"],  */
					//+formatAdditionalAttributes(this.additional_attributes)
				+"}";
	}

}
