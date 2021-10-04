package com.bolder.server;

import java.util.Properties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bolder.server.models.dao.IEmpresaDao;
import com.bolder.server.models.dto.SmtpDto;
import com.bolder.server.models.entity.Empresa;
import com.bolder.server.system.entity.SpConfiguracionBean;
import com.bolder.server.system.entity.SpSmtpConfiguration;
import com.bolder.server.system.service.SpDataConfigurationService;



@Configuration
public class SPMailSender extends JavaMailSenderImpl {

	@Autowired
	private IEmpresaDao configuracionDao;
	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	@Autowired
	Environment environment;
	
	@Bean
    public JavaMailSender getJavaMailSender() {
		
		
		Empresa configuracion = configuracionDao.findFirst();
		SmtpDto smtpDto = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			spDataConfigurationService.setSmtpConfiguration(objectMapper.readValue(configuracion.getSmtpConfiguracion(), SpSmtpConfiguration.class));
			spDataConfigurationService.setConfiguracion( new ModelMapper().map(configuracion, SpConfiguracionBean.class) );
			spDataConfigurationService.setRootUri("http://localhost:"+environment.getProperty("local.server.port"));
			
			smtpDto = objectMapper.readValue(configuracion.getSmtpConfiguracion(), SmtpDto.class);
	        Properties props = this.getJavaMailProperties();
			props.put("mail.smtp.host", smtpDto.getHost() );
			props.put("mail.smtp.port", smtpDto.getPort());
			if (smtpDto.isSmtpTtls())
				props.put("mail.smtp.starttls.enable", "true");
			if ( smtpDto.isSmtpAuth() ) {
				props.put("mail.smtp.auth", "true");
			    this.setUsername(smtpDto.getUsername());
			    this.setPassword(smtpDto.getPassword());
			}
			else 
				props.put("mail.smtp.auth", "false");
		    //props.put("mail.debug", "true");
		    
		    props.put("mail.smtp.timeout", 20000);
		    props.put("mail.smtps.connectiontimeout", 60000);
		    
		    

		} catch (Exception e) {

		}
    
	    return this;
		
	}
    

}
