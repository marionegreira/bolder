package com.bolder.server.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.IvaNegocio;

public interface IIvaNegocioDao extends JpaRepository<IvaNegocio, String> {


}
