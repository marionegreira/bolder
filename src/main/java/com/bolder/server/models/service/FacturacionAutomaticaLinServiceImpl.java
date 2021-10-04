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
import com.bolder.server.models.dao.IFacturacionAutomaticaDao;
import com.bolder.server.models.dao.IFacturacionAutomaticaLinDao;
import com.bolder.server.models.dto.FacturacionAutomaticaLinEditDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.models.entity.IvaProducto;
import com.bolder.server.models.entity.FacturacionAutomatica;
import com.bolder.server.models.entity.FacturacionAutomaticaLin;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class FacturacionAutomaticaLinServiceImpl implements IFacturacionAutomaticaLinService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IFacturacionAutomaticaLinDao facturacionAutomaticaLinDao;
	@Autowired
	private IFacturacionAutomaticaDao facturacionAutomaticaCabDao;
	
	@Autowired
	private IArticuloDao articuloDao;

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IIvaConfiguracionDao ivaConfiguracionDao;



	@Override
	@Transactional
	public FacturacionAutomaticaLinEditDto save(FacturacionAutomaticaLinEditDto facturacionAutomaticaLinDto) throws Exception {
		
		FacturacionAutomaticaLin facturacionAutomaticaLin = new ModelMapper().map(facturacionAutomaticaLinDto, FacturacionAutomaticaLin.class);
		facturacionAutomaticaLin.setId(null);
		
		FacturacionAutomatica ventaCab = facturacionAutomaticaCabDao.findById(facturacionAutomaticaLinDto.getFacturacionAutomaticaId()).orElse(null);
		if (ventaCab==null)
			throw new PersonalException("Debe indicar una cabecera de venta");
		
		Cliente cliente = clienteDao.findById(ventaCab.getCliente().getId()).orElse(null);
		
		facturacionAutomaticaLin.setFacturacionAutomatica(ventaCab);
	
		if (facturacionAutomaticaLin.getPrecio()==null ) facturacionAutomaticaLin.setPrecio(0D);
		if (facturacionAutomaticaLin.getCantidad()==null) facturacionAutomaticaLin.setCantidad(0D);

		
		
		if (facturacionAutomaticaLinDto.getArticuloCodigo() != null) {
			Articulo articulo = articuloDao.findById(facturacionAutomaticaLinDto.getArticuloCodigo()).orElse(null);
			if (facturacionAutomaticaLin.getPrecio()==null ) facturacionAutomaticaLin.setPrecio(1D);
			if (facturacionAutomaticaLin.getCantidad()==null) facturacionAutomaticaLin.setCantidad(articulo.getPrecioBase());
			facturacionAutomaticaLin = calculaLinea(facturacionAutomaticaLin, cliente.getIvaNegocio(),articulo.getIvaProducto());
		}

		
		facturacionAutomaticaLin.setOrden(new Long(ventaCab.getFacturacionAutomaticaLineas().size()));
		
		facturacionAutomaticaLin = facturacionAutomaticaLinDao.save(facturacionAutomaticaLin);

		em.refresh(ventaCab);
		facturacionAutomaticaLinDto= new ModelMapper().map(facturacionAutomaticaLin, FacturacionAutomaticaLinEditDto.class);
		return facturacionAutomaticaLinDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<FacturacionAutomaticaLinEditDto> findAllToList() throws Exception{
		List<FacturacionAutomaticaLin> facturacionAutomaticaLin = facturacionAutomaticaLinDao.findAll();
		
		Type listType = new TypeToken<List<FacturacionAutomaticaLinEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<FacturacionAutomaticaLinEditDto> facturacionAutomaticaLinDto= modelMapper.map(facturacionAutomaticaLin, listType);
		return facturacionAutomaticaLinDto;
	}


	@Override
	@Transactional(readOnly=true)
	public FacturacionAutomaticaLinEditDto findOne(Long linId) throws Exception{

		FacturacionAutomaticaLin facturacionAutomaticaLin = facturacionAutomaticaLinDao.findById(linId).orElse(null);
		if (facturacionAutomaticaLin==null) 
			return null;
		return new ModelMapper().map(facturacionAutomaticaLin, FacturacionAutomaticaLinEditDto.class);
	}
	

	@Override
	@Transactional
	public FacturacionAutomaticaLinEditDto patch(FacturacionAutomaticaLinEditDto dto) throws Exception{
		
		FacturacionAutomaticaLin facturacionAutomaticaLin = facturacionAutomaticaLinDao.findById(dto.getId()).orElse(null);
		if (facturacionAutomaticaLin==null) 
			throw new PersonalException("No existe la linea");
		
		FacturacionAutomatica ventaCab = facturacionAutomaticaCabDao.findById(dto.getFacturacionAutomaticaId()).orElse(null);
		
		if (facturacionAutomaticaLin.getPrecio()==null ) facturacionAutomaticaLin.setPrecio(0D);
		if (facturacionAutomaticaLin.getCantidad()==null) facturacionAutomaticaLin.setCantidad(0D);
		
		FacturacionAutomaticaLin toSave = (FacturacionAutomaticaLin)new Util().mergeData(facturacionAutomaticaLin, dto);
		toSave.setArticulo(facturacionAutomaticaLin.getArticulo());
		
		if (facturacionAutomaticaLin.getFacturacionAutomatica().getCliente()!=null && facturacionAutomaticaLin.getArticulo()!=null)
			toSave = calculaLinea(toSave, facturacionAutomaticaLin.getFacturacionAutomatica().getCliente().getIvaNegocio(), facturacionAutomaticaLin.getArticulo().getIvaProducto());

		facturacionAutomaticaLin=facturacionAutomaticaLinDao.save(toSave);
		FacturacionAutomaticaLinEditDto facturacionAutomaticaLinDto= new ModelMapper().map(facturacionAutomaticaLin, FacturacionAutomaticaLinEditDto.class);
		
		em.refresh(ventaCab);
		
		return facturacionAutomaticaLinDto;
	}

	@Override
	@Transactional
	public void delete(Long linId) throws Exception {
		
		
		FacturacionAutomaticaLin facturacionAutomaticaLin = facturacionAutomaticaLinDao.findById(linId).orElse(null);
		if (facturacionAutomaticaLin==null) 
			throw new PersonalException("No existe la linea");
		Long pedidoId=facturacionAutomaticaLin.getFacturacionAutomatica().getId();

		facturacionAutomaticaLinDao.delete(facturacionAutomaticaLin);
		facturacionAutomaticaLinDao.flush();
		FacturacionAutomatica ventaCab = facturacionAutomaticaCabDao.findById(pedidoId).orElse(null);
	}
	
	@Override
	@Transactional
	public void reordenar(List<FacturacionAutomaticaLinEditDto> facturacionAutomaticaLinDtoList) throws Exception {
		for (FacturacionAutomaticaLinEditDto item : facturacionAutomaticaLinDtoList) {
			facturacionAutomaticaLinDao.updateOrden(item.getId(), item.getOrden());
		}
	}
	
	
	private FacturacionAutomaticaLin calculaLinea(FacturacionAutomaticaLin facturacionAutomaticaLin, IvaNegocio ivaNegocio, IvaProducto ivaProducto) throws Exception {
		
		
		IvaConfiguracionId ivaConfiguracionId = new IvaConfiguracionId(ivaNegocio, ivaProducto);
		IvaConfiguracion ivaConfiguracion = ivaConfiguracionDao.findById(ivaConfiguracionId).orElse(null);
		if (ivaConfiguracion==null)
			throw new PersonalException("Debe configurar el iva para el articulo");
		
		facturacionAutomaticaLin.setIva(ivaConfiguracion.getIva());
		facturacionAutomaticaLin.setRetencion(ivaConfiguracion.getRetencion());
		facturacionAutomaticaLin.setImporteBruto( facturacionAutomaticaLin.getCantidad() * facturacionAutomaticaLin.getPrecio() );
		facturacionAutomaticaLin.setImporteIva(0D);
		facturacionAutomaticaLin.setImporteRetencion(0D);
		
		if (facturacionAutomaticaLin.getIva()>0)
			facturacionAutomaticaLin.setImporteIva(facturacionAutomaticaLin.getImporteBruto()*(facturacionAutomaticaLin.getIva()/100) );

		if (facturacionAutomaticaLin.getRetencion()>0)
			facturacionAutomaticaLin.setImporteRetencion(facturacionAutomaticaLin.getImporteBruto()*(facturacionAutomaticaLin.getRetencion()/100) );
		
		facturacionAutomaticaLin.setImporteNeto( facturacionAutomaticaLin.getImporteBruto()+facturacionAutomaticaLin.getImporteIva()-facturacionAutomaticaLin.getImporteRetencion() );
		
		return facturacionAutomaticaLin;
	
	}



	
	
	
	


}
