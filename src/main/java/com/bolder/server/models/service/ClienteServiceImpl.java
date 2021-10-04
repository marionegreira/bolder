package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolder.server.models.dao.IClienteDao;
import com.bolder.server.models.dto.ClienteDropdownDto;
import com.bolder.server.models.dto.ClienteDto;
import com.bolder.server.models.dto.ClienteEditDto;
import com.bolder.server.models.dto.ClienteListDto;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ClienteServiceImpl implements IClienteService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IClienteDao clienteDao;


	@Override
	@Transactional
	public ClienteDto save(Cliente cliente) throws Exception {
		cliente = clienteDao.save(cliente);
		ClienteDto clienteDto= new ModelMapper().map(cliente, ClienteDto.class);
		return clienteDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ClienteListDto> findAllToList() throws Exception{
		List<Cliente> cliente = clienteDao.findAll();
		
		Type listType = new TypeToken<List<ClienteListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<ClienteListDto> clienteDto= modelMapper.map(cliente, listType);
		return clienteDto;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ClienteDropdownDto> findAllDropdown() throws Exception{
		List<Cliente> cliente = clienteDao.findAll();
		
		Type listType = new TypeToken<List<ClienteDropdownDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<ClienteDropdownDto> clienteDropdownDto= modelMapper.map(cliente, listType);
		return clienteDropdownDto;
	}


	@Override
	@Transactional(readOnly=true)
	public ClienteDto findOne(Long id) throws Exception{
		Cliente cliente = clienteDao.findById(id).orElse(null);
		if (cliente==null) return null;
		return new ModelMapper().map(cliente, ClienteDto.class);
	}
	

	@Override
	@Transactional
	public Cliente patch(ClienteEditDto dto, Long id) throws Exception{
		Cliente cliente = clienteDao.findById(id).orElse(null);
		if (cliente==null) 
			throw new PersonalException("No existe el cliente");
		Cliente toSave = (Cliente)new Util().mergeData(cliente, dto);
		if (dto.getIvaNegocioCodigo().equals("") )
			toSave.setIvaNegocio(null);
		return clienteDao.save(toSave);
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		clienteDao.deleteById(id);
	}
	

	
	
	


}
