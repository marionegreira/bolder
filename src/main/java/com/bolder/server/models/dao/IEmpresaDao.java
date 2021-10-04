package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.Empresa;



public interface IEmpresaDao extends JpaRepository<Empresa, Long>{
	@Query(value="SELECT c.* FROM empresa c LIMIT 1",nativeQuery = true)
	public Empresa findFirst();
}
