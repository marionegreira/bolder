package com.bolder.server.controllers;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bolder.server.util.PersonalException;
import com.bolder.server.models.dto.ConectorSmtpDto;
import com.bolder.server.models.dto.EmpresaEditDto;
import com.bolder.server.models.service.IEmpresaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EmpresaController {
	
	@Autowired
	private IEmpresaService configuracionService;

	
	@RequestMapping(value = "/configuracion", method = RequestMethod.GET)
	public EmpresaEditDto getConfiguracion() throws Exception{
		return configuracionService.findFirst();
	}

	@RequestMapping(value = "/configuracion", method = RequestMethod.POST)
	public ResponseEntity<?> modificaConfiguracion(@RequestBody EmpresaEditDto configuracionEditDto) throws Exception{
		configuracionEditDto = configuracionService.save(configuracionEditDto);
		return new ResponseEntity<EmpresaEditDto>(configuracionEditDto, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/configuracion", method = {RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<?>  put(@RequestBody EmpresaEditDto dto) throws Exception{
		if ( configuracionService.patch(dto) !=null) {
			configuracionService.updateBean();
			return new ResponseEntity<String>("Ok", HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>("Ignored", HttpStatus.NO_CONTENT);
	}

	@ResponseBody
	@RequestMapping(value="/configuracion/textsmtp/{email}", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<?> sendTestSmtp(@RequestBody ConectorSmtpDto conectorSmtpDto,@PathVariable(value="email") String email) throws Exception {
		configuracionService.textStmp(conectorSmtpDto, email);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value="/configuracion/upload/{tipo}", method=RequestMethod.POST)
	public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile multipartFile,@PathVariable(value="tipo") String tipo) throws Exception {
		configuracionService.uploadFile(multipartFile, tipo);
		return new ResponseEntity<>("", HttpStatus.OK);
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
