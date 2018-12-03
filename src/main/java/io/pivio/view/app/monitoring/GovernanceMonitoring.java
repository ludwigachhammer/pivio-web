package io.pivio.view.app.monitoring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class GovernanceMonitoring {
	
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
	
	private String isHostedInAZDCloud;
	private String Redundancy;
	private String ZeroDowntimeDeployment;
	private String Retry;
	private String Isolation;
	private String Caching;
	private String Fallback;
	private String LooseCoupling;
	
	private String url;
	/*
	public GovernanceMonitoring(String url) {
		this.Codebase;
		this.Dependencies;
		this.Configuration;
		this.BackingServices;
		this.BuildReleaseRun;
		this.Processes;
		this.PortBinding;
		this.Concurrency;
		this.Disposability;
		this.DevProdParity;
		this.Logs;
		this.AdminProcesses;
		
		this.isHostedInAZDCloud;
		this.Redundancy;
		this.ZeroDowntimeDeployment;
		this.Retry;
		this.Isolation;
		this.Caching;
		this.Fallback;
		this.LooseCoupling;
	}
	*/
	
	public String getCodebase() {
		//Codebase = getRequestInformation();
		return Codebase;
	}

	public void setCodebase(String codebase) {
		Codebase = codebase;
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

}
