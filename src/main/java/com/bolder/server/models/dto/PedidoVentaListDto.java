package com.bolder.server.models.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import com.bolder.server.models.entity.SerieRegistro;

public class PedidoVentaListDto implements Serializable{

	private static final long serialVersionUID = -5175314499186821895L;
	
	private Long id;
	private String no;
	private String cliNombre;
	private String cliDireccion;
	private String cliPoblacion;
	private String cliCp;
	private String cliProvincia;
	private String cliNif;
	private Date fecha;
	private Double importeNeto;


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCliNombre() {
		return cliNombre;
	}
	public void setCliNombre(String cliNombre) {
		this.cliNombre = cliNombre;
	}
	public String getCliDireccion() {
		return cliDireccion;
	}
	public void setCliDireccion(String cliDireccion) {
		this.cliDireccion = cliDireccion;
	}
	public String getCliPoblacion() {
		return cliPoblacion;
	}
	public void setCliPoblacion(String cliPoblacion) {
		this.cliPoblacion = cliPoblacion;
	}
	public String getCliCp() {
		return cliCp;
	}
	public void setCliCp(String cliCp) {
		this.cliCp = cliCp;
	}
	public String getCliProvincia() {
		return cliProvincia;
	}
	public void setCliProvincia(String cliProvincia) {
		this.cliProvincia = cliProvincia;
	}
	public String getCliNif() {
		return cliNif;
	}
	public void setCliNif(String cliNif) {
		this.cliNif = cliNif;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getImporteNeto() {
		return importeNeto;
	}
	public void setImporteNeto(Double importeNeto) {
		this.importeNeto = importeNeto;
	}

	
	
	
}
