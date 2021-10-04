package com.bolder.server;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableAsync
public class BolderConfig {
	private final Logger logger = LoggerFactory.getLogger(BolderConfig.class);

	@Autowired
	Environment environment;
	
	
	
	
	@Bean
	public ITemplateResolver thymeleafTemplateResolver() {

		FileTemplateResolver templateResolver = new FileTemplateResolver();
	    templateResolver.setPrefix("attach/company/templates/");
	    templateResolver.setSuffix(".html");
	    templateResolver.setTemplateMode("HTML");
	    templateResolver.setCharacterEncoding("UTF-8");
	    //quitar al finalizar de debugar
	    //templateResolver.setCacheable(false);
	    //quitar al finalizar de debugar
	    return templateResolver;
	}
	

}
