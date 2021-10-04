package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaListDto;

public interface IFacturaVentaService {
	public List<FacturaVentaListDto> findAllToList() throws Exception;
	public FacturaVentaDtoBig findOne(Long id) throws Exception;
	public String getFacturaVentaPdfB64(Long facturaId) throws Exception;
	public String test(Long facturaId) throws Exception;
	public void sendFacturaPdf(Long facturaId, String emails) throws Exception;
}
