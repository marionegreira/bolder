package com.bolder.server.system.service;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.bolder.server.system.entity.SpConfiguracionBean;
import com.bolder.server.system.entity.SpSmtpConfiguration;

@Component
public class SpDataConfigurationService {

	public SpDataConfigurationService() {
		
		pathUploadsAttach="uploads//attaches";
		pathSystemAttach="attach//system";
		pathCompanyAttach="attach//company";
	}
	
	private String pathUploadsAttach;
	private String pathSystemAttach;
	private String pathCompanyAttach;
	private String rootUri;
	private Date queueNextTime;
	private String secretMobile;
	private String secretUserWeb;
	private String secretUserExternalAnonimous;
	private SpSmtpConfiguration smtpConfiguration;
	private SpConfiguracionBean configuracion;

	public String getPathUploadsAttach() {
		return pathUploadsAttach;
	}

	public void setPathUploadsAttach(String pathUploadsAttach) {
		this.pathUploadsAttach = pathUploadsAttach;
	}

	public String getPathSystemAttach() {
		return pathSystemAttach;
	}

	public void setPathSystemAttach(String pathSystemAttach) {
		this.pathSystemAttach = pathSystemAttach;
	}

	public String getPathCompanyAttach() {
		return pathCompanyAttach;
	}

	public void setPathCompanyAttach(String pathCompanyAttach) {
		this.pathCompanyAttach = pathCompanyAttach;
	}

	
	public String getRootUri() {
		return rootUri;
	}

	public void setRootUri(String rootUri) {
		this.rootUri = rootUri;
	}

	public Date getQueueNextTime() {
		return queueNextTime;
	}

	public void setQueueNextTime(Date queueNextTime) {
		this.queueNextTime = queueNextTime;
	}
	
	

	public String getSecretMobile() {
		return secretMobile;
	}

	public void setSecretMobile(String secretMobile) {
		this.secretMobile = secretMobile;
	}

	public String getSecretUserWeb() {
		return secretUserWeb;
	}

	public void setSecretUserWeb(String secretUserWeb) {
		this.secretUserWeb = secretUserWeb;
	}

	public String getSecretUserExternalAnonimous() {
		return secretUserExternalAnonimous;
	}

	public void setSecretUserExternalAnonimous(String secretUserExternalAnonimous) {
		this.secretUserExternalAnonimous = secretUserExternalAnonimous;
	}

	public SpSmtpConfiguration getSmtpConfiguration() {
		return smtpConfiguration;
	}

	public void setSmtpConfiguration(SpSmtpConfiguration smtpConfiguration) {
		this.smtpConfiguration = smtpConfiguration;
	}

	public SpConfiguracionBean getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(SpConfiguracionBean configuracion) {
		this.configuracion = configuracion;
	}
	
}
