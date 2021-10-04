package com.bolder.server.controllers;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bolder.server.util.PersonalException;
import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaDtoBig;
import com.bolder.server.models.dto.PedidoVentaEditDto;
import com.bolder.server.models.dto.PedidoVentaListDto;
import com.bolder.server.models.entity.PedidoVenta;
import com.bolder.server.models.service.IPedidoVentaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PedidoVentaController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPedidoVentaService pedidoVentaCabService;

	
	@RequestMapping(value = "/pedido/venta", method = RequestMethod.GET)
	public List<PedidoVentaListDto> getPedidoVentaCabsList() throws Exception{
		return pedidoVentaCabService.findAllToList();
	}
	
	
	@RequestMapping(value = "/pedido/venta/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getPedidoVentaCab( @PathVariable(value="id") Long id) throws Exception{
		PedidoVentaDtoBig cabDto = pedidoVentaCabService.findOne(id);
		if (cabDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<PedidoVentaDtoBig>(cabDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/pedido/venta", method = RequestMethod.POST)
	public ResponseEntity<?> crea(@RequestBody PedidoVentaEditDto pedidoVentaCab) throws Exception{
		PedidoVentaDtoBig facturaVentaDtoBig = pedidoVentaCabService.save(pedidoVentaCab);
		return new ResponseEntity<PedidoVentaDtoBig>(facturaVentaDtoBig, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/pedido/venta/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody PedidoVentaEditDto dto,@PathVariable(value="id") Long id) throws Exception{
		pedidoVentaCabService.patch(dto,id);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	
	@RequestMapping(value = "/pedido/venta/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) throws Exception {
		pedidoVentaCabService.delete(id);
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pedido/venta/{pedidoId}/facturar", method = {RequestMethod.GET})
	public ResponseEntity<?>  facturar(@PathVariable(value="pedidoId") Long pedidoId) throws Exception {
		FacturaVentaEditDto facturaVentaEditDto = pedidoVentaCabService.facturar(pedidoId);
		return new ResponseEntity<FacturaVentaEditDto>(facturaVentaEditDto, HttpStatus.OK);
	}

	@ExceptionHandler(value=PersonalException.class)
	@ResponseBody
	public ResponseEntity<String> personalException(HttpServletResponse response, Exception e) throws Exception {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.valueOf(420));
    }

	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> exception(HttpServletResponse response, Exception e) throws Exception {
		e.printStackTrace();
        return new ResponseEntity<String>("Intentelo mas tarde", HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(value=EmptyResultDataAccessException.class)
	public ResponseEntity<String> EmptyResultDataAccessException(HttpServletResponse response, Exception e) throws Exception {
		return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<String> DataIntegrityViolationException(HttpServletResponse response, Exception e) throws Exception {
		return new ResponseEntity<String>("Integrity Error", HttpStatus.FAILED_DEPENDENCY);
    }
}
