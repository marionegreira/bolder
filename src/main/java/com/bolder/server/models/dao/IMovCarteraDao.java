package com.bolder.server.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.MovCartera;

public interface IMovCarteraDao extends JpaRepository<MovCartera, Long> {

	@Query(value="SELECT mc FROM MovCartera mc "
			+ "LEFT JOIN FETCH mc.facturaVenta "			
			+ "WHERE mc.importePendiente<>0 AND registrado=false AND mc.tipoMovCartera=0")
	public List<MovCartera> findCobrosPendientes();
	
	@Query(value="SELECT mc FROM MovCartera mc "
			+ "LEFT JOIN FETCH mc.facturaVenta "			
			+ "WHERE registrado=true AND mc.tipoMovCartera=0")
	public List<MovCartera> findCobrosRegistrados();
	
	@Query(value="SELECT mc FROM MovCartera mc "
			+ "LEFT JOIN FETCH mc.facturaVenta "			
			+ "WHERE mc.facturaVenta.id=:facturaId AND mc.tipoMovCartera=0")
	public List<MovCartera> findCobrosByFactura(Long facturaId);
}
