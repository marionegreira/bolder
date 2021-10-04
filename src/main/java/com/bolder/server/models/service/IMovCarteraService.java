package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.ArticuloDropdownDto;
import com.bolder.server.models.dto.ArticuloDto;
import com.bolder.server.models.dto.ArticuloEditDto;
import com.bolder.server.models.dto.ArticuloListDto;
import com.bolder.server.models.dto.MovCarteraEditDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.MovCartera;

public interface IMovCarteraService {

	public List<MovCarteraEditDto> findCobrosPendientes() throws Exception;
	public List<MovCarteraEditDto> findCobrosRegistrado() throws Exception;
	public List<MovCarteraEditDto> registra(MovCarteraEditDto movCarteraDto, Long cobroId) throws Exception;

}
