package io.pivio.view.configuration;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServerConfig {
    public List pages = new ArrayList<>(); // show nothing for default
    //TODO: Link config file to variables
    public String apiAddress = "https://pivio-server.cfapps.io:9123"; // default @Value("${config.api}")
    public String mainUrl = "https://pivio-web.cfapps.io:9999"; // needs to be determined later to match the host with the 'main' app. @Value("${config.js_api}")
    public String jsApiAddress = "https://pivio-server.cfapps.io:9123/"; // If your webbrowser needs access to the API, the address is a different one. @Value("${config.mainurl}")

    @Override
    public String toString() {
        return "ServerConfig{" +
                "pages=" + pages +
                ", apiAddress='" + apiAddress + '\'' +
                ", mainUrl='" + mainUrl + '\'' +
                ", jsApiAddress='" + jsApiAddress + '\'' +
                '}';
    }
}
