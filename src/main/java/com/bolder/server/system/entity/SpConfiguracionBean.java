package com.bolder.server.system.entity;

import java.io.Serializable;

import com.bolder.server.models.entity.SerieRegistro;


public class SpConfiguracionBean implements Serializable{

	private static final long serialVersionUID = -6472229909022897005L;
	public SpConfiguracionBean() {
	}
	
	private Long Id;
	private String url;
	private String nombre;
	private String direccion;
	private String poblacion;
	private String cp;
	private String provincia;	
	private String telefono;
	private String email;
	private String www;
	private String cif;
	
	private SerieRegistro serieFactura;
	private SerieRegistro seriePedido;
	
	

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public SerieRegistro getSerieFactura() {
		return serieFactura;
	}
	public void setSerieFactura(SerieRegistro serieFactura) {
		this.serieFactura = serieFactura;
	}
	public SerieRegistro getSeriePedido() {
		return seriePedido;
	}
	public void setSeriePedido(SerieRegistro seriePedido) {
		this.seriePedido = seriePedido;
	}
	
	
}
