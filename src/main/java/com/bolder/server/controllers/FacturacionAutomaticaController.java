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
import com.bolder.server.models.dto.FacturacionAutomaticaDtoBig;
import com.bolder.server.models.dto.FacturacionAutomaticaEditDto;
import com.bolder.server.models.service.IFacturacionAutomaticaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FacturacionAutomaticaController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFacturacionAutomaticaService facturacionAutomaticaService;

	
	@RequestMapping(value = "/factura/automatica", method = RequestMethod.GET)
	public List<FacturacionAutomaticaEditDto> getFacturacionAutomaticaList() throws Exception{
		return facturacionAutomaticaService.findAllToList();
	}
	
	
	@RequestMapping(value = "/factura/automatica/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getFacturacionAutomatica( @PathVariable(value="id") Long id) throws Exception{
		FacturacionAutomaticaDtoBig cabDto = facturacionAutomaticaService.findOne(id);
		if (cabDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<FacturacionAutomaticaDtoBig>(cabDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/pedido/automatica", method = RequestMethod.POST)
	public ResponseEntity<?> crea(@RequestBody FacturacionAutomaticaEditDto facturacionAutomatica) throws Exception{
		FacturacionAutomaticaDtoBig facturaVentaDtoBig = facturacionAutomaticaService.save(facturacionAutomatica);
		return new ResponseEntity<FacturacionAutomaticaDtoBig>(facturaVentaDtoBig, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/pedido/automatica/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody FacturacionAutomaticaEditDto dto,@PathVariable(value="id") Long id) throws Exception{
		facturacionAutomaticaService.patch(dto,id);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	
	@RequestMapping(value = "/pedido/automatica/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) throws Exception {
		facturacionAutomaticaService.delete(id);
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
