package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolder.server.models.dao.IIvaProductoDao;
import com.bolder.server.models.dto.IvaProductoDto;
import com.bolder.server.models.entity.IvaProducto;
import com.bolder.server.util.PersonalException;

@Service
public class IvaProductoServiceImpl implements IIvaProductoService {
	
	@Autowired
	private IIvaProductoDao ivaProductoDao;


	@Override
	@Transactional
	public IvaProductoDto save(IvaProducto ivaProducto) throws Exception {
		if (ivaProducto.getCodigo()==null)
			throw new PersonalException("Debe indicar un id");
		IvaProducto saved = ivaProductoDao.findById(ivaProducto.getCodigo()).orElse(null);
		if (saved!=null) 
			throw new PersonalException("Ya existe un ivaProducto con este id");
		
		ivaProducto = ivaProductoDao.save(ivaProducto);
		IvaProductoDto ivaProductoDto= new ModelMapper().map(ivaProducto, IvaProductoDto.class);

		return ivaProductoDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<IvaProductoDto> findAllToList() throws Exception{
		List<IvaProducto> ivaProducto = ivaProductoDao.findAll();
		
		Type listType = new TypeToken<List<IvaProductoDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<IvaProductoDto> ivaProductoDto= modelMapper.map(ivaProducto, listType);
		return ivaProductoDto;
	}
	

	@Override
	@Transactional
	public IvaProducto patch(IvaProducto ivaProducto) throws Exception{
		IvaProducto ivaProductoSaved = ivaProductoDao.findById(ivaProducto.getCodigo()).orElse(null);
		ivaProductoSaved.setDescripcion(ivaProducto.getDescripcion());
		return ivaProductoDao.save(ivaProductoSaved);
	}

	@Override
	@Transactional
	public void delete(String id) throws Exception {
		ivaProductoDao.deleteById(id);
	}

}
