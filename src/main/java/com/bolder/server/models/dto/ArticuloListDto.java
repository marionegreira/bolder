package com.bolder.server.models.dto;

import java.io.Serializable;

public class ArticuloListDto implements Serializable {

	private static final long serialVersionUID = 1716882111650295066L;
	
	private String codigo;
	private String nombre;
	private Double precioBase;
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
	public Double getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	
	
}
