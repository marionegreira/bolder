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
import com.bolder.server.models.dto.ArticuloDropdownDto;
import com.bolder.server.models.dto.ArticuloDto;
import com.bolder.server.models.dto.ArticuloEditDto;
import com.bolder.server.models.dto.ArticuloListDto;
import com.bolder.server.models.dto.MovCarteraEditDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.service.IArticuloService;
import com.bolder.server.models.service.IMovCarteraService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class MovCarteraController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IMovCarteraService movCarteraService;

	
	@RequestMapping(value = "/cartera/cobro/pendientes", method = RequestMethod.GET)
	public List<MovCarteraEditDto> getCobrosPendientes() throws Exception{
		return movCarteraService.findCobrosPendientes();
	}
	@RequestMapping(value = "/cartera/cobro/registrados", method = RequestMethod.GET)
	public List<MovCarteraEditDto> getCobrosRegistrado() throws Exception{
		return movCarteraService.findCobrosRegistrado();
	}
	@RequestMapping(value = "/cartera/cobro/{cobroId}/registra", method = RequestMethod.POST)
	public ResponseEntity<?> getCobrosRegistrado(@PathVariable(value="cobroId") Long cobroId,@RequestBody MovCarteraEditDto movCartera) throws Exception{
		List<MovCarteraEditDto> movCarteraDtoList = movCarteraService.registra(movCartera, cobroId);
		return new ResponseEntity<List<MovCarteraEditDto>>(movCarteraDtoList, HttpStatus.OK);
	}
	
	

	@ExceptionHandler(value=PersonalException.class)
	@ResponseBody
	public ResponseEntity<String> personalException(HttpServletResponse response, Exception e) throws Exception {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.valueOf(420));
    }

	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> exception(HttpServletResponse response, Exception e) throws Exception {
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
