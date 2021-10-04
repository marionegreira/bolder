package com.bolder.server.models.service;

import java.util.List;

import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaDtoBig;
import com.bolder.server.models.dto.PedidoVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaListDto;
import com.bolder.server.models.entity.PedidoVenta;

public interface IPedidoVentaService {

	public PedidoVentaDtoBig save(PedidoVentaEditDto pedidoVentaCabDto) throws Exception;
	public void delete(Long id) throws Exception;
	public PedidoVenta patch(PedidoVentaEditDto dto, Long pedidoId) throws Exception;
	public List<PedidoVentaListDto> findAllToList() throws Exception;
	public PedidoVentaDtoBig findOne(Long id) throws Exception;
	public FacturaVentaEditDto facturar(Long pedidoId) throws Exception;

}
