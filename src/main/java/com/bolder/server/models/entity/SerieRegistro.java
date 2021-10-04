package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class SerieRegistro implements Serializable {

	private static final long serialVersionUID = 2043216045689938857L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String serie;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(200) DEFAULT ''")
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
