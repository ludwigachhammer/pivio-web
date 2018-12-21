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
	//12 factor App - Info
	private String InfoCodebase = "";
	private String InfoDependencies = "";
	private String InfoConfiguration = "";
	private String InfoBackingServices = "";
	private String InfoBuildReleaseRun = "";
	private String InfoProcesses = "";
	private String InfoPortBinding = "";
	private String InfoConcurrency = "";
	private String InfoDisposability = "";
	private String InfoDevProdParity = "";
	private String InfoLogs = "";
	private String InfoAdminProcesses = "";
	
	//Resilience pattern
	private String isHostedInAZDCloud;
	private String Redundancy;
	private String ZeroDowntimeDeployment;
	private String Retry;
	private String Isolation;
	private String Caching;
	private String Fallback;
	private String LooseCoupling;
	private String Url;
	//Resilience pattern - Info
	private String InfoisHostedInAZDCloud = "";
	private String InfoRedundancy = "";
	private String InfoZeroDowntimeDeployment = "";
	private String InfoRetry = "";
	private String InfoIsolation = "";
	private String InfoCaching = "";
	private String InfoFallback = "";
	private String InfoLooseCoupling = "";
	private String InfoUrl = "";
	
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
			setCodebase(searchUrl);
			setDependencies(searchUrl);
			setConfiguration(searchUrl);
			//TODO setBackingServices(searchUrl);
			setBuildReleaseRun(searchUrl);
			setProcesses();
			setPortBinding(searchUrl);
			setConcurrency();
			setDisposability();
			setDevProdParity(searchUrl);
			setLogs(searchUrl);
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
	
	//GETTER
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
    		return "circle orange";
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
	
	//SETTER
	//12 factor App
	public void setCodebase(String searchUrl) {
		setInfoCodebase("The repository for the application/service is: "+searchUrl);
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
				setInfoDependencies("The following files contain dependencies: "+domElement);
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
				infoString = infoString + domElement;
				setInfoConfiguration(infoString);
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
				infoString = infoString + domElement;
				setInfoConfiguration(infoString);
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
				infoString = infoString + domElement;
				setInfoConfiguration(infoString);
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
				infoString = infoString + domElement;
				setInfoConfiguration(infoString);
				System.out.println(this.Configuration);
				this.Configuration = "Yes";
			}
			setInfoConfiguration("The following files are configuration files: "+infoString);
			System.out.println(this.Configuration);
		}catch(Exception e) {
			this.Configuration = "No";
		}
	}
	public void setBackingServices() {
		//TODO search for most common DBs
		this.BackingServices = "Yes";
		setInfoBackingServices("No information about the backing services available");
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
				setInfoBuildReleaseRun(domElement);
				this.BuildReleaseRun = "Yes";
			}else {
				this.BuildReleaseRun = "No";
			}
		}catch(Exception e ) {
			this.BuildReleaseRun = "No";
		}
	}
	public void setProcesses() {
		setInfoProcesses("No information about the processes available");
		this.Processes = "Yes";
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
				setInfoPortBinding("A static port is defined in the following documents: "+domElement);
				this.PortBinding = "No";
			}else {
				setInfoPortBinding("No static ports are defined.");
				this.PortBinding = "Yes";
			}
		}catch(Exception e ) {
			this.PortBinding = "No";
		}
	}
	public void setConcurrency() {
		setInfoConcurrency("Not implemented");
		this.Concurrency = "Not implemented";
	}
	public void setDisposability() {
		setInfoDisposability("Not implemented");
		this.Disposability = "Not implemented";
	}
	public void setDevProdParity(String searchUrl) {
		String prodUrl = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:prod";
		String devUrl = "https://api.github.com/search/code?q=repo:"+searchUrl+"+filename:dev";
		Boolean prodBoolean = false;
		Boolean devBoolean = false;
		String infoString = "";
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
				infoString = infoString + domElement;
				setInfoDevProdParity(infoString);
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
				infoString = infoString + domElement;
				setInfoDevProdParity(infoString);
				devBoolean = true;
			}
			if(prodBoolean && devBoolean) {
				setInfoDevProdParity("The following dev/prod configuration files were found: "+infoString);
				this.DevProdParity = "Yes";
			}else {
				setInfoDevProdParity("No dev/prod configuration files found");
				this.DevProdParity = "No";
			}
		}catch(Exception e ) {
			setInfoDevProdParity("Error retrieving dev/prod information");
			this.DevProdParity = "No";
		}
	}
	public void setLogs(String searchUrl) {
		String logsUrl = "https://api.github.com/search/code?q=log+repo:"+searchUrl;
		try{
			JSONObject response = new JSONObject(getRequestInformation(logsUrl));
			System.out.println(response);
			JSONArray arr = response.getJSONArray("items");
			if(arr.length() > 0){
				String domElement = "";
				for (int i = 0; i < arr.length(); i++) {
				    domElement = domElement + arr.getJSONObject(i).getString("name")+";";
				}
				//domElement for InfoDialog
				setInfoLogs(domElement);
				this.Logs = "No";
			}else {
				setInfoLogs("The application/service does not use log dependencies");
				this.Logs = "Yes";
			}
		}catch(Exception e ) {
			setInfoDevProdParity("Error retrieving logs information");
			this.Logs = "No";
		}
	}
	public void setAdminProcesses() {
		setInfoAdminProcesses("Not implemented");
		this.AdminProcesses = "Not implemented";
	}	
	//Resilience Pattern
	public void setIsHostedInAZDCloud(String url) {
		System.out.println("setIsHostedInAZDCloud: " + url);
		if(url.contains("azd")) {
			setInfoisHostedInAZDCloud("This application is hosted in the AZD Cloud");
			this.isHostedInAZDCloud = "Yes";
		}else {
			setInfoisHostedInAZDCloud("This application is not hosted in the AZD Cloud");
			this.isHostedInAZDCloud = "No";
		}
	}
	public void setRedundancy(String instances) {
		System.out.println("Instances: " + instances);
		String tmp = instances.substring(0, instances.lastIndexOf('/'));
		int numberOfInstances = Integer.parseInt(tmp);
		if(numberOfInstances > 1) {
			setInfoRedundancy("This application has implemented redundancy");
			this.Redundancy = "Yes";
		}else {
			setInfoRedundancy("This application has not implemented redundancy");
			this.Redundancy = "No";
		}
	}
	public void setZeroDowntimeDeployment(String instances) {
		System.out.println("Instances: " + instances);
		String tmp = instances.substring(0, instances.lastIndexOf('/'));
		int numberOfInstances = Integer.parseInt(tmp);
		if(numberOfInstances > 1) {
			setInfoZeroDowntimeDeployment("");
			this.ZeroDowntimeDeployment = "Yes";
		}else {
			setInfoZeroDowntimeDeployment("");
			this.ZeroDowntimeDeployment = "No";
		}
	}
	public void setRetry(String searchUrl) {
		String failsafeUrl = "https://api.github.com/search/code?q=failsafe+repo:"+searchUrl;
		String histrixUrl = "https://api.github.com/search/code?q=histrix+repo:"+searchUrl;
		//TODO boolean for histrix und failsafe
		Boolean failsafe = false;
		Boolean histrix = false;
		String infoString = "";
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
				infoString = infoString + domElement;
				setInfoRetry(infoString);
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
				infoString = infoString + domElement;
				setInfoRetry(infoString);
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
			setInfoIsolation("This application/service is not isolated, please see section services");
			this.Isolation = "No";
		}else {
			setInfoIsolation("This application/services is isolated. Does not communicate to other applications/services");
			this.Isolation = "Yes";
		}
	}
	public void setCaching(String searchUrl) {
		String cacheUrl = "https://api.github.com/search/code?q=cach+repo:"+searchUrl;
		this.Caching = "";
		String dependenciesUrl = "https://api.github.com/search/code?q=dependencies+repo:"+searchUrl;
		String infoString = "";
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
				infoString = infoString + domElement;
				setInfoCaching(infoString);
				this.Caching = "Yes";
			}else {
				setInfoCaching("This application/service has no caching dependency");
				this.Caching = "No";
			}
		}catch(Exception e ) {
			setInfoCaching("This application/service has no caching dependency");
			this.Caching = "No";
		}
	}
	public void setFallback(){
		//Same as Retry
		//this.Fallback = "";
	}
	public void setLooseCoupling(){
		setInfoLooseCoupling("Not implemented");
		this.LooseCoupling = "Not implemented";
	}
	
	
	//Info for dialog box
	public String getInfoisHostedInAZDCloud() {
		return InfoisHostedInAZDCloud;
	}

	public void setInfoisHostedInAZDCloud(String infoisHostedInAZDCloud) {
		InfoisHostedInAZDCloud = infoisHostedInAZDCloud;
	}

	public String getInfoRedundancy() {
		return InfoRedundancy;
	}

	public void setInfoRedundancy(String infoRedundancy) {
		InfoRedundancy = infoRedundancy;
	}

	public String getInfoZeroDowntimeDeployment() {
		return InfoZeroDowntimeDeployment;
	}

	public void setInfoZeroDowntimeDeployment(String infoZeroDowntimeDeployment) {
		InfoZeroDowntimeDeployment = infoZeroDowntimeDeployment;
	}

	public String getInfoRetry() {
		return InfoRetry;
	}

	public void setInfoRetry(String infoRetry) {
		InfoRetry = infoRetry;
	}

	public String getInfoIsolation() {
		return InfoIsolation;
	}

	public void setInfoIsolation(String infoIsolation) {
		InfoIsolation = infoIsolation;
	}

	public String getInfoCaching() {
		return InfoCaching;
	}

	public void setInfoCaching(String infoCaching) {
		InfoCaching = infoCaching;
	}

	public String getInfoFallback() {
		return InfoFallback;
	}

	public void setInfoFallback(String infoFallback) {
		InfoFallback = infoFallback;
	}

	public String getInfoLooseCoupling() {
		return InfoLooseCoupling;
	}

	public void setInfoLooseCoupling(String infoLooseCoupling) {
		InfoLooseCoupling = infoLooseCoupling;
	}

	public String getInfoUrl() {
		return InfoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		InfoUrl = infoUrl;
	}

	public String getInfoCodebase() {
		return InfoCodebase;
	}

	public void setInfoCodebase(String infoCodebase) {
		InfoCodebase = infoCodebase;
	}

	public String getInfoDependencies() {
		return InfoDependencies;
	}

	public void setInfoDependencies(String infoDependencies) {
		InfoDependencies = infoDependencies;
	}

	public String getInfoConfiguration() {
		return InfoConfiguration;
	}

	public void setInfoConfiguration(String infoConfiguration) {
		InfoConfiguration = infoConfiguration;
	}

	public String getInfoBackingServices() {
		return InfoBackingServices;
	}

	public void setInfoBackingServices(String infoBackingServices) {
		InfoBackingServices = infoBackingServices;
	}

	public String getInfoBuildReleaseRun() {
		return InfoBuildReleaseRun;
	}

	public void setInfoBuildReleaseRun(String infoBuildReleaseRun) {
		InfoBuildReleaseRun = infoBuildReleaseRun;
	}

	public String getInfoProcesses() {
		return InfoProcesses;
	}

	public void setInfoProcesses(String infoProcesses) {
		InfoProcesses = infoProcesses;
	}

	public String getInfoPortBinding() {
		return InfoPortBinding;
	}

	public void setInfoPortBinding(String infoPortBinding) {
		InfoPortBinding = infoPortBinding;
	}

	public String getInfoConcurrency() {
		return InfoConcurrency;
	}

	public void setInfoConcurrency(String infoConcurrency) {
		InfoConcurrency = infoConcurrency;
	}

	public String getInfoDisposability() {
		return InfoDisposability;
	}

	public void setInfoDisposability(String infoDisposability) {
		InfoDisposability = infoDisposability;
	}

	public String getInfoDevProdParity() {
		return InfoDevProdParity;
	}

	public void setInfoDevProdParity(String infoDevProdParity) {
		InfoDevProdParity = infoDevProdParity;
	}

	public String getInfoLogs() {
		return InfoLogs;
	}

	public void setInfoLogs(String infoLogs) {
		InfoLogs = infoLogs;
	}

	public String getInfoAdminProcesses() {
		return InfoAdminProcesses;
	}

	public void setInfoAdminProcesses(String infoAdminProcesses) {
		InfoAdminProcesses = infoAdminProcesses;
	}
}
