package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.bolder.server.util.NotPacheable;

@Entity
@Table(	name="Usuario",
   		indexes = {@Index(columnList = "username")}
		)
@DynamicUpdate
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "usuario",fetch=FetchType.LAZY)
	private List<PedidoVenta> docventa;

	@Column(columnDefinition="VARCHAR(200) DEFAULT ''",nullable = false,unique=true)
	private String username;
	@JsonProperty(access = Access.WRITE_ONLY)
	@NotPacheable
	@Column(columnDefinition="VARCHAR(200) DEFAULT ''",nullable = false)
	private String password;
	@Column(columnDefinition="VARCHAR(200) DEFAULT ''",nullable = false)
	private String nombre;
	@Column(columnDefinition="VARCHAR(200) DEFAULT ''",nullable = false)
	private String apellido;
	
	@Column(columnDefinition="VARCHAR(200) DEFAULT ''",nullable = false)
	@Email(message="introduzca un email valido")
	private String email;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(columnDefinition="INT DEFAULT 0",nullable = false)
	private RoleType role;

	private Boolean enabled=true;
	
	public Usuario(Long id) {
		// TODO Auto-generated constructor stub
	}
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<PedidoVenta> getDocVentaCab() {
		return docventa;
	}
	public void setDocVentaCab(List<PedidoVenta> docventa) {
		this.docventa = docventa;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public RoleType getRole() {
		return role;
	}
	public void setRole(RoleType role) {
		this.role = role;
	}

	//@JsonFormat(shape = JsonFormat.Shape.NUMBER)
	public enum RoleType{
		USER,ADMIN,SUPER

	}
	
    @PrePersist
    public void prePersist() {

    }
    
    @PreUpdate
    public void preUpdate() {

    }
	
}
