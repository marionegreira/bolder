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
import com.bolder.server.models.dao.IIvaConfiguracionDao;
import com.bolder.server.models.dto.IvaConfiguracionDto;
import com.bolder.server.models.dto.IvaDataDto;
import com.bolder.server.models.entity.Empresa;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;
import com.bolder.server.util.PersonalException;

@Service
public class IvaConfiguracionServiceImpl implements IIvaConfiguracionService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IIvaConfiguracionDao ivaConfiguracionDao;
	@Autowired
	private IIvaNegocioService ivaNegocioService;
	@Autowired
	private IIvaProductoService ivaProductoService;


	@Override
	@Transactional
	public IvaConfiguracionDto save(IvaConfiguracionDto ivaConfiguracionDto) throws Exception {
		IvaConfiguracion ivaConfiguracion = new ModelMapper().map(ivaConfiguracionDto, IvaConfiguracion.class);
		if (ivaConfiguracionDto.getIvaNegocioCodigo()==null || ivaConfiguracionDto.getIvaProductoCodigo()==null)
			throw new PersonalException("Debe indicar un codigo de iva");
		
		ivaConfiguracion.setIvaConfiguracionId(new IvaConfiguracionId(ivaConfiguracionDto.getIvaNegocioCodigo(), ivaConfiguracionDto.getIvaProductoCodigo()));
		if (ivaConfiguracion.getIva()==null) ivaConfiguracion.setIva(0D);
		if (ivaConfiguracion.getRetencion()==null) ivaConfiguracion.setRetencion(0D);
		
		ivaConfiguracion = ivaConfiguracionDao.save(ivaConfiguracion);
		ivaConfiguracionDto= new ModelMapper().map(ivaConfiguracion, IvaConfiguracionDto.class);

		return ivaConfiguracionDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<IvaConfiguracionDto> findAllToList() throws Exception{
		List<IvaConfiguracion> ivaConfiguracion = ivaConfiguracionDao.findAll();
		
		Type listType = new TypeToken<List<IvaConfiguracionDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<IvaConfiguracionDto> ivaConfiguracionDto= modelMapper.map(ivaConfiguracion, listType);
		return ivaConfiguracionDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public IvaDataDto getIvaData() throws Exception{
		IvaDataDto ivaDataDto = new IvaDataDto();
		
		ivaDataDto.setIvasNegocio(ivaNegocioService.findAllToList());
		ivaDataDto.setIvasProducto(ivaProductoService.findAllToList());
		ivaDataDto.setIvasConfiguracion(findAllToList());

		return ivaDataDto;
	}



	

	@Override
	@Transactional
	public IvaConfiguracion patch(IvaConfiguracionDto ivaConfiguracionDto) throws Exception{
		IvaConfiguracionId  ivaConfiguracionId = new IvaConfiguracionId(ivaConfiguracionDto.getIvaNegocioCodigo(), ivaConfiguracionDto.getIvaProductoCodigo());
		IvaConfiguracion ivaConfiguracion = ivaConfiguracionDao.findById(ivaConfiguracionId).orElse(null);
		if (ivaConfiguracion==null)
			throw new PersonalException("No exite el registro");
		
		if (ivaConfiguracionDto.getIva()!=null)
			ivaConfiguracion.setIva(ivaConfiguracionDto.getIva());
		if (ivaConfiguracionDto.getRetencion()!=null)
			ivaConfiguracion.setRetencion(ivaConfiguracionDto.getRetencion());
		return ivaConfiguracionDao.save(ivaConfiguracion);
	}

	@Override
	@Transactional
	public void delete(String ivaNegocio, String ivaProducto) throws Exception {
		IvaConfiguracionId  ivaConfiguracionId = new IvaConfiguracionId(ivaNegocio,ivaProducto);
		ivaConfiguracionDao.deleteById(ivaConfiguracionId);
	}
	
	
	
	


}
