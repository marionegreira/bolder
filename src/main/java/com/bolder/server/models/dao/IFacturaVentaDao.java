package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolder.server.models.entity.FacturaVenta;
import com.bolder.server.models.entity.PedidoVenta;



public interface IFacturaVentaDao extends JpaRepository<FacturaVenta, Long>{
	
	
	@Query(value="SELECT fvc FROM FacturaVenta fvc "
			+ "LEFT JOIN FETCH fvc.facturaVentaLineas "			
			+ "LEFT JOIN FETCH fvc.movimientosCartera "		
			+ "LEFT JOIN FETCH fvc.cliente "
			+ "LEFT JOIN FETCH fvc.usuario "
			+ "WHERE fvc.id=:Id ")
	public FacturaVenta findByIdBig(Long Id);
	
}
