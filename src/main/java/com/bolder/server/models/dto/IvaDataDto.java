package com.bolder.server.models.dto;

import java.util.List;
public class IvaDataDto {
	
	List<IvaNegocioDto> ivasNegocio;
	List<IvaProductoDto> ivasProducto;
	List<IvaConfiguracionDto> ivasConfiguracion;
	
	public List<IvaNegocioDto> getIvasNegocio() {
		return ivasNegocio;
	}
	public void setIvasNegocio(List<IvaNegocioDto> ivasNegocio) {
		this.ivasNegocio = ivasNegocio;
	}
	public List<IvaProductoDto> getIvasProducto() {
		return ivasProducto;
	}
	public void setIvasProducto(List<IvaProductoDto> ivasProducto) {
		this.ivasProducto = ivasProducto;
	}
	public List<IvaConfiguracionDto> getIvasConfiguracion() {
		return ivasConfiguracion;
	}
	public void setIvasConfiguracion(List<IvaConfiguracionDto> ivasConfiguracion) {
		this.ivasConfiguracion = ivasConfiguracion;
	}
	
	
	
}
