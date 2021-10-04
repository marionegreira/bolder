package com.bolder.server.models.dto;

import java.util.Date;

public class PedidoVentaLinListDto {
	
	private Long id;
	private Long pedidoVentaId;
	private String pedidoVentaNo;
	private String articuloCodigo;
	private String descripcion;
	private Double cantidad;
	private Double precio;
	private Double iva;
	private Double retencion;
	private Long orden;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getPedidoVentaId() {
		return pedidoVentaId;
	}
	public void setPedidoVentaId(Long pedidoVentaId) {
		this.pedidoVentaId = pedidoVentaId;
	}
	public String getPedidoVentaNo() {
		return pedidoVentaNo;
	}
	public void setPedidoVentaNo(String pedidoVentaNo) {
		this.pedidoVentaNo = pedidoVentaNo;
	}
	public String getArticuloCodigo() {
		return articuloCodigo;
	}
	public void setArticuloCodigo(String articuloCodigo) {
		this.articuloCodigo = articuloCodigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getRetencion() {
		return retencion;
	}
	public void setRetencion(Double retencion) {
		this.retencion = retencion;
	}
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}
	
	
}
