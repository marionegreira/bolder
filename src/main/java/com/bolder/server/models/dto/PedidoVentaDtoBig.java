package com.bolder.server.models.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.bolder.server.models.entity.SerieRegistro;

public class PedidoVentaDtoBig implements Serializable {

	private static final long serialVersionUID = 8425303331740307575L;
	
	
	private Long id;
	private String no;
	private Long clienteId;
	private String clienteIvaNegocioCodigo;
	private Long serieRegistroFacturaId;
	private String serieRegistroFacturaSerie;
	private List<PedidoVentaLinDto> pedidoVentaLineas=new ArrayList();
	private String cliNombre;
	private String cliDireccion;
	private String cliPoblacion;
	private String cliCp;
	private String cliProvincia;
	private String cliNif;
	private Date fecha;
	private Date fechaFactura;
	private Double importeBruto;
	private Double importeNeto;
	private Double importeIva;
	private Double importeRetencion;
	private Date fechaCreacion;
	private Date fechaModificacion;

	
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
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	public String getClienteIvaNegocioCodigo() {
		return clienteIvaNegocioCodigo;
	}
	public void setClienteIvaNegocioCodigo(String clienteIvaNegocioCodigo) {
		this.clienteIvaNegocioCodigo = clienteIvaNegocioCodigo;
	}
	public Long getSerieRegistroFacturaId() {
		return serieRegistroFacturaId;
	}
	public void setSerieRegistroFacturaId(Long serieRegistroFacturaId) {
		this.serieRegistroFacturaId = serieRegistroFacturaId;
	}
	public String getSerieRegistroFacturaSerie() {
		return serieRegistroFacturaSerie;
	}
	public void setSerieRegistroFacturaSerie(String serieRegistroFacturaSerie) {
		this.serieRegistroFacturaSerie = serieRegistroFacturaSerie;
	}
	public List<PedidoVentaLinDto> getPedidoVentaLineas() {
		return pedidoVentaLineas;
	}
	public void setPedidoVentaLineas(List<PedidoVentaLinDto> pedidoVentaLineas) {
		this.pedidoVentaLineas = pedidoVentaLineas;
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
	public Date getFechaFactura() {
		return fechaFactura;
	}
	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
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
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	
	

}
