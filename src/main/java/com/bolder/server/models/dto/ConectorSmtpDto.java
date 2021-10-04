package com.bolder.server.models.dto;

import java.io.Serializable;


public class ConectorSmtpDto implements Serializable{
	private static final long serialVersionUID = -4334412352805720360L;

	public ConectorSmtpDto() {
	}
	
	private Long Id;
	private String  mailSender;
	private String Host;
	private int Port;
	private boolean smtpAuth;
	private boolean smtpTtls;
	private String username;
	private String password;

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getMailSender() {
		return mailSender;
	}
	public void setMailSender(String mailSender) {
		this.mailSender = mailSender;
	}

	public String getHost() {
		return Host;
	}
	public void setHost(String host) {
		Host = host;
	}
	public int getPort() {
		return Port;
	}
	public void setPort(int port) {
		Port = port;
	}
	public boolean isSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(boolean smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public boolean isSmtpTtls() {
		return smtpTtls;
	}
	public void setSmtpTtls(boolean smtpTtls) {
		this.smtpTtls = smtpTtls;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
