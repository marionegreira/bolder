package com.bolder.server.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;

public interface IIvaConfiguracionDao extends JpaRepository<IvaConfiguracion, IvaConfiguracionId> {


}
