package com.bolder.server.models.dto;

import java.util.Date;

public class SerieRegistroEditDto {
	private Long id;
	private String serie;
	private String descripcion;
	private Long ultimoNumeroAsignado;
	private Date ultimaFechaRegistro;
	private Boolean secuenciaFecha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getUltimoNumeroAsignado() {
		return ultimoNumeroAsignado;
	}
	public void setUltimoNumeroAsignado(Long ultimoNumeroAsignado) {
		this.ultimoNumeroAsignado = ultimoNumeroAsignado;
	}
	public Date getUltimaFechaRegistro() {
		return ultimaFechaRegistro;
	}
	public void setUltimaFechaRegistro(Date ultimaFechaRegistro) {
		this.ultimaFechaRegistro = ultimaFechaRegistro;
	}
	public Boolean getSecuenciaFecha() {
		return secuenciaFecha;
	}
	public void setSecuenciaFecha(Boolean secuenciaFecha) {
		this.secuenciaFecha = secuenciaFecha;
	}
	
	
}
