package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolder.server.models.dao.IIvaNegocioDao;
import com.bolder.server.models.dto.IvaNegocioDto;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.util.PersonalException;

@Service
public class IvaNegocioServiceImpl implements IIvaNegocioService {
	
	@Autowired
	private IIvaNegocioDao ivaNegocioDao;


	@Override
	@Transactional
	public IvaNegocioDto save(IvaNegocio ivaNegocio) throws Exception {
		if (ivaNegocio.getCodigo()==null || ivaNegocio.getCodigo()=="")
			throw new PersonalException("Debe indicar un id");
		IvaNegocio saved = ivaNegocioDao.findById(ivaNegocio.getCodigo()).orElse(null);
		if (saved!=null) 
			throw new PersonalException("Ya existe un ivaNegocio con este id");
		
		ivaNegocio = ivaNegocioDao.save(ivaNegocio);
		IvaNegocioDto ivaNegocioDto= new ModelMapper().map(ivaNegocio, IvaNegocioDto.class);

		return ivaNegocioDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<IvaNegocioDto> findAllToList() throws Exception{
		List<IvaNegocio> ivaNegocio = ivaNegocioDao.findAll();
		
		Type listType = new TypeToken<List<IvaNegocioDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<IvaNegocioDto> ivaNegocioDto= modelMapper.map(ivaNegocio, listType);
		return ivaNegocioDto;
	}
	

	@Override
	@Transactional
	public IvaNegocio patch(IvaNegocio ivaNegocio) throws Exception{
		IvaNegocio ivaNegocioSaved = ivaNegocioDao.findById(ivaNegocio.getCodigo()).orElse(null);
		ivaNegocioSaved.setDescripcion(ivaNegocio.getDescripcion());
		return ivaNegocioDao.save(ivaNegocioSaved);
	}

	@Override
	@Transactional
	public void delete(String id) throws Exception {
		ivaNegocioDao.deleteById(id);
	}

}
