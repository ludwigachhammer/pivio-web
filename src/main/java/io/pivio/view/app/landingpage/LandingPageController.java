package io.pivio.view.app.landingpage;

import io.pivio.view.PivioServerConnector;
import io.pivio.view.configuration.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class LandingPageController {

    @Autowired
    ServerConfig serverConfig;
    
    @RequestMapping(value = "/app/landingpage")
    public String relations(Model model) throws Exception{
    	model.addAttribute("config", serverConfig);
        model.addAttribute("pageId", "tabLandingpage");
        return "landingpage";
    }

}
