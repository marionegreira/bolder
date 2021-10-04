package com.bolder.server.models.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class MovCartera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "factura_venta_id",updatable=false)
	private FacturaVenta facturaVenta;
	private TipoMovCartera tipoMovCartera;
	private Double importePendiente;
	private Double importeMovimiento;
	private Date fechaMovimiento;
	private Boolean registrado=false;
	
	private Boolean movimientoAnulado=false;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FacturaVenta getFacturaVenta() {
		return facturaVenta;
	}

	public void setFacturaVenta(FacturaVenta facturaVenta) {
		this.facturaVenta = facturaVenta;
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

	public Boolean getMovimientoAnulado() {
		return movimientoAnulado;
	}

	public void setMovimientoAnulado(Boolean movimientoAnulado) {
		this.movimientoAnulado = movimientoAnulado;
	}

	public enum TipoMovCartera{
		COBRO,
		PAGO
	}
}
