package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.PedidoVentaLinEditDto;
import com.bolder.server.models.dto.PedidoVentaLinListDto;
import com.bolder.server.models.entity.PedidoVentaLin;

public interface IPedidoVentaLinService {

	public PedidoVentaLinEditDto save(PedidoVentaLinEditDto pedidoVentaLinDto) throws Exception;
	public void delete(Long linId) throws Exception;
	public PedidoVentaLinEditDto patch(PedidoVentaLinEditDto dto) throws Exception;
	public List<PedidoVentaLinListDto> findAllToList() throws Exception;
	public PedidoVentaLinEditDto findOne(Long linId) throws Exception;
	public void reordenar(List<PedidoVentaLinEditDto> pedidoVentaLinDtoList) throws Exception;
}
