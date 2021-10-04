package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@DynamicUpdate
public class PedidoVenta implements Serializable {
	private static final long serialVersionUID = 2977849138088894761L;
	
	public PedidoVenta() {}
	public PedidoVenta(Long id) {
		this.id	= id;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String no;

	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "cliente_id") 
	private Cliente cliente;
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "serie_registro_factura_id") 
	private SerieRegistro serieRegistroFactura;
	
	@OneToMany(mappedBy = "pedidoVenta",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<PedidoVentaLin> pedidoVentaLineas;
	
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(200) DEFAULT ''")
	private String cliNombre;

	@Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String cliDireccion;
	
	@Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String cliPoblacion;
	
	@Column(nullable = false, columnDefinition="VARCHAR(10) DEFAULT ''")
	private String cliCp;
	
	@Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String cliProvincia;

	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(20) DEFAULT ''") 
	private String cliNif;
	
	@NotNull
	private Date fecha;
	@NotNull
	private Date fechaFactura;
	
	private Double importeBruto;
	private Double importeNeto;
	private Double importeIva;
	private Double importeRetencion;
	
	
	@CreationTimestamp
	private Date fechaCreacion;
	@UpdateTimestamp
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SerieRegistro getSerieRegistroFactura() {
		return serieRegistroFactura;
	}
	public void setSerieRegistroFactura(SerieRegistro serieRegistroFactura) {
		this.serieRegistroFactura = serieRegistroFactura;
	}
	public Set<PedidoVentaLin> getPedidoVentaLineas() {
		return pedidoVentaLineas;
	}
	public void setPedidoVentaLineas(Set<PedidoVentaLin> pedidoVentaLineas) {
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
	
	@PrePersist
	void preInsert() {
		   if (importeBruto == null) importeBruto=0D;
		   if (importeNeto == null) importeNeto=0D;
		   if (importeIva == null) importeIva=0D;
		   if (importeRetencion == null) importeRetencion=0D;
		}
}
