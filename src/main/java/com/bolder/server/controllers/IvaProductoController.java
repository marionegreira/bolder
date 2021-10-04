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
import com.bolder.server.models.dto.IvaProductoDto;
import com.bolder.server.models.entity.IvaProducto;
import com.bolder.server.models.service.IIvaProductoService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IvaProductoController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IIvaProductoService ivaProductoService;

	
	@RequestMapping(value = "/configuracion/iva/producto", method = RequestMethod.GET)
	public List<IvaProductoDto> getIvaProductosList() throws Exception{
		return ivaProductoService.findAllToList();
	}

	
	@RequestMapping(value = "/configuracion/iva/producto", method = RequestMethod.POST)
	public ResponseEntity<IvaProductoDto> crea(@RequestBody IvaProducto ivaProducto) throws Exception{
		IvaProductoDto ivaProductoDto = ivaProductoService.save(ivaProducto);
		return new ResponseEntity<IvaProductoDto>(ivaProductoDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/configuracion/iva/producto/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody IvaProducto ivaProducto) throws Exception{
		ivaProductoService.patch(ivaProducto);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/configuracion/iva/producto/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") String id) throws Exception {
			ivaProductoService.delete(id);
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
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
		return new ResponseEntity<String>(e.getCause().getCause().getMessage(), HttpStatus.FAILED_DEPENDENCY);
    }
}
