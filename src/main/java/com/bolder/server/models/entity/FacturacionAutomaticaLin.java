package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.hibernate.HibernateException;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;


@Entity
@DynamicUpdate
public class FacturacionAutomaticaLin implements Serializable{

	private static final long serialVersionUID = -9203024847731905307L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "facturacion_automatica_id",updatable=false)
	private FacturacionAutomatica facturacionAutomatica;

	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "articulo_codigo") 
	private Articulo articulo;
	
	@Lob @Type(type = "org.hibernate.type.TextType")
	private String descripcion;
	private Double cantidad;
	private Double precio;
	private Double iva=0D;
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
	public FacturacionAutomatica getFacturacionAutomatica() {
		return facturacionAutomatica;
	}
	public void setFacturacionAutomatica(FacturacionAutomatica facturacionAutomatica) {
		this.facturacionAutomatica = facturacionAutomatica;
	}
	public Articulo getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
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


