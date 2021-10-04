package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.SpringTemplateEngine;
import com.bolder.server.SPMailSender;
import com.bolder.server.models.dao.IClienteDao;
import com.bolder.server.models.dao.IFacturacionAutomaticaDao;
import com.bolder.server.models.dto.FacturacionAutomaticaDtoBig;
import com.bolder.server.models.dto.FacturacionAutomaticaEditDto;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.FacturacionAutomatica;
import com.bolder.server.system.service.SpDataConfigurationService;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;


@Service
public class FacturacionAutomaticaServiceImpl implements IFacturacionAutomaticaService {
	
	@Autowired
	private IFacturacionAutomaticaDao facturacionAutomaticaDao;
	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	ApplicationContext appContext;
		
	@Override
	@Transactional(readOnly=true)
	public List<FacturacionAutomaticaEditDto> findAllToList() throws Exception{
		List<FacturacionAutomatica> facturacionAutomatica = facturacionAutomaticaDao.findAll();
		
		Type listType = new TypeToken<List<FacturacionAutomaticaEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<FacturacionAutomaticaEditDto> facturaVentaCabDto= modelMapper.map(facturacionAutomatica, listType);
		return facturaVentaCabDto;
	}


	@Override
	@Transactional(readOnly=true)
	public FacturacionAutomaticaDtoBig findOne(Long id) throws Exception{
		FacturacionAutomatica facturacionAutomatica = facturacionAutomaticaDao.findByIdBig(id);
		if (facturacionAutomatica==null) 
			throw new PersonalException("No existe el registro");
		FacturacionAutomaticaDtoBig facturaVentaCabDtoBig = new ModelMapper().map(facturacionAutomatica, FacturacionAutomaticaDtoBig.class);
		return facturaVentaCabDtoBig;
	}
	

	@Override
	@Transactional
	public FacturacionAutomaticaDtoBig save(FacturacionAutomaticaEditDto facturacionAutomaticaDto) throws Exception {
		FacturacionAutomatica facturacionAutomatica = new FacturacionAutomatica(); 
		facturacionAutomatica = new ModelMapper().map(facturacionAutomaticaDto, FacturacionAutomatica.class);
		
		
		
		Cliente cliente = clienteDao.findById(facturacionAutomatica.getCliente().getId()).orElse(null);
		if (cliente == null) 
			throw new PersonalException("Debe indicar un cliente");
		
		facturacionAutomatica.setCliente(cliente);
		
		facturacionAutomatica = facturacionAutomaticaDao.save(facturacionAutomatica);
		
		ModelMapper modelMapper = new ModelMapper();
		//modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		FacturacionAutomaticaDtoBig facturacionAutomaticaDtoBig= modelMapper.map(facturacionAutomatica, FacturacionAutomaticaDtoBig.class);
		facturacionAutomaticaDtoBig.setLineas(new ArrayList());
		return facturacionAutomaticaDtoBig;
	}
	
	@Override
	@Transactional
	public FacturacionAutomatica patch(FacturacionAutomaticaEditDto dto, Long id) throws Exception{
		FacturacionAutomatica facturacionAutomatica = facturacionAutomaticaDao.findById(id).orElse(null);
		if (facturacionAutomatica==null) 
			throw new PersonalException("No existe el facturacionAutomatica");
		FacturacionAutomatica toSave = (FacturacionAutomatica)new Util().mergeData(facturacionAutomatica, dto);
		return facturacionAutomaticaDao.save(toSave);
	}
	
	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		facturacionAutomaticaDao.deleteById(id);
	}
	
	
		
	
	
}
