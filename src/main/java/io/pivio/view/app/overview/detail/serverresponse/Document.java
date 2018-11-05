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

//@JsonIgnoreProperties(ignoreUnknown = true)
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
    
    
    //Nico
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
                //", tags=\"" + tags ++ "\"" +
                //", links=" + links +
                ",\"runtime\":" + runtime.toString() +
                //", software_dependencies:" + software_dependencies +
                ",\"service\":" + service.toString() +
                //Nico
                formatAdditionalAttributes(other_attributes)+
                '}';
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
