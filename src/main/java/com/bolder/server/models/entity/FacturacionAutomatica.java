package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicUpdate
public class FacturacionAutomatica implements Serializable{

	private static final long serialVersionUID = -9164941505123194135L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "cliente_id") 
	private Cliente cliente;
	@OneToMany(mappedBy = "facturacionAutomatica",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<FacturacionAutomaticaLin> facturacionAutomaticaLineas;
	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String descripcion;
	private Periodicidad periodicidad;
	private Date SiguienteEjecucion;
	private Boolean activo=true;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<FacturacionAutomaticaLin> getFacturacionAutomaticaLineas() {
		return facturacionAutomaticaLineas;
	}

	public void setFacturacionAutomaticaLineas(List<FacturacionAutomaticaLin> facturacionAutomaticaLineas) {
		this.facturacionAutomaticaLineas = facturacionAutomaticaLineas;
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

	public enum Periodicidad{
		DIARIO,SEMANAL,QUINCENAL,MENSUAL,ANUAL
	}

}
