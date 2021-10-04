package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.FacturacionAutomaticaLinEditDto;

public interface IFacturacionAutomaticaLinService {

	public FacturacionAutomaticaLinEditDto save(FacturacionAutomaticaLinEditDto pedidoVentaLinDto) throws Exception;
	public void delete(Long linId) throws Exception;
	public FacturacionAutomaticaLinEditDto patch(FacturacionAutomaticaLinEditDto dto) throws Exception;
	public List<FacturacionAutomaticaLinEditDto> findAllToList() throws Exception;
	public FacturacionAutomaticaLinEditDto findOne(Long linId) throws Exception;
	public void reordenar(List<FacturacionAutomaticaLinEditDto> pedidoVentaLinDtoList) throws Exception;
}
