package com.bolder.server.models.service;

import java.util.List;

import com.bolder.server.models.dto.SerieRegistroEditDto;
import com.bolder.server.models.entity.SerieRegistro;

public interface ISerieRegistroService {

	public SerieRegistroEditDto save(SerieRegistro serieRegistro) throws Exception;
	public void delete(Long id) throws Exception;
	public SerieRegistro patch(SerieRegistroEditDto dto, Long id) throws Exception;
	public List<SerieRegistroEditDto> findAllToList() throws Exception;
	public List<SerieRegistroEditDto> findAllDropdown() throws Exception;
	public SerieRegistroEditDto findOne(Long id) throws Exception;
}
