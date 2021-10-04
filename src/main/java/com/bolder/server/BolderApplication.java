package com.bolder.server;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bolder.server.system.service.SpDataConfigurationService;


@SpringBootApplication
@EnableScheduling
public class BolderApplication implements CommandLineRunner{
	
	

	@Autowired
	BCryptPasswordEncoder  passwordEncoder;
	
	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(BolderApplication.class);
        Properties properties = new Properties();
        
        try {
            File file = ResourceUtils.getFile("configuration.json");
    		ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    		JsonNode configurationJson = mapper.readTree(new String(Files.readAllBytes(file.toPath())));
    		
    		
    		Map<String, String> propertiesMap = new ObjectMapper().convertValue(configurationJson.get("properties"), new TypeReference<Map<String, String>>(){});
    		properties.putAll(propertiesMap);
    		
    		application.setDefaultProperties(properties);
		} catch (Exception e) {		}

		application.run(args);
	}

	  
	@Override
	public void run(String... args) throws Exception {
		
		String password = "12345";

		
		for (int i=0; i<2;i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println("--------------------------->"+bcryptPassword);			
		}
		
		File file = ResourceUtils.getFile("configuration.json");
		ObjectMapper mapper = new ObjectMapper().configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		JsonNode configurationJson = mapper.readTree(new String(Files.readAllBytes(file.toPath())));
		
		spDataConfigurationService.setSecretMobile(configurationJson.get("secrets").get("mobile").textValue());
		spDataConfigurationService.setSecretUserWeb(configurationJson.get("secrets").get("userWeb").textValue());
		spDataConfigurationService.setSecretUserExternalAnonimous(configurationJson.get("secrets").get("extAnon").textValue());
		String url = configurationJson.get("webServer").get("url").textValue();
		spDataConfigurationService.getConfiguracion().setUrl(url);

		File attachFolder = new File( spDataConfigurationService.getPathUploadsAttach() );
		if (!attachFolder.exists()) attachFolder.mkdirs();
		File systemAttach = new File( spDataConfigurationService.getPathSystemAttach() );
		if (!systemAttach.exists()) systemAttach.mkdirs();
		File companyAttach = new File( spDataConfigurationService.getPathCompanyAttach() );
		if (!companyAttach.exists()) companyAttach.mkdirs();
		
		

	}
	

}
