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
import com.bolder.server.models.dto.ArticuloIvaDto;
import com.bolder.server.models.dto.ArticuloListDto;
import com.bolder.server.models.entity.Articulo;
import com.bolder.server.models.entity.IvaNegocio;
import com.bolder.server.models.service.IArticuloService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ArticuloController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IArticuloService articuloService;

	
	@RequestMapping(value = "/articulo", method = RequestMethod.GET)
	public List<ArticuloListDto> getArticulosList() throws Exception{
		return articuloService.findAllToList();
	}
	@RequestMapping(value = "/articulo/dropdown", method = RequestMethod.GET)
	public List<ArticuloDropdownDto> getArticulosDropdown() throws Exception{
		return articuloService.findAllDropdown();
	}
	
	
	@RequestMapping(value = "/articulo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getArticulo( @PathVariable(value="id") String codigo) throws Exception{
		ArticuloDto articuloDto = articuloService.findOne(codigo);
		if (articuloDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<ArticuloDto>(articuloDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/articulo/iva/{articuloId}/{ivaNegocio}", method = RequestMethod.GET)
	public ResponseEntity<?>  getArticuloIva( @PathVariable(value="articuloId") String articuloId, @PathVariable(value="ivaNegocio") String ivaNegocio) throws Exception{
		ArticuloIvaDto articuloIvaDto = articuloService.findOneIva(articuloId, new IvaNegocio(ivaNegocio));
		if (articuloIvaDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<ArticuloIvaDto>(articuloIvaDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/articulo", method = RequestMethod.POST)
	public ResponseEntity<ArticuloDto> crea(@RequestBody Articulo articulo) throws Exception{
		ArticuloDto articuloDto = articuloService.save(articulo);
		return new ResponseEntity<ArticuloDto>(articuloDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/articulo/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody ArticuloEditDto dto,@PathVariable(value="id") String codigo) throws Exception{
		articuloService.patch(dto,codigo);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	
	@RequestMapping(value = "/articulo/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") String codigo) throws Exception {
			articuloService.delete(codigo);
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
