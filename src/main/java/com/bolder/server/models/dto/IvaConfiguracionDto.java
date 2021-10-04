package com.bolder.server.models.dto;




public class IvaConfiguracionDto {
	private String ivaNegocioCodigo;
	private String ivaProductoCodigo;
	private Double iva;
	private Double retencion;

	
	public String getIvaNegocioCodigo() {
		return ivaNegocioCodigo;
	}
	public void setIvaNegocioCodigo(String ivaNegocioCodigo) {
		this.ivaNegocioCodigo = ivaNegocioCodigo;
	}
	public String getIvaProductoCodigo() {
		return ivaProductoCodigo;
	}
	public void setIvaProductoCodigo(String ivaProductoCodigo) {
		this.ivaProductoCodigo = ivaProductoCodigo;
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

	
}
