package io.pivio.view.app.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.lang.reflect.Array;
import org.json.JSONObject; 
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.*;
import java.util.Base64;

public class GovernanceMonitoring {
	
	//12 factor App
	private String Codebase;
	private String Dependencies;
	private String Configuration;
	private String BackingServices;
	private String BuildReleaseRun;
	private String Processes;
	private String PortBinding;
	private String Concurrency;
	private String Disposability;
	private String DevProdParity;
	private String Logs;
	private String AdminProcesses;
	
	//Resilience pattern
	private String isHostedInAZDCloud;
	private String Redundancy;
	private String ZeroDowntimeDeployment;
	private String Retry;
	private String Isolation;
	private String Caching;
	private String Fallback;
	private String LooseCoupling;
	private String url;
	
	@Value("${github.username}")
	private String username;
	@Value("${github.password}")
	private String password;
	
	//TODO: Convert calls into Octokit object
	public GovernanceMonitoring() {
		System.out.println("******** GovernanceMonitoringData ******************");
		//12factor App
		this.Codebase = "";
		this.Dependencies= "";
		this.Configuration= "";
		this.BackingServices = "";
		this.BuildReleaseRun = "";
		this.Processes = "";
		this.PortBinding = "";
		this.Concurrency = "";
		this.Disposability = "";
		this.DevProdParity = "";
		this.Logs = "";
		this.AdminProcesses = "";
		//Resilience pattern
		this.isHostedInAZDCloud = "";
		this.Redundancy = "";
		this.ZeroDowntimeDeployment = "";
		this.Retry = "";
		this.Isolation = "";
		this.Caching = "";
		this.Fallback = "";
		this.LooseCoupling = "";
	}
	
	public GovernanceMonitoring(String url, String instances, String cloudUrl, String services) {
		System.out.println("******** GovernanceMonitoringData ******************");
		String governanceUrl = url;
		String repository = governanceUrl.substring(governanceUrl.lastIndexOf('/')+1, governanceUrl.length());
		String tmpUrl = governanceUrl.substring(0, governanceUrl.lastIndexOf('/'));
		String user = governanceUrl.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length());
		String searchUrl = user+'/'+repository;
		System.out.println(searchUrl);
		try {
			//12factor App
			setCodebase();
			setDependencies(searchUrl);
			setConfiguration(searchUrl);
			//TODO setBackingServices(searchUrl);
			setBuildReleaseRun(searchUrl);
			setProcesses();
			setPortBinding(searchUrl);
			setConcurrency();
			setDisposability();
			setDevProdParity(searchUrl);
			setLogs();
			setAdminProcesses();
			//Resilience pattern
			setIsHostedInAZDCloud(cloudUrl);
			setRedundancy(instances);
			setZeroDowntimeDeployment(instances);
			setRetry(searchUrl);
			setIsolation(services);
			setCaching(searchUrl);
			//setFallback();
			setLooseCoupling();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	//12 factor App
	public String getCodebase() {
		return Codebase;
	}
	public String getDependencies() {
		return Dependencies;
	}
	public String getConfiguration() {
		return Configuration;
	}
	public String getBackingServices() {
		return BackingServices;
	}
	public String getBuildReleaseRun() {
		return BuildReleaseRun;
	}
	public String getProcesses() {
		return Processes;
	}
	public String getPortBinding() {
		return PortBinding;
	}
	public String getConcurrency() {
		return Concurrency;
	}
	public String getDisposability() {
		return Disposability;
	}
	public String getDevProdParity() {
		return DevProdParity;
	}
	public String getLogs() {
		return Logs;
	}
	public String getAdminProcesses() {
		return AdminProcesses;
	}
	
	//Resilience Pattern
	public String getIsHostedInAZDCloud() {
		return isHostedInAZDCloud;
	}
	public String getRedundancy() {
		return Redundancy;
	}
	public String getZeroDowntimeDeployment() {
		return ZeroDowntimeDeployment;
	}
	public String getRetry() {
		return Retry;
	}
	public String getIsolation() {
		return Isolation;
	}
	public String getCaching() {
		return Caching;
	}
	public String getFallback() {
		return Fallback;
	}
	public String getLooseCoupling() {
		return LooseCoupling;
	}
	
	
	public String getRequestInformation(String url) throws Exception {
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     String userpass = "Nicocovi" + ":" + "nico0205";
	     System.out.println("userpass: "+userpass);
	     String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
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
	     //System.out.println(response.toString());
	     return response.toString();
	}
	
	public String getIcon(String status) {
		if(status.equals("Not implemented")) {
    		return "circle red";
    	}
    	if(status.equals("Yes")) {
    		return "circle green";
    	}
    	else {
    		return "circle red";
    	}
    }
	
	public String get12factorStatusIcon(int x) {
		String status;
		switch (x) {
	        case 1:  status = getCodebase();
	        		 return getIcon(status);
	        case 2:  status = getDependencies();
   		 			 return getIcon(status);
	        case 3:  status = getConfiguration();
   		 			 return getIcon(status);
	        case 4:  status = getBackingServices();
   		 			 return getIcon(status);
	        case 5:  status = getBuildReleaseRun();
   		 			 return getIcon(status);
	        case 6:  status = getProcesses();
   		 			 return getIcon(status);
	        case 7:  status = getPortBinding();
		   		 	 return getIcon(status);
		    case 8:  status = getConcurrency();
			 		 return getIcon(status);
		    case 9:  status = getDisposability();
			 		 return getIcon(status);
		    case 10: status = getDevProdParity();
			 		 return getIcon(status);
		    case 11: status = getLogs();
			 		 return getIcon(status);
		    case 12: status = getAdminProcesses();
			 		 return getIcon(status);
	        default: status = "circle red";
   		 			 return status;
	    }
    }
	
	public String getResiliencePatternStatusIcon(int x) {
		String status;
		switch (x) {
	        case 1:  status = getIsHostedInAZDCloud();
	        		 return getIcon(status);
	        case 2:  status = getRedundancy();
   		 			 return getIcon(status);
	        case 3:  status = getZeroDowntimeDeployment();
   		 			 return getIcon(status);
	        case 4:  status = getRetry();
   		 			 return getIcon(status);
	        case 5:  status = getIsolation();
   		 			 return getIcon(status);
	        case 6:  status = getCaching();
   		 			 return getIcon(status);
	        case 7:  status = getFallback();
		   		 	 return getIcon(status);
		    case 8:  status = getLooseCoupling();
			 		 return getIcon(status);
	        default: status = "circle red";
   		 			 return status;
	    }
    }

	
	//Setter
	//12 factor App
	public void setCodebase() {
		this.Codebase = "Yes";
	}
	public void setDependencies(String searchUrl) {
		String dependenciesUrl = "https://api.github.com/search/code?q=dependencies+repo:"+searchUrl;
		try{
			JSONObject response = new JSONObject(getRequestInformation(dependenciesUrl));
			System.out.println(response);
			JSONArray arr = response.getJSONArray("items");
			if(arr.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr.length(); i++) {
				    domElement = domElement + arr.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				this.Dependencies = "Yes";
			}else {
				this.Dependencies = "No dependencies found";
			}
		}catch(Exception e ) {
			this.Dependencies = "No";
		}
	}
	public void setConfiguration(String searchUrl) {
		String yml = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:.yml";
		String yaml = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:.yaml";
		String properties = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:.properties";
		String packageJson = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:package.json";
		String infoString = "";
		try{
			JSONObject responseYml = new JSONObject(getRequestInformation(yml));
			JSONObject responseYaml = new JSONObject(getRequestInformation(yaml));
			JSONObject responseProperties = new JSONObject(getRequestInformation(properties));
			JSONObject responsePackageJson = new JSONObject(getRequestInformation(packageJson));
			//yml
			JSONArray arr1 = responseYml.getJSONArray("items");
			if(arr1.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr1.length(); i++) {
				    domElement = domElement + arr1.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				System.out.println(this.Configuration);
				this.Configuration = "Yes";
			}
			//yaml
			JSONArray arr2 = responseYaml.getJSONArray("items");
			if(arr2.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr2.length(); i++) {
				    domElement = domElement + arr2.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				System.out.println(this.Configuration);
				this.Configuration = "Yes";
			}
			//properties
			JSONArray arr3 = responseProperties.getJSONArray("items");
			if(arr3.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr3.length(); i++) {
				    domElement = domElement + arr3.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				System.out.println(this.Configuration);
				this.Configuration = "Yes";
			}
			//package.json
			JSONArray arr4 = responsePackageJson.getJSONArray("items");
			if(arr4.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr4.length(); i++) {
				    domElement = domElement + arr4.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				System.out.println(this.Configuration);
				this.Configuration = "Yes";
			}
			System.out.println(this.Configuration);
		}catch(Exception e) {
			this.Configuration = "No";
		}
	}
	public void setBackingServices() {
		//TODO search for most common DBs
		this.BackingServices = "";
	}
	public void setBuildReleaseRun(String searchUrl) {
		String releaseUrl = "https://api.github.com/search/code?q=release+repo:"+searchUrl;
		try{
			JSONObject response = new JSONObject(getRequestInformation(releaseUrl));
			System.out.println(response);
			JSONArray arr = response.getJSONArray("items");
			if(arr.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr.length(); i++) {
				    domElement = domElement + arr.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				this.BuildReleaseRun = "Yes";
			}else {
				this.BuildReleaseRun = "No";
			}
		}catch(Exception e ) {
			this.BuildReleaseRun = "No";
		}
	}
	public void setProcesses() {
		this.Processes = "Not implemented";
	}
	public void setPortBinding(String searchUrl) {
		String portsUrl = "https://api.github.com/search/code?q=port+repo:"+searchUrl;
		this.PortBinding = "";
		try{
			JSONObject response = new JSONObject(getRequestInformation(portsUrl));
			System.out.println(response);
			JSONArray arr = response.getJSONArray("items");
			if(arr.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr.length(); i++) {
				    domElement = domElement + arr.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				this.PortBinding = "No";
			}else {
				this.PortBinding = "Yes";
			}
		}catch(Exception e ) {
			this.PortBinding = "No";
		}
	}
	public void setConcurrency() {
		this.Concurrency = "Not implemented";
	}
	public void setDisposability() {
		this.Disposability = "Not implemented";
	}
	public void setDevProdParity(String searchUrl) {
		String prodUrl = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:prod";
		String devUrl = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:dev";
		Boolean prodBoolean = false;
		Boolean devBoolean = false;
		try{
			JSONObject responseProd = new JSONObject(getRequestInformation(prodUrl));
			JSONArray arr1 = responseProd.getJSONArray("items");
			if(arr1.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr1.length(); i++) {
				    domElement = domElement + arr1.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				//this.DevProdParity = "No";
				prodBoolean = true;
			}
			JSONObject responseDev = new JSONObject(getRequestInformation(devUrl));
			JSONArray arr2 = responseProd.getJSONArray("items");
			if(arr2.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr1.length(); i++) {
				    domElement = domElement + arr1.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				//this.DevProdParity = "No";
				devBoolean = true;
			}
			if(prodBoolean && devBoolean) {
				this.DevProdParity = "Yes";
			}else {
				this.DevProdParity = "No";
			}
		}catch(Exception e ) {
			this.DevProdParity = "No";
		}
	}
	public void setLogs() {
		this.Logs = "Not implemented";
	}
	public void setAdminProcesses() {
		this.AdminProcesses = "Not implemented";
	}
	
	//Resilience Pattern
	public void setIsHostedInAZDCloud(String url) {
		System.out.println("setIsHostedInAZDCloud: " + url);
		if(url.contains("azd")) {
			this.isHostedInAZDCloud = "Yes";
		}else {
			this.isHostedInAZDCloud = "No";
		}
	}
	public void setRedundancy(String instances) {
		System.out.println("Instances: " + instances);
		String tmp = instances.substring(0, instances.lastIndexOf('/'));
		int numberOfInstances = Integer.parseInt(tmp);
		if(numberOfInstances > 1) {
			this.Redundancy = "Yes";
		}else {
			this.Redundancy = "No";
		}
	}
	public void setZeroDowntimeDeployment(String instances) {
		System.out.println("Instances: " + instances);
		String tmp = instances.substring(0, instances.lastIndexOf('/'));
		int numberOfInstances = Integer.parseInt(tmp);
		if(numberOfInstances > 1) {
			this.ZeroDowntimeDeployment = "Yes";
		}else {
			this.ZeroDowntimeDeployment = "No";
		}
	}
	public void setRetry(String searchUrl) {
		String failsafeUrl = "https://api.github.com/search/code?q=failsafe+repo:"+searchUrl;
		String histrixUrl = "https://api.github.com/search/code?q=histrix+repo:"+searchUrl;
		//TODO boolean for histrix und failsafe
		Boolean failsafe = false;
		Boolean histrix = false;
		try {
			JSONObject responseFailsafe = new JSONObject(getRequestInformation(failsafeUrl));
			JSONObject responseHistrix = new JSONObject(getRequestInformation(histrixUrl));
			//Failsafe
			JSONArray arr1 = responseFailsafe.getJSONArray("items");
			if(arr1.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr1.length(); i++) {
				    domElement = domElement + arr1.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				failsafe = true;
			}
			//Histrix
			JSONArray arr2 = responseHistrix.getJSONArray("items");
			if(arr2.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr2.length(); i++) {
				    domElement = domElement + arr2.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				histrix = true;
			}
			if(failsafe && histrix) {
				this.Retry = "Yes";
				this.Fallback = "Yes";
			}else {
				this.Retry = "No";
				this.Fallback = "No";
			}
		}catch(Exception e) {
			this.Retry = "No";
			this.Fallback = "No";
		}
	}
	//TODO: number fo services
	public void setIsolation(String services) {
		if(services.length() > 0) {
			this.Isolation = "No";
		}else {
			this.Isolation = "Yes";
		}
	}
	public void setCaching(String searchUrl) {
		String cacheUrl = "https://api.github.com/search/code?q=cach+repo:"+searchUrl;
		this.Caching = "";
		String dependenciesUrl = "https://api.github.com/search/code?q=dependencies+repo:"+searchUrl;
		try{
			JSONObject response = new JSONObject(getRequestInformation(cacheUrl));
			System.out.println(response);
			JSONArray arr = response.getJSONArray("items");
			if(arr.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr.length(); i++) {
				    domElement = domElement + arr.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				this.Caching = "Yes";
			}else {
				this.Caching = "No";
			}
		}catch(Exception e ) {
			this.Caching = "No";
		}
	}
	public void setFallback(){
		//Same as Retry
		//this.Fallback = "";
	}
	public void setLooseCoupling(){
		this.LooseCoupling = "Not implemented";
	}
	
}
