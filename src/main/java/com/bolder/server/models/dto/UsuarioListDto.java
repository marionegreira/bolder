package com.bolder.server.models.dto;

import java.io.Serializable;

public class UsuarioListDto implements Serializable{
	private static final long serialVersionUID = 786643920017085866L;

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private Boolean enabled;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

	
	
	
}
