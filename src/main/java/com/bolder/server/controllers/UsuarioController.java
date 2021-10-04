package com.bolder.server.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
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
import com.bolder.server.models.dto.UsuarioEditDto;
import com.bolder.server.models.dto.UsuarioListDto;
import com.bolder.server.models.entity.Usuario;
import com.bolder.server.models.service.IUsuarioService;
import com.bolder.server.util.PersonalException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UsuarioController {
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public List<UsuarioListDto> findAllNotMe() throws Exception{
		return usuarioService.findAllNotMe();
	}
	
	@RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
	public UsuarioEditDto getCliente(@PathVariable(value="id") Long id) throws Exception{
		return usuarioService.findOne(id);
	}
	

	
	@RequestMapping(value = "/usuario", method = RequestMethod.POST)
	public ResponseEntity<?> crea(@RequestBody Usuario usuario) throws Exception{
		usuario.setId(null);
		UsuarioEditDto newUsuario = usuarioService.save(usuario);
		return new ResponseEntity<UsuarioEditDto>(newUsuario, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/usuario/{id}", method = {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<?> patch(@RequestBody UsuarioEditDto dto,@PathVariable(value="id") Long id) throws Exception{
		if ( usuarioService.patch(dto,id) !=null)
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Ignored", HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/me", method = RequestMethod.GET)
	public UsuarioEditDto getMe() throws Exception{
		return usuarioService.getMe();
	}
	
	@RequestMapping(value = "/me", method = {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<?> mePatch(@RequestBody UsuarioEditDto dto) throws Exception{
		if ( usuarioService.mePatch(dto) !=null)
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
		else
			return new ResponseEntity<String>("Ignored", HttpStatus.NO_CONTENT);
	}	
	
	@RequestMapping(value = "/me/changepass", method = {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<?> meChangePass(@RequestBody Map<String, String> obj) throws Exception{
		usuarioService.meChangePass(obj.get("oldPassword"),obj.get("newPassword"),obj.get("rePassword"));
		return new ResponseEntity<String>("Ok", HttpStatus.OK);

	}

	@ResponseBody
	@RequestMapping(value="/usuario/changepass/{userId}", method=RequestMethod.POST)
	public ResponseEntity<?>  changePassword(@RequestBody Map<String, String> obj, @PathVariable(value="userId") Long userId) throws Exception{
		usuarioService.changePassword(obj.get("password"),obj.get("rePassword"),userId );

		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="/usuario/myprofile", method=RequestMethod.POST)
	public ResponseEntity<?>  SaveMyProfile(@RequestBody Usuario usuario,Principal principal) throws Exception{
		usuarioService.modifyMyProfile(usuario,principal.getName());
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	*/
	@RequestMapping(value = "/usuario/{id}", method = {RequestMethod.DELETE})
	public ResponseEntity<?>  delete(@PathVariable(value="id") Long id) throws Exception {
			usuarioService.delete(id);
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
