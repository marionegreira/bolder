package com.bolder.server;

import java.io.File;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WebServerConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
	private final Logger logger = LoggerFactory.getLogger(WebServerConfig.class);

    @Override
		public void customize(ConfigurableWebServerFactory factory) {
    	
    		try {
				File file = ResourceUtils.getFile("configuration.json");
				ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				JsonNode jsonNode = mapper.readTree(new String(Files.readAllBytes(file.toPath())));
				
				factory.setPort(jsonNode.get("webServer").get("port").intValue());
        	} catch (Exception e) {
        		logger.error("error al parsear configuration.json");
        		e.printStackTrace();
        	}
    	
		    
		}
}
