package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.FacturacionAutomaticaLin;


public interface IFacturacionAutomaticaLinDao extends JpaRepository<FacturacionAutomaticaLin, Long> {

	@Modifying
	@Query("UPDATE FacturacionAutomaticaLin SET orden = :orden where id = :lineaId")
	void updateOrden(Long lineaId, Long orden);
}
