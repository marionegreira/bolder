package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.IvaNegocioDto;
import com.bolder.server.models.entity.IvaNegocio;

public interface IIvaNegocioService {

	public IvaNegocioDto save(IvaNegocio ivaNegocio) throws Exception;
	public void delete(String id) throws Exception;
	public IvaNegocio patch(IvaNegocio ivaNegocio) throws Exception;
	public List<IvaNegocioDto> findAllToList() throws Exception;
}
