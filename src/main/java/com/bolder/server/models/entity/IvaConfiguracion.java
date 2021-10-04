package com.bolder.server.models.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicUpdate
public class IvaConfiguracion implements Serializable{

	private static final long serialVersionUID = 855738627273212214L;
	
	@EmbeddedId
	public IvaConfiguracionId ivaConfiguracionId;

	private Double iva;
	private Double retencion;

	
	public Double getIva() {
		return iva;
	}
	public IvaConfiguracionId getIvaConfiguracionId() {
		return ivaConfiguracionId;
	}
	public void setIvaConfiguracionId(IvaConfiguracionId ivaConfiguracionId) {
		this.ivaConfiguracionId = ivaConfiguracionId;
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
	
	
	@Embeddable
	public static class IvaConfiguracionId implements Serializable{

		private static final long serialVersionUID = -5902643075715288859L;
		
		public IvaConfiguracionId(){}
		public IvaConfiguracionId(IvaNegocio ivaNegocio,IvaProducto ivaProducto) {
			this.ivaNegocio = ivaNegocio;
			this.ivaProducto = ivaProducto;
		}
		public IvaConfiguracionId(String ivaNegocio,String ivaProducto) {
			this.ivaNegocio = new IvaNegocio(ivaNegocio);
			this.ivaProducto = new IvaProducto(ivaProducto);
		}
		
		@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "iva_negocio_codigo")
		private IvaNegocio ivaNegocio;
		@ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name = "iva_producto_codigo")
		private IvaProducto ivaProducto;
		
		
		public IvaNegocio getIvaNegocio() {
			return ivaNegocio;
		}
		public void setIvaNegocio(IvaNegocio ivaNegocio) {
			this.ivaNegocio = ivaNegocio;
		}
		public IvaProducto getIvaProducto() {
			return ivaProducto;
		}
		public void setIvaProducto(IvaProducto ivaProducto) {
			this.ivaProducto = ivaProducto;
		}
		
		
		

	}
	
	

}
