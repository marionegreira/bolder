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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bolder.server.util.PersonalException;
import com.bolder.server.models.dto.PedidoVentaLinEditDto;
import com.bolder.server.models.dto.PedidoVentaLinListDto;
import com.bolder.server.models.service.IPedidoVentaLinService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PedidoVentaLinController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IPedidoVentaLinService pedidoVentaLinService;

	
	@RequestMapping(value = "/pedido/venta/lin", method = RequestMethod.GET)
	public List<PedidoVentaLinListDto> getPedidoVentaLinsList() throws Exception{
		return pedidoVentaLinService.findAllToList();
	}
	
	
	@RequestMapping(value = "/pedido/venta/lin/{idLin}", method = RequestMethod.GET)
	public ResponseEntity<?>  getPedidoVentaLin(@PathVariable(value="idLin") Long idLin) throws Exception{
		PedidoVentaLinEditDto cabDto = pedidoVentaLinService.findOne(idLin);
		if (cabDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<PedidoVentaLinEditDto>(cabDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/pedido/venta/lin", method = RequestMethod.POST)
	public ResponseEntity<PedidoVentaLinEditDto> crea(@RequestBody PedidoVentaLinEditDto pedidoVentaLin) throws Exception{
		PedidoVentaLinEditDto articuloDto = pedidoVentaLinService.save(pedidoVentaLin);
		return new ResponseEntity<PedidoVentaLinEditDto>(articuloDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/pedido/venta/lin/{idLin}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody PedidoVentaLinEditDto dto, @PathVariable(value="idLin") Long idLin) throws Exception{
		PedidoVentaLinEditDto pedidoVentaLinDto = pedidoVentaLinService.patch(dto);
		return new ResponseEntity<PedidoVentaLinEditDto>(pedidoVentaLinDto, HttpStatus.OK);

	}

	
	@RequestMapping(value = "/pedido/venta/lin/{idLin}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="idLin") Long idLin) throws Exception {
		pedidoVentaLinService.delete(idLin);
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pedido/venta/lin/orden", method = RequestMethod.POST)
	public ResponseEntity<?> reorder(@RequestBody List<PedidoVentaLinEditDto> pedidoVentaLinDtoList) throws Exception{
		
		pedidoVentaLinService.reordenar(pedidoVentaLinDtoList);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);
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
