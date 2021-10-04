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
import com.bolder.server.models.dto.ClienteDropdownDto;
import com.bolder.server.models.dto.ClienteDto;
import com.bolder.server.models.dto.ClienteEditDto;
import com.bolder.server.models.dto.ClienteListDto;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.service.IClienteService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ClienteController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IClienteService clienteService;

	
	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public List<ClienteListDto> getClientesList() throws Exception{
		return clienteService.findAllToList();
	}
	@RequestMapping(value = "/cliente/dropdown", method = RequestMethod.GET)
	public List<ClienteDropdownDto> getClientesDropdown() throws Exception{
		return clienteService.findAllDropdown();
	}
	
	
	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getCliente( @PathVariable(value="id") Long id) throws Exception{
		ClienteDto clienteDto = clienteService.findOne(id);
		if (clienteDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/cliente", method = RequestMethod.POST)
	public ResponseEntity<ClienteDto> crea(@RequestBody Cliente cliente) throws Exception{
		cliente.setId(null);
		ClienteDto clienteDto = clienteService.save(cliente);
		return new ResponseEntity<ClienteDto>(clienteDto, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/cliente/{id}", method = {RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody ClienteEditDto  dto,@PathVariable(value="id") Long id) throws Exception{
		clienteService.patch(dto,id);
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	
	@RequestMapping(value = "/cliente/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) throws Exception {
			clienteService.delete(id);
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
