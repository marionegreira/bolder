package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
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
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@Table(indexes = {
		@Index(columnList = "nif"),
		@Index(columnList = "nombre")
} )
@DynamicUpdate

public class Cliente implements Serializable{
	private static final long serialVersionUID = -8431524588678157122L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "cliente",fetch=FetchType.LAZY)
	private List<PedidoVenta> docVentaCab;
	
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(200) DEFAULT ''")
	private String nombre;

	@Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String direccion;
	
	private String poblacion;

	private String cp;
	
	private String provincia;
	private String telefono;	
	
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(20) DEFAULT ''") 
	private String nif;
	private String emailInfo;
	private String emailEnvioDocs;

	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String observaciones;
	
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "iva_negocio_codigo")
	private IvaNegocio ivaNegocio;

	private Boolean enabled=true;
	

	
	public Cliente() {
	}
	
	public Cliente(Long id) {
		this.id=id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<PedidoVenta> getDocVentaCab() {
		return docVentaCab;
	}

	public void setDocVentaCab(List<PedidoVenta> docVentaCab) {
		this.docVentaCab = docVentaCab;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEmailInfo() {
		return emailInfo;
	}

	public void setEmailInfo(String emailInfo) {
		this.emailInfo = emailInfo;
	}

	public IvaNegocio getIvaNegocio() {
		return ivaNegocio;
	}

	public void setIvaNegocio(IvaNegocio ivaNegocio) {
		this.ivaNegocio = ivaNegocio;
	}

	public String getEmailEnvioDocs() {
		return emailEnvioDocs;
	}

	public void setEmailEnvioDocs(String emailEnvioDocs) {
		this.emailEnvioDocs = emailEnvioDocs;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@PrePersist
    public void prePersist() {

    }
    
    @PreUpdate
    public void preUpdate() {

    }
    


}
