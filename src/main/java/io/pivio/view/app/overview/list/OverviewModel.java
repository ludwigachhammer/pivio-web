package io.pivio.view.app.overview.list;

import io.pivio.view.app.overview.detail.serverresponse.Context;
import org.joda.time.DateTime;
import org.ocpsoft.prettytime.PrettyTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverviewModel {
    public String name;
    public String short_name;
    public String status;
    public String description;
    public String lastUpload;
    public String lastUpdate;
    public Context context;
    public String domain;
    public String subdomain;
    public String product;
    public String id;
    public String owner;
    public String type;

    public String getPrettyUploadDate() {
        return new PrettyTime().format(new DateTime(lastUpload).toDate());
    }

    public String getPrettyUpdateDate() {
        return new PrettyTime().format(new DateTime(lastUpdate).toDate());
    }

    public String getMeta() {
        String meta = "cloudfoundry" + name + " " + short_name + " " + description + " " + owner + " " + domain + " " + subdomain + " " + product;
        if (context != null) {
            meta = meta + " " + context.belongs_to_bounded_context;
        }
        return meta.toLowerCase();
    }
    
    public String getName(){
    	return name;
    }
    
    public String getDomain() {
    	return domain;
    }
    
    public String getSubdomain() {
    	return subdomain;
    }
    
    public String getProduct() {
    	return product;
    }
    
    public String getStatusIcon() {
    	if(status != null) {
    		if(status.equals("running")) {
        		return "circle green";
        	}
        	if(status.equals("stopped") || status.equals("crashed")) {
        		return "circle red";
        	}
        	else {
        		return "circle yellow";
        	}
    	}else {
    		return "circle yellow";
    	}
    	
    }	
    

    public String getCardColor() {
        if (type.equalsIgnoreCase("service")) {
            return "blue";
        }
        return "green";
    }
}
