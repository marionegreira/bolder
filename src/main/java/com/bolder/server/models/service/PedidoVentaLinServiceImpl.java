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
import com.bolder.server.models.dao.IPedidoVentaDao;
import com.bolder.server.models.dao.IPedidoVentaLinDao;
import com.bolder.server.models.dto.PedidoVentaLinEditDto;
import com.bolder.server.models.dto.PedidoVentaLinListDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.entity.IvaConfiguracion.IvaConfiguracionId;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.models.entity.IvaProducto;
import com.bolder.server.models.entity.PedidoVenta;
import com.bolder.server.models.entity.PedidoVentaLin;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class PedidoVentaLinServiceImpl implements IPedidoVentaLinService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IPedidoVentaLinDao pedidoVentaLinDao;
	@Autowired
	private IPedidoVentaDao pedidoVentaCabDao;
	
	@Autowired
	private IArticuloDao articuloDao;

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IIvaConfiguracionDao ivaConfiguracionDao;



	@Override
	@Transactional
	public PedidoVentaLinEditDto save(PedidoVentaLinEditDto pedidoVentaLinDto) throws Exception {
		
		PedidoVentaLin pedidoVentaLin = new ModelMapper().map(pedidoVentaLinDto, PedidoVentaLin.class);
		pedidoVentaLin.setId(null);
		
		PedidoVenta ventaCab = pedidoVentaCabDao.findById(pedidoVentaLinDto.getPedidoVentaId()).orElse(null);
		if (ventaCab==null)
			throw new PersonalException("Debe indicar una cabecera de venta");
		
		Cliente cliente = clienteDao.findById(ventaCab.getCliente().getId()).orElse(null);
		
		pedidoVentaLin.setPedidoVenta(ventaCab);
	
		if (pedidoVentaLin.getPrecio()==null ) pedidoVentaLin.setPrecio(0D);
		if (pedidoVentaLin.getCantidad()==null) pedidoVentaLin.setCantidad(0D);
		pedidoVentaLin.setImporteBruto(0D);
		pedidoVentaLin.setImporteNeto(0D);
		pedidoVentaLin.setImporteIva(0D);
		pedidoVentaLin.setImporteRetencion(0D);
		
		
		if (pedidoVentaLinDto.getArticuloCodigo() != null) {
			Articulo articulo = articuloDao.findById(pedidoVentaLinDto.getArticuloCodigo()).orElse(null);
			if (pedidoVentaLin.getPrecio()==null ) pedidoVentaLin.setPrecio(1D);
			if (pedidoVentaLin.getCantidad()==null) pedidoVentaLin.setCantidad(articulo.getPrecioBase());
			pedidoVentaLin = calculaLinea(pedidoVentaLin, cliente.getIvaNegocio(),articulo.getIvaProducto());
		}

		
		pedidoVentaLin.setOrden(new Long(ventaCab.getPedidoVentaLineas().size()));
		
		pedidoVentaLin = pedidoVentaLinDao.save(pedidoVentaLin);

		em.refresh(ventaCab);
		updatePedidoCab(ventaCab);

		pedidoVentaLinDto= new ModelMapper().map(pedidoVentaLin, PedidoVentaLinEditDto.class);
		return pedidoVentaLinDto;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PedidoVentaLinListDto> findAllToList() throws Exception{
		List<PedidoVentaLin> pedidoVentaLin = pedidoVentaLinDao.findAll();
		
		Type listType = new TypeToken<List<PedidoVentaLinListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<PedidoVentaLinListDto> pedidoVentaLinDto= modelMapper.map(pedidoVentaLin, listType);
		return pedidoVentaLinDto;
	}


	@Override
	@Transactional(readOnly=true)
	public PedidoVentaLinEditDto findOne(Long linId) throws Exception{

		PedidoVentaLin pedidoVentaLin = pedidoVentaLinDao.findById(linId).orElse(null);
		if (pedidoVentaLin==null) 
			return null;
		return new ModelMapper().map(pedidoVentaLin, PedidoVentaLinEditDto.class);
	}
	

	@Override
	@Transactional
	public PedidoVentaLinEditDto patch(PedidoVentaLinEditDto dto) throws Exception{
		
		PedidoVentaLin pedidoVentaLin = pedidoVentaLinDao.findById(dto.getId()).orElse(null);
		if (pedidoVentaLin==null) 
			throw new PersonalException("No existe la linea");
		
		PedidoVenta ventaCab = pedidoVentaCabDao.findById(dto.getPedidoVentaId()).orElse(null);
		
		if (pedidoVentaLin.getPrecio()==null ) pedidoVentaLin.setPrecio(0D);
		if (pedidoVentaLin.getCantidad()==null) pedidoVentaLin.setCantidad(0D);
		
		PedidoVentaLin toSave = (PedidoVentaLin)new Util().mergeData(pedidoVentaLin, dto);
		toSave.setArticulo(pedidoVentaLin.getArticulo());
		
		if (pedidoVentaLin.getPedidoVenta().getCliente()!=null && pedidoVentaLin.getArticulo()!=null)
			toSave = calculaLinea(toSave, pedidoVentaLin.getPedidoVenta().getCliente().getIvaNegocio(), pedidoVentaLin.getArticulo().getIvaProducto());

		pedidoVentaLin=pedidoVentaLinDao.save(toSave);
		PedidoVentaLinEditDto pedidoVentaLinDto= new ModelMapper().map(pedidoVentaLin, PedidoVentaLinEditDto.class);
		
		em.refresh(ventaCab);
		updatePedidoCab(ventaCab);
		
		return pedidoVentaLinDto;
	}

	@Override
	@Transactional
	public void delete(Long linId) throws Exception {
		
		
		PedidoVentaLin pedidoVentaLin = pedidoVentaLinDao.findById(linId).orElse(null);
		if (pedidoVentaLin==null) 
			throw new PersonalException("No existe la linea");
		Long pedidoId=pedidoVentaLin.getPedidoVenta().getId();

		pedidoVentaLinDao.delete(pedidoVentaLin);
		pedidoVentaLinDao.flush();
		PedidoVenta ventaCab = pedidoVentaCabDao.findById(pedidoId).orElse(null);
		updatePedidoCab(ventaCab);
	}
	
	@Override
	@Transactional
	public void reordenar(List<PedidoVentaLinEditDto> pedidoVentaLinDtoList) throws Exception {
		for (PedidoVentaLinEditDto item : pedidoVentaLinDtoList) {
			pedidoVentaLinDao.updateOrden(item.getId(), item.getOrden());
		}
	}
	
	
	private PedidoVentaLin calculaLinea(PedidoVentaLin pedidoVentaLin, IvaNegocio ivaNegocio, IvaProducto ivaProducto) throws Exception {
		
		
		IvaConfiguracionId ivaConfiguracionId = new IvaConfiguracionId(ivaNegocio, ivaProducto);
		IvaConfiguracion ivaConfiguracion = ivaConfiguracionDao.findById(ivaConfiguracionId).orElse(null);
		if (ivaConfiguracion==null)
			throw new PersonalException("Debe configurar el iva para el articulo");
		
		pedidoVentaLin.setIva(ivaConfiguracion.getIva());
		pedidoVentaLin.setRetencion(ivaConfiguracion.getRetencion());
		pedidoVentaLin.setImporteBruto( pedidoVentaLin.getCantidad() * pedidoVentaLin.getPrecio() );
		pedidoVentaLin.setImporteIva(0D);
		pedidoVentaLin.setImporteRetencion(0D);
		
		if (pedidoVentaLin.getIva()>0)
			pedidoVentaLin.setImporteIva(pedidoVentaLin.getImporteBruto()*(pedidoVentaLin.getIva()/100) );

		if (pedidoVentaLin.getRetencion()>0)
			pedidoVentaLin.setImporteRetencion(pedidoVentaLin.getImporteBruto()*(pedidoVentaLin.getRetencion()/100) );
		
		pedidoVentaLin.setImporteNeto( pedidoVentaLin.getImporteBruto()+pedidoVentaLin.getImporteIva()-pedidoVentaLin.getImporteRetencion() );
		
		return pedidoVentaLin;
	
	}

	@Transactional
	private void updatePedidoCab(PedidoVenta ventaCab) {
		ventaCab.setImporteBruto(0D);
		ventaCab.setImporteNeto(0D);
		ventaCab.setImporteIva(0D);
		ventaCab.setImporteRetencion(0D);
		
		for (PedidoVentaLin pedidoVentaLin : ventaCab.getPedidoVentaLineas()) {
			ventaCab.setImporteBruto(ventaCab.getImporteBruto()+pedidoVentaLin.getImporteBruto());
			ventaCab.setImporteNeto( ventaCab.getImporteNeto()+pedidoVentaLin.getImporteNeto() );
			ventaCab.setImporteIva( ventaCab.getImporteIva() + pedidoVentaLin.getImporteIva());
			ventaCab.setImporteRetencion( ventaCab.getImporteRetencion()+pedidoVentaLin.getImporteRetencion() );
		}
		
		pedidoVentaCabDao.save(ventaCab);
		
	}

	
	
	
	


}
