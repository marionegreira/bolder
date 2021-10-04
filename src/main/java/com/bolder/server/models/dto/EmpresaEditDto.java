package com.bolder.server.models.dto;

import java.io.Serializable;

public class EmpresaEditDto implements Serializable{
	private static final long serialVersionUID = 5749194972881417669L;
	public EmpresaEditDto() {
	}
	
	private Long Id;
	private String endpointCode;
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
	private String smtpConfiguracion;
	private String logo;
	private String logor;
	private Long serieFacturaId;
	private Long seriePedidoId;
	private String serieFacturaSerie;
	private String seriePedidoSerie;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	
	public String getEndpointCode() {
		return endpointCode;
	}
	public void setEndpointCode(String endpointCode) {
		this.endpointCode = endpointCode;
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
	
	public String getWww() {
		return www;
	}
	public void setWww(String www) {
		this.www = www;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getSmtpConfiguracion() {
		return smtpConfiguracion;
	}
	public void setSmtpConfiguracion(String smtpConfiguracion) {
		this.smtpConfiguracion = smtpConfiguracion;
	}

	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getLogor() {
		return logor;
	}
	public void setLogor(String logor) {
		this.logor = logor;
	}
	public Long getSerieFacturaId() {
		return serieFacturaId;
	}
	
	public Long getSeriePedidoId() {
		return seriePedidoId;
	}
	public void setSeriePedidoId(Long seriePedidoId) {
		this.seriePedidoId = seriePedidoId;
	}
	public void setSerieFacturaId(Long serieFacturaId) {
		this.serieFacturaId = serieFacturaId;
	}
	public String getSerieFacturaSerie() {
		return serieFacturaSerie;
	}
	public void setSerieFacturaSerie(String serieFacturaSerie) {
		this.serieFacturaSerie = serieFacturaSerie;
	}
	public String getSeriePedidoSerie() {
		return seriePedidoSerie;
	}
	public void setSeriePedidoSerie(String seriePedidoSerie) {
		this.seriePedidoSerie = seriePedidoSerie;
	}
	
	
}
