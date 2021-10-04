package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.ArticuloDropdownDto;
import com.bolder.server.models.dto.ArticuloDto;
import com.bolder.server.models.dto.ArticuloEditDto;
import com.bolder.server.models.dto.ArticuloIvaDto;
import com.bolder.server.models.dto.ArticuloListDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.IvaNegocio;

public interface IArticuloService {

	public ArticuloDto save(Articulo articulo) throws Exception;
	public void delete(String codigo) throws Exception;
	public Articulo patch(ArticuloEditDto dto, String codigo) throws Exception;
	public List<ArticuloListDto> findAllToList() throws Exception;
	public List<ArticuloDropdownDto> findAllDropdown() throws Exception;
	public ArticuloDto findOne(String codigo) throws Exception;
	public ArticuloIvaDto findOneIva(String codigo, IvaNegocio ivaNegocio) throws Exception;
	


}
