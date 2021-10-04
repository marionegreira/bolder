package com.bolder.server.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.models.entity.IvaProducto;

public interface IIvaProductoDao extends JpaRepository<IvaProducto, String> {


}
