package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolder.server.models.dao.IFacturaVentaDao;
import com.bolder.server.models.dao.IMovCarteraDao;
import com.bolder.server.models.dto.MovCarteraEditDto;
import com.bolder.server.models.entity.FacturaVenta;
import com.bolder.server.models.entity.MovCartera;
import com.bolder.server.util.PersonalException;

@Service
public class MovCarteraServiceImpl implements IMovCarteraService {
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IMovCarteraDao movCarteraDao;
	@Autowired
	private IFacturaVentaDao facturaVentaDao;


	@Override
	public List<MovCarteraEditDto> findCobrosPendientes() throws Exception {

		List<MovCartera> movimientos = movCarteraDao.findCobrosPendientes();
		Type listType = new TypeToken<List<MovCarteraEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		List<MovCarteraEditDto> movCarteraDto= new ModelMapper().map(movimientos, listType);

		return movCarteraDto;
	}

	@Override
	public List<MovCarteraEditDto> findCobrosRegistrado() throws Exception {
		
		List<MovCartera> movimientos = movCarteraDao.findCobrosRegistrados();
		Type listType = new TypeToken<List<MovCarteraEditDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		List<MovCarteraEditDto> movCarteraDto= new ModelMapper().map(movimientos, listType);

		return movCarteraDto;
	}
	@Override
	@Transactional
	public List<MovCarteraEditDto> registra(MovCarteraEditDto movCarteraDto, Long cobroId) throws Exception {
		MovCartera movCartera = movCarteraDao.findById(cobroId).orElse(null);
		if (movCartera==null)
			throw new PersonalException("No existe el movimiento");
		
		if (movCartera.getRegistrado())
			throw new PersonalException("Este movimiento ya está registrado");
		
		Double importeRestante = movCartera.getImportePendiente()-movCarteraDto.getImporteMovimiento();
		if (importeRestante>=0) {


			movCartera.setFechaMovimiento(movCarteraDto.getFechaMovimiento());
			movCartera.setImporteMovimiento(movCarteraDto.getImporteMovimiento());
			movCartera.setRegistrado(true);
			movCarteraDao.save(movCartera);
			if (importeRestante>0) {
				MovCartera newMovCartera = new MovCartera();
				newMovCartera.setFechaMovimiento(null);
				newMovCartera.setImporteMovimiento(0D);
				newMovCartera.setImportePendiente(importeRestante);
				newMovCartera.setRegistrado(false);
				newMovCartera.setTipoMovCartera(movCartera.getTipoMovCartera());
				newMovCartera.setFacturaVenta(movCartera.getFacturaVenta());
				newMovCartera.setMovimientoAnulado(false);
				newMovCartera = movCarteraDao.save(newMovCartera);
			}
			


			
		}else {
			throw new PersonalException("El importe a liquidar debe ser menos o igual a la deuda");
		}
		
		FacturaVenta facturaVenta = movCartera.getFacturaVenta();
		facturaVenta.setImportePendienteCobro(importeRestante);
		facturaVentaDao.save(facturaVenta);
		
		movCarteraDao.flush();
		List<MovCartera> movCarteraList = movCarteraDao.findCobrosByFactura(movCartera.getFacturaVenta().getId());
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		Type listType = new TypeToken<List<MovCarteraEditDto>>(){}.getType();
		
		List<MovCarteraEditDto> movCarteraDtoList= new ModelMapper().map(movCarteraList, listType);
		return movCarteraDtoList;
	}
	
}
