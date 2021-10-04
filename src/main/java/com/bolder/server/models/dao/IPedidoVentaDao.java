package com.bolder.server.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolder.server.models.entity.PedidoVenta;



public interface IPedidoVentaDao extends JpaRepository<PedidoVenta, Long>{
	
	
	@Query(value="SELECT pvc FROM PedidoVenta pvc "
			+ "LEFT JOIN FETCH pvc.pedidoVentaLineas "			
			+ "LEFT JOIN FETCH pvc.cliente "
			+ "LEFT JOIN FETCH pvc.usuario "
			+ "WHERE pvc.id=:id ")
	public Optional<PedidoVenta> findByIdBig(Long id);
	/*
	@Query(value="SELECT c FROM DocVentaCab c "
			+ "LEFT JOIN FETCH c.centro "			
			+ "LEFT JOIN FETCH c.cliente "
			+ "ORDER BY c.fechaCreacion DESC ")
	public List<PedidoVentaCab> findList();
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.usuario "
			+ "LEFT JOIN FETCH c.dispositivo "
			+ "LEFT JOIN FETCH c.centro "
			+ "LEFT JOIN FETCH c.attaches "
			+ "LEFT JOIN FETCH c.maquinas "
			+ "LEFT JOIN FETCH c.imputables "
			+ "LEFT JOIN FETCH c.notas "			
			+ "ORDER BY c.fechaCreacion DESC ")
	public List<PedidoVentaCab> findListBig();
	

	@Query(value="SELECT c FROM DocVentaCab c "
			+ " WHERE c.cliente.id=:clienteId AND c.id=:id "
			+ "ORDER BY c.fechaCreacion DESC")
	public PedidoVentaCab mobfindById(Long id,Long clienteId);
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.usuario "
			+ "LEFT JOIN FETCH c.dispositivo "
			+ "LEFT JOIN FETCH c.centro "
			+ "LEFT JOIN FETCH c.attaches "
			+ "LEFT JOIN FETCH c.maquinas cm "
			+ "LEFT JOIN FETCH c.imputables "
			+ "LEFT JOIN FETCH c.notas "
			+ "LEFT JOIN FETCH c.proyecto "
			+ "LEFT JOIN FETCH cm.maquina "
			+ "WHERE c.id=:idDocVentaCab AND c.cliente.id=:clienteId "
			+ "ORDER BY c.fechaCreacion DESC")
	public PedidoVentaCab mobfindOneBig(Long idDocVentaCab,Long clienteId);
	
	
	@Query(value="SELECT COUNT(*) FROM docventa i LEFT JOIN  attach a on a.docventa_id=i.id WHERE i.cliente_id=:clienteId AND i.id=:id AND a.tipo_attach=1",nativeQuery = true)
	public Long countAttachFirma(Long id,Long clienteId);
	@Query(value="SELECT COUNT(*) FROM docventa i LEFT JOIN  attach a on a.docventa_id=i.id WHERE i.cliente_id=:clienteId AND i.id=:id AND a.tipo_attach=2",nativeQuery = true)
	public Long countAttachConformidad(Long id,Long clienteId);	
	@Query(value="SELECT COUNT(*) FROM docventa i  WHERE i.cliente_id=:clienteId",nativeQuery = true)
	public Long count(Long clienteId);
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.usuario "
			+ "LEFT JOIN FETCH c.dispositivo "
			+ "LEFT JOIN FETCH c.centro "
			+ "LEFT JOIN FETCH c.attaches "
			+ "LEFT JOIN FETCH c.maquinas "
			+ "LEFT JOIN FETCH c.imputables "
			+ "LEFT JOIN FETCH c.notas "
			+ "WHERE c.id=:idDocVentaCab "
			+ "ORDER BY c.fechaCreacion DESC")
	public PedidoVentaCab findOneBig(Long idDocVentaCab);
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.usuario "
			+ "LEFT JOIN FETCH c.dispositivo "
			+ "LEFT JOIN FETCH c.centro "
			+ "LEFT JOIN FETCH c.attaches "
			+ "LEFT JOIN FETCH c.maquinas "
			+ "LEFT JOIN FETCH c.imputables "
			+ "LEFT JOIN FETCH c.notas "
			+ "WHERE c.idGestion=:idGestion "
			+ "ORDER BY c.fechaCreacion DESC")
	public PedidoVentaCab findOneBigByIdGestion(String idGestion);
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.usuario "
			+ "LEFT JOIN FETCH c.dispositivo "
			+ "LEFT JOIN FETCH c.centro "
			+ "LEFT JOIN FETCH c.attaches "
			+ "LEFT JOIN FETCH c.maquinas "
			+ "LEFT JOIN FETCH c.imputables "
			+ "LEFT JOIN FETCH c.notas "
			+ "WHERE c.id=:idDocVentaCab AND c.cliente.id=:clienteId "
			+ "ORDER BY c.fechaCreacion DESC")
	public PedidoVentaCab mobfindByIdBig(Long idDocVentaCab,Long clienteId);
	
	@Modifying
	@Query("update DocVentaCab c set c.fechaModificacion = NOW() where c.id = :docventaId")
	void updateFechaModif(Long docventaId);
	
	@Modifying
	@Query("update DocVentaCab c set c.confirmado = true where c.id = :docventaId")
	void checkDocVentaCab(Long docventaId);
	
	@Query(value="SELECT c.id as idDocVentaCab,null as idDocVentaCabCerrado,c.fecha_modificacion as fechaModificacion,c.fecha_inicio as fechaInicio,NULL as fechaFinalizacion, id_gestion as idGestion FROM docventa c " + 
			"WHERE c.fecha_modificacion > :date " + 
			"UNION " + 
			"SELECT d.docventa_id as idDocVentaCab, d.id as idDocVentaCabCerrado,null as fechaModificacion, null as fechaInicio, d.fecha_fin as fechaFinalizacion, d.id_gestion as idGestion FROM documento_cerrado d " + 
			"WHERE d.fecha_creacion > :date",nativeQuery = true)
	public List<IStatusDocVentaCabsDto> DocVentaCabsStatus(Date date);
	
	@Query(value="SELECT c FROM DocVentaCab c LEFT JOIN FETCH c.cliente "
			+ "LEFT JOIN FETCH c.centro "
			+ "WHERE c.fechaInicio is not null")
	public List<PedidoVentaCab> getDocVentaCabsEnProceso();
	*/

}
