package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bolder.server.models.entity.Articulo;

public interface IArticuloDao extends JpaRepository<Articulo, String> {


}
