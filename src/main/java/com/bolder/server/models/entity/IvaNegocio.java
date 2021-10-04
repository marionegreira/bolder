package com.bolder.server.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class IvaNegocio implements Serializable {
	
	private static final long serialVersionUID = 3431492132600097970L;
	
	public IvaNegocio() {}
	public IvaNegocio(String codigo) {
		this.codigo = codigo;
	}
	
	@Id
	private String codigo;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(200) DEFAULT ''")
	private String descripcion;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
