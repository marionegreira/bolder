package com.bolder.server.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class Articulo implements Serializable{

	private static final long serialVersionUID = -2316452735018837236L;
	
	@Id
	private String codigo;
	
	private String nombre;
	@Column(columnDefinition="VARCHAR(200) DEFAULT ''")
	private String descripcion="";
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "iva_producto_codigo")
	private IvaProducto ivaProducto;
	private Double precioBase=0D; 


	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public IvaProducto getIvaProducto() {
		return ivaProducto;
	}

	public void setIvaProducto(IvaProducto ivaProducto) {
		this.ivaProducto = ivaProducto;
	}

	public Double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}


}
