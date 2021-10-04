package com.bolder.server.models.service;

import java.util.List;
import com.bolder.server.models.dto.ClienteDropdownDto;
import com.bolder.server.models.dto.ClienteDto;
import com.bolder.server.models.dto.ClienteEditDto;
import com.bolder.server.models.dto.ClienteListDto;
import com.bolder.server.models.entity.Cliente;

public interface IClienteService {
	
	public ClienteDto save(Cliente cliente) throws Exception;
	public void delete(Long id) throws Exception;
	public Cliente patch(ClienteEditDto dto, Long id) throws Exception;
	public List<ClienteListDto> findAllToList() throws Exception;
	public List<ClienteDropdownDto> findAllDropdown() throws Exception;
	public ClienteDto findOne(Long id) throws Exception;
	
}
