package com.bolder.server.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.PedidoVentaLin;

public interface IPedidoVentaLinDao extends JpaRepository<PedidoVentaLin, Long> {

	@Query(value="SELECT pv FROM PedidoVentaLin pv "
			+ "WHERE pv.pedidoVenta.id=:id ")
	public List<PedidoVentaLin> findByPedidoId(Long id);
	
	
	@Modifying
	@Query("UPDATE PedidoVentaLin SET orden = :orden where id = :lineaId")
	void updateOrden(Long lineaId, Long orden);
	
}
