package com.bolder.server.models.dto;

public class MovCarteraCobrarDto {
	
	private String facturaVentaNo;
	private String fecha;
	private Double importe;
	
	public String getFacturaVentaNo() {
		return facturaVentaNo;
	}
	public void setFacturaVentaNo(String facturaVentaNo) {
		this.facturaVentaNo = facturaVentaNo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
		

}
