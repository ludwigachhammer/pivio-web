package io.pivio.view.app.overview.detail.serverresponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Runtime {

    public String cpu;
    public String ram;
    public String disk;
    public String instances;
    public String host_type;
    public String network_zone;
    
    public String toString(){
    	return "{"+
    			"\"cpu\":\"" + cpu + "\"" +
                ", \"ram\":\"" + ram + "\"" +
                ", \"disk\":\"" + disk + "\"" +
                ", \"instances\":\"" + instances + "\"" +
                ", \"host_type\":\"" + host_type + "\"" +
                ", \"network_zone\":\"" + network_zone + "\"" +
    			"}";
    }
}
