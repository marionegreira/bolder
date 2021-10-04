package com.bolder.server.models.dto;

import java.util.Date;
import com.bolder.server.models.entity.MovCartera.TipoMovCartera;

public class MovCarteraEditDto {
	private Long id;
	private String facturaVentaNo;
	private String facturaVentaCliNombre;
	private Date facturaVentaFecha;
	private TipoMovCartera tipoMovCartera;
	private Double importePendiente;
	private Double importeMovimiento;
	private Date fechaMovimiento;
	private Boolean registrado;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFacturaVentaNo() {
		return facturaVentaNo;
	}
	public void setFacturaVentaNo(String facturaVentaNo) {
		this.facturaVentaNo = facturaVentaNo;
	}
	public String getFacturaVentaCliNombre() {
		return facturaVentaCliNombre;
	}
	public void setFacturaVentaCliNombre(String facturaVentaCliNombre) {
		this.facturaVentaCliNombre = facturaVentaCliNombre;
	}
	public Date getFacturaVentaFecha() {
		return facturaVentaFecha;
	}
	public void setFacturaVentaFecha(Date facturaVentaFecha) {
		this.facturaVentaFecha = facturaVentaFecha;
	}
	public TipoMovCartera getTipoMovCartera() {
		return tipoMovCartera;
	}
	public void setTipoMovCartera(TipoMovCartera tipoMovCartera) {
		this.tipoMovCartera = tipoMovCartera;
	}
	public Double getImportePendiente() {
		return importePendiente;
	}
	public void setImportePendiente(Double importePendiente) {
		this.importePendiente = importePendiente;
	}
	public Double getImporteMovimiento() {
		return importeMovimiento;
	}
	public void setImporteMovimiento(Double importeMovimiento) {
		this.importeMovimiento = importeMovimiento;
	}
	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}
	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}
	public Boolean getRegistrado() {
		return registrado;
	}
	public void setRegistrado(Boolean registrado) {
		this.registrado = registrado;
	}
	
	

}
