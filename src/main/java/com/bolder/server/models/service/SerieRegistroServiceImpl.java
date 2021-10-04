package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolder.server.models.dao.ISerieRegistroDao;
import com.bolder.server.models.dto.SerieRegistroEditDto;
import com.bolder.server.models.entity.SerieRegistro;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class SerieRegistroServiceImpl implements ISerieRegistroService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private ISerieRegistroDao serieRegistroDao;


	@Override
	@Transactional
	public SerieRegistroEditDto save(SerieRegistro serieRegistro) throws Exception {
		serieRegistro.setId(null);

		serieRegistro = serieRegistroDao.save(serieRegistro);
		SerieRegistroEditDto serieRegistroDto= new ModelMapper().map(serieRegistro, SerieRegistroEditDto.class);

		return serieRegistroDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<SerieRegistroEditDto> findAllToList() throws Exception{
		List<SerieRegistro> serieRegistro = serieRegistroDao.findAll();
		
		Type listType = new TypeToken<List<SerieRegistroEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<SerieRegistroEditDto> serieRegistroDto= modelMapper.map(serieRegistro, listType);
		return serieRegistroDto;
	}

	@Override
	@Transactional(readOnly=true)
	public List<SerieRegistroEditDto> findAllDropdown() throws Exception{
		List<SerieRegistro> serieRegistro = serieRegistroDao.findAll();
		
		Type listType = new TypeToken<List<SerieRegistroEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<SerieRegistroEditDto> serieRegistroDropdownDto= modelMapper.map(serieRegistro, listType);
		return serieRegistroDropdownDto;
	}


	@Override
	@Transactional(readOnly=true)
	public SerieRegistroEditDto findOne(Long id) throws Exception{
		SerieRegistro serieRegistro = serieRegistroDao.findById(id).orElse(null);
		if (serieRegistro==null) return null;
		return new ModelMapper().map(serieRegistro, SerieRegistroEditDto.class);
	}
	

	@Override
	@Transactional
	public SerieRegistro patch(SerieRegistroEditDto dto, Long id) throws Exception{
		SerieRegistro serieRegistro = serieRegistroDao.findById(id).orElse(null);
		if (serieRegistro==null) 
			throw new PersonalException("No existe el serieRegistro");
		SerieRegistro toSave = (SerieRegistro)new Util().mergeData(serieRegistro, dto);
		return serieRegistroDao.save(toSave);
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		serieRegistroDao.deleteById(id);
	}
	
	


}
