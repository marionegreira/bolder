package com.bolder.server.models.service;

import java.util.List;

import com.bolder.server.models.dto.FacturacionAutomaticaDtoBig;
import com.bolder.server.models.dto.FacturacionAutomaticaEditDto;
import com.bolder.server.models.entity.FacturacionAutomatica;

public interface IFacturacionAutomaticaService {

	public List<FacturacionAutomaticaEditDto> findAllToList() throws Exception;
	public FacturacionAutomaticaDtoBig findOne(Long id) throws Exception;
	public FacturacionAutomaticaDtoBig save(FacturacionAutomaticaEditDto facturacionAutomaticaDto) throws Exception;
	public FacturacionAutomatica patch(FacturacionAutomaticaEditDto dto, Long id) throws Exception;
	public void delete(Long id) throws Exception;

}
