package com.bolder.server.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

@Entity
@DynamicUpdate
public class VersionAndroid implements Serializable {

	private static final long serialVersionUID = 620442990930868467L;
	
	public VersionAndroid () {}
	public VersionAndroid (Long id) {
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

	@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "version_back_id")
	private VersionBack versionBack;

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
	public VersionBack getVersionBack() {
		return versionBack;
	}
	public void setVersionBack(VersionBack versionBack) {
		this.versionBack = versionBack;
	}
	
	
}
