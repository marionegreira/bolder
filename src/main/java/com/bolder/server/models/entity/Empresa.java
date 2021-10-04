package com.bolder.server.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

@Entity
public class Empresa implements Serializable{

	private static final long serialVersionUID = 7307659676618194032L;

	@Id
	private Long id;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(255) DEFAULT ''")
	private String url;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String nombre;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String direccion;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String poblacion;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(10) DEFAULT ''")
	private String cp;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(100) DEFAULT ''")
	private String provincia;	
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(20) DEFAULT ''")
	private String telefono;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(50) DEFAULT ''")
	@Email
	private String email;
	private String www;
	@NotNull @Column(nullable = false, columnDefinition="VARCHAR(20) DEFAULT ''")
	private String cif;	
	
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "serie_factura_codigo") 
	private SerieRegistro serieFactura;
	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "serie_pedido_codigo") 
	private SerieRegistro seriePedido;

	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String smtpConfiguracion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

	public void setDireccion(String fireccion) {
		this.direccion = fireccion;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWww() {
		return www;
	}

	public void setWww(String www) {
		this.www = www;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public SerieRegistro getSerieFactura() {
		return serieFactura;
	}

	public void setSerieFactura(SerieRegistro serieFactura) {
		this.serieFactura = serieFactura;
	}
	

	public SerieRegistro getSeriePedido() {
		return seriePedido;
	}
	public void setSeriePedido(SerieRegistro seriePedido) {
		this.seriePedido = seriePedido;
	}
	public String getSmtpConfiguracion() {
		return smtpConfiguracion;
	}

	public void setSmtpConfiguracion(String smtpConfiguracion) {
		this.smtpConfiguracion = smtpConfiguracion;
	}


}
