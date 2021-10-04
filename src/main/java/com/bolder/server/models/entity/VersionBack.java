package com.bolder.server.models.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicUpdate
public class VersionBack implements Serializable {

	private static final long serialVersionUID = 620442990930868467L;
	
	public VersionBack () {}
	public VersionBack (Long id) {
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	private String descripcion;
	
	private String filePath;
	private Boolean lanzado;
	
	@OneToMany(mappedBy = "versionBack",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<VersionFront> versionFront;
	@OneToMany(mappedBy = "versionBack",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<VersionFront> versionAndroid;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Boolean getLanzado() {
		return lanzado;
	}
	public void setLanzado(Boolean lanzado) {
		this.lanzado = lanzado;
	}
	public Set<VersionFront> getVersionFront() {
		return versionFront;
	}
	public void setVersionFront(Set<VersionFront> versionFront) {
		this.versionFront = versionFront;
	}
	public Set<VersionFront> getVersionAndroid() {
		return versionAndroid;
	}
	public void setVersionAndroid(Set<VersionFront> versionAndroid) {
		this.versionAndroid = versionAndroid;
	}
	
	
	

}
