package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.IvaConfiguracionDto;
import com.bolder.server.models.dto.IvaDataDto;
import com.bolder.server.models.entity.IvaConfiguracion;

public interface IIvaConfiguracionService {

	public IvaConfiguracionDto save(IvaConfiguracionDto ivaConfiguracionDto) throws Exception;
	public void delete(String ivaNegocio, String ivaProducto) throws Exception;
	public IvaConfiguracion patch(IvaConfiguracionDto ivaConfiguracionDto) throws Exception;
	public List<IvaConfiguracionDto> findAllToList() throws Exception;
	public IvaDataDto getIvaData() throws Exception;


}
