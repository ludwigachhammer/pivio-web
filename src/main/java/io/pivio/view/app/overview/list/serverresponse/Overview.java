package io.pivio.view.app.overview.list.serverresponse;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.pivio.view.app.overview.detail.serverresponse.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * Model of the landing-page
 * 
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Overview implements Comparable {

    public String name;
    public String status;
    public String short_name;
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
    //links
    public String jira;
    public String github;
    public String jenkins;
    public String cloudfoundry;
    public String iteraplan;

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Overview) o).name);
    }
}
