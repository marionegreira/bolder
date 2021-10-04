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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@DynamicUpdate
public class FacturaVenta implements Serializable {

	private static final long serialVersionUID = 8678307309757279151L;


	public FacturaVenta() {}
	public FacturaVenta(Long id) {
		this.id= id;
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

	@OneToMany(mappedBy = "facturaVenta",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<FacturaVentaLin> facturaVentaLineas;
	
	@OneToMany(mappedBy = "facturaVenta",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<MovCartera> movimientosCartera;
	
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
	private Double importeBruto=0D;
	private Double importeNeto=0D;
	private Double importeIva=0D;
	private Double importeRetencion=0D;
	private Double importePendienteCobro;
	
	
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

	public Set<FacturaVentaLin> getFacturaVentaLineas() {
		return facturaVentaLineas;
	}
	public void setFacturaVentaLineas(Set<FacturaVentaLin> facturaVentaLineas) {
		this.facturaVentaLineas = facturaVentaLineas;
	}
	
	public Set<MovCartera> getMovimientosCartera() {
		return movimientosCartera;
	}
	public void setMovimientosCartera(Set<MovCartera> movimientosCartera) {
		this.movimientosCartera = movimientosCartera;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Double getImportePendienteCobro() {
		return importePendienteCobro;
	}
	public void setImportePendienteCobro(Double importePendienteCobro) {
		this.importePendienteCobro = importePendienteCobro;
	}
	@PrePersist
	void preInsert() {
	   if (importeBruto == null) importeBruto=0D;
	   if (importeNeto == null) importeNeto=0D;
	   if (importeIva == null) importeIva=0D;
	   if (importeRetencion == null) importeRetencion=0D;
	}
	

}
