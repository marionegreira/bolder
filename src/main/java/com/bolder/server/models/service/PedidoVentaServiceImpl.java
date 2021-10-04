package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bolder.server.models.dao.IClienteDao;
import com.bolder.server.models.dao.IEmpresaDao;
import com.bolder.server.models.dao.IFacturaVentaDao;
import com.bolder.server.models.dao.IFacturaVentaLinDao;
import com.bolder.server.models.dao.IMovCarteraDao;
import com.bolder.server.models.dao.IPedidoVentaDao;
import com.bolder.server.models.dao.ISerieRegistroDao;
import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaDtoBig;
import com.bolder.server.models.dto.PedidoVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaListDto;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.entity.Empresa;
import com.bolder.server.models.entity.FacturaVenta;
import com.bolder.server.models.entity.FacturaVentaLin;
import com.bolder.server.models.entity.MovCartera;
import com.bolder.server.models.entity.MovCartera.TipoMovCartera;
import com.bolder.server.models.entity.PedidoVenta;
import com.bolder.server.models.entity.PedidoVentaLin;
import com.bolder.server.models.entity.SerieRegistro;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class PedidoVentaServiceImpl implements IPedidoVentaService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IPedidoVentaDao pedidoVentaCabDao;
	

	@Autowired
	private IFacturaVentaDao facturaVentaCabDao;
	@Autowired
	private IFacturaVentaLinDao facturaVentaCabLinDao;
	
	@Autowired
	private ISerieRegistroDao serieRegistroDao;
	@Autowired
	private IEmpresaDao configuracionDao;

	@Autowired
	private IClienteDao clienteDao;
	@Autowired
	private IMovCarteraDao movCarteraDao;


	@Override
	@Transactional
	public PedidoVentaDtoBig save(PedidoVentaEditDto pedidoVentaCabDto) throws Exception {
		PedidoVenta pedidoVentaCab = new PedidoVenta(); 
		pedidoVentaCab = new ModelMapper().map(pedidoVentaCabDto, PedidoVenta.class);
		
		
		
		Empresa configuracion = configuracionDao.findFirst();
		pedidoVentaCab.setSerieRegistroFactura(configuracion.getSerieFactura());
		pedidoVentaCab.setNo( getCodigoSerieRegistro(configuracion.getSeriePedido(), new Date() ));
		
		
		Cliente cliente = clienteDao.findById(pedidoVentaCab.getCliente().getId()).orElse(null);
		if (cliente == null) 
			throw new PersonalException("Debe indicar un cliente");
		
		pedidoVentaCab.setCliente(cliente);
		pedidoVentaCab.setCliNombre(cliente.getNombre());
		pedidoVentaCab.setCliDireccion(cliente.getDireccion());
		pedidoVentaCab.setCliPoblacion(cliente.getPoblacion());
		pedidoVentaCab.setCliProvincia(cliente.getProvincia());
		pedidoVentaCab.setCliCp(cliente.getCp());
		pedidoVentaCab.setCliNif(cliente.getNif());
		if (pedidoVentaCab.getFecha()==null)
			pedidoVentaCab.setFecha(new Date());
		if (pedidoVentaCab.getFechaFactura()==null)
			pedidoVentaCab.setFechaFactura(new Date());
		
		pedidoVentaCab = pedidoVentaCabDao.save(pedidoVentaCab);
		
		ModelMapper modelMapper = new ModelMapper();
		//modelMapper.getConfiguration().setAmbiguityIgnored(true);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		PedidoVentaDtoBig pedidoVentaDtoBig= modelMapper.map(pedidoVentaCab, PedidoVentaDtoBig.class);
		pedidoVentaDtoBig.setPedidoVentaLineas(new ArrayList());
		return pedidoVentaDtoBig;
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PedidoVentaListDto> findAllToList() throws Exception{
		List<PedidoVenta> pedidoVentaCab = pedidoVentaCabDao.findAll();
		
		Type listType = new TypeToken<List<PedidoVentaListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<PedidoVentaListDto> pedidoVentaCabDto= modelMapper.map(pedidoVentaCab, listType);
		return pedidoVentaCabDto;
	}


	@Override
	@Transactional(readOnly=true)
	public PedidoVentaDtoBig findOne(Long id) throws Exception{
		PedidoVenta pedidoVentaCab = pedidoVentaCabDao.findByIdBig(id).orElse(null);
		if (pedidoVentaCab==null) return null;
		return new ModelMapper().map(pedidoVentaCab, PedidoVentaDtoBig.class);
	}
	

	@Override
	@Transactional
	public PedidoVenta patch(PedidoVentaEditDto dto, Long id) throws Exception{
		PedidoVenta pedidoVentaCab = pedidoVentaCabDao.findById(id).orElse(null);
		if (pedidoVentaCab==null) 
			throw new PersonalException("No existe el pedidoVentaCab");
		PedidoVenta toSave = (PedidoVenta)new Util().mergeData(pedidoVentaCab, dto);
		return pedidoVentaCabDao.save(toSave);
	}

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		pedidoVentaCabDao.deleteById(id);
	}
	
	
	@Override
	@Transactional
	public FacturaVentaEditDto facturar(Long pedidoId) throws Exception {
		PedidoVenta pedidoVentaCab = pedidoVentaCabDao.findByIdBig(pedidoId).orElse(null);
		if (pedidoVentaCab==null) 
			throw new PersonalException("No existe el pedidoVentaCab");
		PedidoVentaDtoBig pedidoVentaCabDtoBig = new ModelMapper().map(pedidoVentaCab, PedidoVentaDtoBig.class);
		FacturaVentaDtoBig facturaVentaCabDtoBig = new FacturaVentaDtoBig();
		
		facturaVentaCabDtoBig.setClienteId(pedidoVentaCab.getCliente().getId());
		
		BeanUtils.copyProperties(pedidoVentaCabDtoBig,facturaVentaCabDtoBig);
		FacturaVenta facturaVentaCab = new ModelMapper().map(facturaVentaCabDtoBig, FacturaVenta.class);
		facturaVentaCab.setId(null);
		facturaVentaCab.setFecha(pedidoVentaCab.getFechaFactura());
		facturaVentaCab.setNo(  getCodigoSerieRegistro(pedidoVentaCab.getSerieRegistroFactura(),pedidoVentaCab.getFechaFactura())  );
		
		Set<FacturaVentaLin> lineasFactura=new HashSet<FacturaVentaLin>();
		
		Double total = 0D;

		for (PedidoVentaLin pedidoLin : pedidoVentaCab.getPedidoVentaLineas()) {
			
			total=total+(pedidoLin.getCantidad()*pedidoLin.getPrecio());
			FacturaVentaLin facturaLin = new FacturaVentaLin();
			BeanUtils.copyProperties(pedidoLin,facturaLin);
			facturaLin.setId(null);
			facturaLin.setFacturaVenta(facturaVentaCab);
			lineasFactura.add(facturaLin);
		}
		if (total==0)
			throw new PersonalException("No hay nada que facturar");
			
		facturaVentaCab.setFacturaVentaLineas(lineasFactura);
		facturaVentaCab.setImportePendienteCobro(facturaVentaCab.getImporteNeto());
		facturaVentaCab=facturaVentaCabDao.save(facturaVentaCab);
		
		
		ProcesaCartera(facturaVentaCab);
		
		FacturaVentaEditDto facturaVentaEditDto = new ModelMapper().map(facturaVentaCab, FacturaVentaEditDto.class);
		pedidoVentaCabDao.delete(pedidoVentaCab);
		
		return facturaVentaEditDto;
	}
	
	@Transactional
	public String getCodigoSerieRegistro(SerieRegistro serieRegistro,Date fecha) throws Exception {
		try {
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaRegistro = formatter.parse(formatter.format(fecha));
			
			if (serieRegistro.getSecuenciaFecha()) {
				if ( serieRegistro.getUltimaFechaRegistro()!=null && serieRegistro.getUltimaFechaRegistro().after(fechaRegistro) ) 
					throw new PersonalException("No se cumple la secuencia de fechas en la serie de la factura");
				
			}
			String codigo=""+(serieRegistro.getUltimoNumeroAsignado()+1);
			codigo = serieRegistro.getSerie()+"0000000".substring(codigo.length()) + codigo;
			
			serieRegistro.setUltimaFechaRegistro(fechaRegistro);
			serieRegistro.setUltimoNumeroAsignado(serieRegistro.getUltimoNumeroAsignado()+1);
			serieRegistroDao.save(serieRegistro);
			return codigo;
			
			
		} catch (Exception e) {
			 throw e;
		}
	}
	public void ProcesaCartera(FacturaVenta facturaVentaCab) throws Exception {
		try {
			
			MovCartera movCartera = new MovCartera();
			movCartera.setImporteMovimiento(0D);
			movCartera.setImportePendiente(facturaVentaCab.getImporteNeto());
			movCartera.setMovimientoAnulado(false);
			movCartera.setFacturaVenta(facturaVentaCab);
			movCartera.setRegistrado(false);
			movCartera.setTipoMovCartera(TipoMovCartera.COBRO);
			movCarteraDao.save(movCartera);
			
			
		} catch (Exception e) {
			 throw e;
		}
	}
	

	
	
	
	


}
