package com.bolder.server.models.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.bolder.server.models.entity.FacturacionAutomaticaLin;
import com.bolder.server.models.entity.FacturacionAutomatica.Periodicidad;

public class FacturacionAutomaticaDtoBig implements Serializable{

	private static final long serialVersionUID = -2620170860081207964L;
	
	private Long id;
	private Long clienteId;
	private String clienteNombre;
	private List<FacturacionAutomaticaLinEditDto> lineas;
	private String descripcion;
	private Periodicidad periodicidad;
	private Date SiguienteEjecucion;
	private Boolean activo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public String getClienteNombre() {
		return clienteNombre;
	}
	public void setClienteNombre(String clienteNombre) {
		this.clienteNombre = clienteNombre;
	}

	public List<FacturacionAutomaticaLinEditDto> getLineas() {
		return lineas;
	}
	public void setLineas(List<FacturacionAutomaticaLinEditDto> lineas) {
		this.lineas = lineas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}
	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}
	public Date getSiguienteEjecucion() {
		return SiguienteEjecucion;
	}
	public void setSiguienteEjecucion(Date siguienteEjecucion) {
		SiguienteEjecucion = siguienteEjecucion;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}
