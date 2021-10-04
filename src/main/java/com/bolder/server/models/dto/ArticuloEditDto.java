package com.bolder.server.models.dto;

import java.io.Serializable;

public class ArticuloEditDto implements Serializable{

	private static final long serialVersionUID = -4862278532256564233L;
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private Double precioBase;
	private String ivaProductoCodigo;
	private String ivaProductoDescripcion;
		
	
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

	public Double getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}
	public String getIvaProductoCodigo() {
		return ivaProductoCodigo;
	}
	public void setIvaProductoCodigo(String ivaProductoCodigo) {
		this.ivaProductoCodigo = ivaProductoCodigo;
	}
	public String getIvaProductoDescripcion() {
		return ivaProductoDescripcion;
	}
	public void setIvaProductoDescripcion(String ivaProductoDescripcion) {
		this.ivaProductoDescripcion = ivaProductoDescripcion;
	}

}
