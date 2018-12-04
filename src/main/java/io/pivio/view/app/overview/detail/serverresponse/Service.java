package io.pivio.view.app.overview.detail.serverresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Service {

    public List<Provides> provides = new ArrayList<>();
    public DependsOn depends_on = new DependsOn();
    public List<String> buildpacks = new ArrayList<>();

    public String toString() {
    	
    	String service = "";
    	String providesString = "";
    	String buildpacksString = "";
    	
    	if (provides != null && provides.isEmpty()){
    		providesString = "\"provides\":[]";
    	}else {
    		String str = "\"provides\":[";
        	for (Provides temp : provides) {
    			str = str + temp.toString() + ",";
    		}
        	str = str.substring(0, (str.length())-1);
        	
        	providesString =  str + "]";
    	}
    	
    	if (buildpacks != null && buildpacks.isEmpty()){
    		buildpacksString = "\"buildpacks\":[]";
    	}else {
    		String str = "\"buildpacks\":[";
        	for (String temp : buildpacks) {
    			str = str + "\"" + temp + "\"" + ",";
    		}
        	str = str.substring(0, (str.length())-1);
        	
        	buildpacksString = str + "]";
    	}
    	
    	service = "{"+ providesString + "," + buildpacksString +"}";
    	System.out.println("******Services*******");
    	System.out.println(service);
    	System.out.println("*********************");
    	return service;
    }
    
}
