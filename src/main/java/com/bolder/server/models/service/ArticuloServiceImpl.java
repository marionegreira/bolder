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
import com.bolder.server.models.dao.IArticuloDao;
import com.bolder.server.models.dao.IClienteDao;
import com.bolder.server.models.dao.IIvaConfiguracionDao;
import com.bolder.server.models.dto.ArticuloDropdownDto;
import com.bolder.server.models.dto.ArticuloDto;
import com.bolder.server.models.dto.ArticuloEditDto;
import com.bolder.server.models.dto.ArticuloIvaDto;
import com.bolder.server.models.dto.ArticuloListDto;
import com.bolder.server.models.dto.IvaConfiguracionDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class ArticuloServiceImpl implements IArticuloService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IArticuloDao articuloDao;

	@Autowired
	private IClienteDao clienteDao;
	
	@Autowired
	private IIvaConfiguracionDao ivaConfiguracionDao;

	@Override
	@Transactional
	public ArticuloDto save(Articulo articulo) throws Exception {
		if (articulo.getCodigo()=="")
			throw new PersonalException("Debe indicar un id");
		Articulo saved = articuloDao.findById(articulo.getCodigo()).orElse(null);
		if (saved!=null) 
			throw new PersonalException("Ya existe un articulo con este id");
		
		articulo = articuloDao.save(articulo);
		ArticuloDto articuloDto= new ModelMapper().map(articulo, ArticuloDto.class);

		return articuloDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ArticuloListDto> findAllToList() throws Exception{
		List<Articulo> articulo = articuloDao.findAll();
		
		Type listType = new TypeToken<List<ArticuloListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<ArticuloListDto> articuloDto= modelMapper.map(articulo, listType);
		return articuloDto;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ArticuloDropdownDto> findAllDropdown() throws Exception{
		List<Articulo> articulo = articuloDao.findAll();
		
		Type listType = new TypeToken<List<ArticuloDropdownDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<ArticuloDropdownDto> articuloDropdownDto= modelMapper.map(articulo, listType);
		return articuloDropdownDto;
	}


	@Override
	@Transactional(readOnly=true)
	public ArticuloDto findOne(String codigo) throws Exception{
		Articulo articulo = articuloDao.findById(codigo).orElse(null);
		if (articulo==null) return null;
		return new ModelMapper().map(articulo, ArticuloDto.class);
	}
	@Override
	@Transactional(readOnly=true)
	public ArticuloIvaDto findOneIva(String codigo, IvaNegocio ivaNegocio) throws Exception{
		Articulo articulo = articuloDao.findById(codigo).orElse(null);
		if (articulo==null) return null;
		ArticuloIvaDto ArticuloIvaDto = new ModelMapper().map(articulo, ArticuloIvaDto.class);
		IvaConfiguracion ivaConfiguracion = ivaConfiguracionDao.findById(new IvaConfiguracionId(ivaNegocio,articulo.getIvaProducto())).orElse(null);
		
		ArticuloIvaDto.setIvaConfiguracion(new ModelMapper().map(ivaConfiguracion, IvaConfiguracionDto.class));
		
		return ArticuloIvaDto;
	}
	

	@Override
	@Transactional
	public Articulo patch(ArticuloEditDto dto, String codigo) throws Exception{
		Articulo articulo = articuloDao.findById(codigo).orElse(null);
		if (articulo==null) 
			throw new PersonalException("No existe el articulo");
		Articulo toSave = (Articulo)new Util().mergeData(articulo, dto);
		if (dto.getIvaProductoCodigo().equals("0"))
			toSave.setIvaProducto(null);
		return articuloDao.save(toSave);
	}

	@Override
	@Transactional
	public void delete(String codigo) throws Exception {
		articuloDao.deleteById(codigo);
	}
	
	


}
