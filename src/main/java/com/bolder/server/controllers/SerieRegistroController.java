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
import com.bolder.server.models.dto.SerieRegistroEditDto;
import com.bolder.server.models.entity.SerieRegistro;
import com.bolder.server.models.service.ISerieRegistroService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class SerieRegistroController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ISerieRegistroService serieRegistroService;

	
	@RequestMapping(value = "/configuracion/serieregistro", method = RequestMethod.GET)
	public List<SerieRegistroEditDto> getSerieRegistrosList() throws Exception{
		return serieRegistroService.findAllToList();
	}
	
	
	@RequestMapping(value = "/configuracion/serieregistro/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getSerieRegistro( @PathVariable(value="id") Long id) throws Exception{
		SerieRegistroEditDto serieRegistroDto = serieRegistroService.findOne(id);
		if (serieRegistroDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<SerieRegistroEditDto>(serieRegistroDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/configuracion/serieregistro", method = RequestMethod.POST)
	public ResponseEntity<SerieRegistroEditDto> crea(@RequestBody SerieRegistro serieRegistro) throws Exception{
		SerieRegistroEditDto serieRegistroDto = serieRegistroService.save(serieRegistro);
		return new ResponseEntity<SerieRegistroEditDto>(serieRegistroDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/configuracion/serieregistro/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody SerieRegistroEditDto  dto,@PathVariable(value="id") Long id) throws Exception{
		serieRegistroService.patch(dto,id);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	
	@RequestMapping(value = "/configuracion/serieregistro/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) throws Exception {
			serieRegistroService.delete(id);
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
		return new ResponseEntity<String>("Integrity Error", HttpStatus.FAILED_DEPENDENCY);
    }
}
