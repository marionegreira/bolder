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
import com.bolder.server.models.dto.IvaConfiguracionDto;
import com.bolder.server.models.dto.IvaDataDto;
import com.bolder.server.models.entity.IvaConfiguracion;
import com.bolder.server.models.service.IIvaConfiguracionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class IvaConfiguracionController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IIvaConfiguracionService ivaConfiguracionService;

	
	@RequestMapping(value = "/configuracion/iva/configuracion", method = RequestMethod.GET)
	public List<IvaConfiguracionDto> getIvaConfiguracionsList() throws Exception{
		return ivaConfiguracionService.findAllToList();
	}
	
	@RequestMapping(value = "/configuracion/iva/data", method = RequestMethod.GET)
	public IvaDataDto getIvaData() throws Exception{
		return ivaConfiguracionService.getIvaData();
	}

	
	@RequestMapping(value = "/configuracion/iva/configuracion", method = RequestMethod.POST)
	public ResponseEntity<IvaConfiguracionDto> crea(@RequestBody IvaConfiguracionDto ivaConfiguracionDto) throws Exception{
		ivaConfiguracionDto = ivaConfiguracionService.save(ivaConfiguracionDto);
		return new ResponseEntity<IvaConfiguracionDto>(ivaConfiguracionDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/configuracion/iva/configuracion", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  patch(@RequestBody IvaConfiguracionDto ivaConfiguracionDto) throws Exception{
		ivaConfiguracionService.patch(ivaConfiguracionDto);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}
	
	@RequestMapping(value = "/configuracion/iva/configuracion/{ivaNegocio}/{ivaProducto}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="ivaNegocio") String ivaNegocio, @PathVariable(value="ivaProducto") String ivaProducto) throws Exception {
			ivaConfiguracionService.delete(ivaNegocio,ivaProducto);
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
	}

	@ExceptionHandler(value=PersonalException.class)
	@ResponseBody
	public ResponseEntity<String> personalException(HttpServletResponse response, Exception e) throws Exception {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.valueOf(420));
    }

	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<String> exception(HttpServletResponse response, Exception e) throws Exception {
		System.out.println(e.getMessage());	
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
