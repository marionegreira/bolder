package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.IvaProductoDto;
import com.bolder.server.models.entity.IvaProducto;

public interface IIvaProductoService {

	public IvaProductoDto save(IvaProducto ivaProducto) throws Exception;
	public void delete(String id) throws Exception;
	public IvaProducto patch(IvaProducto ivaProducto) throws Exception;
	public List<IvaProductoDto> findAllToList() throws Exception;
}
