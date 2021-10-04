package com.bolder.server.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaListDto;
import com.bolder.server.models.entity.Cliente;
import com.bolder.server.models.service.IFacturaVentaService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class FacturaVentaController {

	 private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IFacturaVentaService facturaVentaCabService;

	
	@RequestMapping(value = "/factura/venta", method = RequestMethod.GET)
	public List<FacturaVentaListDto> getFacturaVentaCabsList() throws Exception{
		return facturaVentaCabService.findAllToList();
	}
	
	
	@RequestMapping(value = "/factura/venta/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>  getFacturaVentaCab( @PathVariable(value="id") Long id) throws Exception{
		FacturaVentaDtoBig cabDto = facturaVentaCabService.findOne(id);
		if (cabDto==null) {
			return new ResponseEntity<String>("", HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<FacturaVentaDtoBig>(cabDto, HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/factura/venta/{id}/pdf", method = RequestMethod.GET)
	public Map<String, String>  getFacturaVentaPdf( @PathVariable(value="id") Long facturaId) throws Exception{
		Map<String, String> pdfResponse = new HashMap<String, String>();
		pdfResponse.put("pdf", facturaVentaCabService.getFacturaVentaPdfB64(facturaId) );
		return pdfResponse;
	}
	
	@RequestMapping(value = "/factura/venta/{id}/mail/pdf", method = RequestMethod.POST)
	public ResponseEntity<?>  sendFacturaVentaMailPdf( @PathVariable(value="id") Long facturaId, @RequestBody String mails) throws Exception{
		facturaVentaCabService.sendFacturaPdf(facturaId, mails);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/factura/venta/{id}/test", method = RequestMethod.GET)
	public String  text( @PathVariable(value="id") Long facturaId) throws Exception{

		return facturaVentaCabService.test(facturaId);

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
