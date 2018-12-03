package io.pivio.view.app.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

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
	
	//TODO: Convert calls into Octokit object
	
	public GovernanceMonitoring(String url) {
		System.out.println("******** getGovernanceMonitoringData ******************");
		String governanceUrl = url;
		String repository = governanceUrl.substring(governanceUrl.lastIndexOf('/')+1, governanceUrl.length());
		String tmpUrl = governanceUrl.substring(0, governanceUrl.lastIndexOf('/'));
		String user = governanceUrl.substring(tmpUrl.lastIndexOf('/')+1, tmpUrl.length());
		String searchUrl = user+'/'+repository;
		System.out.println(searchUrl);
		try {
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
			
			this.isHostedInAZDCloud = "";
			this.Redundancy = "";
			this.ZeroDowntimeDeployment = "";
			this.Retry = "";
			this.Isolation = "";
			this.Caching = "";
			this.Fallback = "";
			this.LooseCoupling = "";
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
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
	     return response.toString();
	}
	
	public String getIcon(String status) {
    	if(!(status.equals("Yes"))) {
    		return "circle red";
    	}
    	else {
    		return "circle green";
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

}
