package com.bolder.server.models.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonValue;
import com.bolder.server.models.entity.Usuario.RoleType;
public class UsuarioEditDto implements Serializable{
	private static final long serialVersionUID = -8989871591444260629L;

	private Long id;
	private String nombre;
	private String apellido;
	private String username;
	private String email;
	private RoleType role;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	
}
