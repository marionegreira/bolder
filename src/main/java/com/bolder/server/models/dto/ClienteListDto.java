package com.bolder.server.models.dto;

import com.bolder.server.models.entity.IvaNegocio;

public class ClienteListDto {
	
	private Long id;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String cp;
	private String provincia;
	private String nif;
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
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	

}
