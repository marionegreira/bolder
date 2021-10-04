package com.bolder.server.models.dto;

import com.bolder.server.models.entity.IvaNegocio;

public class ClienteEditDto {
	
	private Long id;
	
	private String nombre;
	private String direccion;
	private String poblacion;
	private String cp;
	private String provincia;
	private String telefono;	
	private String nif;
	private String emailInfo;
	private String emailEnvioDocs;
	private String observaciones;
	private String ivaNegocioCodigo;
	private Boolean enabled;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getIvaNegocioCodigo() {
		return ivaNegocioCodigo;
	}
	public void setIvaNegocioCodigo(String ivaNegocioCodigo) {
		this.ivaNegocioCodigo = ivaNegocioCodigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getEmailInfo() {
		return emailInfo;
	}
	public void setEmailInfo(String emailInfo) {
		this.emailInfo = emailInfo;
	}
	public String getEmailEnvioDocs() {
		return emailEnvioDocs;
	}
	public void setEmailEnvioDocs(String emailEnvioDocs) {
		this.emailEnvioDocs = emailEnvioDocs;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	

}
