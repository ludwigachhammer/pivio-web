package io.pivio.view.app.servicegraph;

import io.pivio.view.PivioServerConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
public class ServiceGraphService {

    @Autowired
    PivioServerConnector pivioServerConnector;
    
    public List<String> getNodes() throws Exception{
        List<String> nodes = pivioServerConnector.getAllNames();
        return nodes;
    }
    
}
