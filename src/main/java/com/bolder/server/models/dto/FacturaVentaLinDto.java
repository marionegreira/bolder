package com.bolder.server.models.dto;

import java.util.Date;

public class FacturaVentaLinDto {
	private Long id;
	private Long IdFacturaId;
	private String IdFacturaVentaNo;
	
	private String articuloCodigo;
	private String descripcion;
	private Double cantidad;
	private Double precio;
	private Double iva;
	private Double retencion;
	private Double importeBruto;
	private Double importeNeto;
	private Double importeIva;
	private Double importeRetencion;
	private Long orden;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdFacturaId() {
		return IdFacturaId;
	}
	public void setIdFacturaId(Long idFacturaId) {
		IdFacturaId = idFacturaId;
	}
	public String getIdFacturaVentaNo() {
		return IdFacturaVentaNo;
	}
	public void setIdFacturaVentaNo(String idFacturaVentaNo) {
		IdFacturaVentaNo = idFacturaVentaNo;
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

	public Double getImporteBruto() {
		return importeBruto;
	}
	public void setImporteBruto(Double importeBruto) {
		this.importeBruto = importeBruto;
	}
	public Double getImporteNeto() {
		return importeNeto;
	}
	public void setImporteNeto(Double importeNeto) {
		this.importeNeto = importeNeto;
	}
	public Double getImporteIva() {
		return importeIva;
	}
	public void setImporteIva(Double importeIva) {
		this.importeIva = importeIva;
	}
	public Double getImporteRetencion() {
		return importeRetencion;
	}
	public void setImporteRetencion(Double importeRetencion) {
		this.importeRetencion = importeRetencion;
	}
	public Long getOrden() {
		return orden;
	}
	public void setOrden(Long orden) {
		this.orden = orden;
	}

}
