package com.bolder.server.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bolder.server.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long> {


}
