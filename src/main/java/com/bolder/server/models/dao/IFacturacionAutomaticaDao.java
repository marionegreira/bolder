package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolder.server.models.entity.FacturaVenta;
import com.bolder.server.models.entity.FacturacionAutomatica;
import com.bolder.server.models.entity.PedidoVenta;



public interface IFacturacionAutomaticaDao extends JpaRepository<FacturacionAutomatica, Long>{
	
	
	@Query(value="SELECT fa FROM FacturacionAutomatica fa "
			+ "LEFT JOIN FETCH fa.facturacionAutomaticaLineas "	
			+ "LEFT JOIN FETCH fa.cliente "
			+ "WHERE fa.id=:Id ")
	public FacturacionAutomatica findByIdBig(Long Id);
	
}
