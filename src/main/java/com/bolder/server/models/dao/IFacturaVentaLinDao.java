package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.FacturaVentaLin;


public interface IFacturaVentaLinDao extends JpaRepository<FacturaVentaLin, Long> {


}
