package com.bolder.server.models.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.bolder.server.SPMailSender;
import com.bolder.server.models.dao.IFacturaVentaDao;
import com.bolder.server.models.dto.FacturaVentaDtoBig;
import com.bolder.server.models.dto.FacturaVentaListDto;
import com.bolder.server.models.entity.FacturaVenta;
import com.bolder.server.system.service.SpDataConfigurationService;
import com.bolder.server.util.PersonalException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.pdf.BaseFont;


@Service
public class FacturaVentaServiceImpl implements IFacturaVentaService {
	
	@Autowired
	private IFacturaVentaDao facturaVentaCabDao;
	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;
	@Autowired
	private SPMailSender mailSender;
	@Autowired
	ApplicationContext appContext;
		
	@Override
	@Transactional(readOnly=true)
	public List<FacturaVentaListDto> findAllToList() throws Exception{
		List<FacturaVenta> facturaVentaCab = facturaVentaCabDao.findAll();
		
		Type listType = new TypeToken<List<FacturaVentaListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<FacturaVentaListDto> facturaVentaCabDto= modelMapper.map(facturaVentaCab, listType);
		return facturaVentaCabDto;
	}


	@Override
	@Transactional(readOnly=true)
	public FacturaVentaDtoBig findOne(Long id) throws Exception{
		FacturaVenta faturaVentaCab = facturaVentaCabDao.findByIdBig(id);
		if (faturaVentaCab==null) 
			throw new PersonalException("No extite la factura");
		FacturaVentaDtoBig facturaVentaCabDtoBig = new ModelMapper().map(faturaVentaCab, FacturaVentaDtoBig.class);
		return facturaVentaCabDtoBig;
	}
	
	@Override
	@Transactional(readOnly=true)
	public String getFacturaVentaPdfB64(Long facturaId) throws Exception{
		
	
		FacturaVenta facturaVenta = facturaVentaCabDao.findByIdBig(facturaId);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		FacturaVentaDtoBig facturaVentaDto = modelMapper.map(facturaVenta, FacturaVentaDtoBig.class);
		
		String base64Pdf="";
        ByteArrayOutputStream outPdf = new ByteArrayOutputStream();
        
        try {
        	
        	Context thymeleafContext = new Context();
    	    thymeleafContext.setVariable("factura", facturaVentaDto);
    	    thymeleafContext.setVariable("configuracion", spDataConfigurationService.getConfiguracion());
    		
    	    File logo = new File(spDataConfigurationService.getPathCompanyAttach()+"//logo.jpg");
    	    if (logo.exists()) {
	    	    String logoB64 = java.util.Base64.getEncoder().encodeToString(Files.readAllBytes(logo.toPath()));
	    	    thymeleafContext.setVariable("logo",logoB64);
    	    }

    	    
            String htmlOrderToPdf = thymeleafTemplateEngine.process("FacturaVentaPdf.html", thymeleafContext);

            ITextRenderer renderer = new ITextRenderer();
            renderer.getFontResolver().addFont("fonts/Roboto-Regular.ttf", BaseFont.IDENTITY_H, true);
            renderer.getFontResolver().addFont("fonts/Roboto-Bold.ttf", BaseFont.IDENTITY_H, true);
            renderer.setDocumentFromString(htmlOrderToPdf);
            renderer.layout();

            renderer.createPDF(outPdf);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	base64Pdf = java.util.Base64.getEncoder().encodeToString(outPdf.toByteArray());
        }
        
		return base64Pdf;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public String test(Long facturaId) throws Exception{
		String htmlOrderToPdf="";
	

        
        try {
        	
        	FacturaVenta facturaVenta = facturaVentaCabDao.findByIdBig(facturaId);
    		MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, java.nio.charset.StandardCharsets.UTF_8.name());

            Context thymeleafContext = new Context();
    	    thymeleafContext.setVariable("factura", facturaVenta);
    	    thymeleafContext.setVariable("configuracion", spDataConfigurationService.getConfiguracion());
    		
    	    File logo = new File(spDataConfigurationService.getPathCompanyAttach()+"//logo.jpg");
    	    if (logo.exists()) {
        	    String logoB64 = java.util.Base64.getEncoder().encodeToString(Files.readAllBytes(logo.toPath()));
        	    thymeleafContext.setVariable("logo",logoB64);
    	    }

    	    
    	    htmlOrderToPdf = thymeleafTemplateEngine.process("MailFactura.html", thymeleafContext);

            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        }
        
		return htmlOrderToPdf;
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public void sendFacturaPdf(Long facturaId,String emails) throws Exception {
		
		FacturaVenta facturaVenta = facturaVentaCabDao.findByIdBig(facturaId);
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, java.nio.charset.StandardCharsets.UTF_8.name());

        
        Context thymeleafContext = new Context();
	    thymeleafContext.setVariable("factura", facturaVenta);
	    thymeleafContext.setVariable("configuracion", spDataConfigurationService.getConfiguracion());


	    
        
        
        String facturaB64 = getFacturaVentaPdfB64(facturaId);
        ByteArrayResource resource = new ByteArrayResource( Base64.getDecoder().decode(facturaB64) );
        mimeMessageHelper.addAttachment("factura-"+ facturaVenta.getNo() +".pdf", resource);
                mimeMessageHelper.setFrom(spDataConfigurationService.getSmtpConfiguration().getMailSender());
        
        if (emails.contains("@"))
    		mimeMessageHelper.setTo(InternetAddress.parse(emails.replace(" ", "").replace(";", ",") ));
        else
        	new PersonalException("Sin email de entrega");
        mimeMessageHelper.setSubject("Nueva factura  "+facturaVenta.getNo()); 
        

        
        String htmlbodyMail = thymeleafTemplateEngine.process("MailFactura.html", thymeleafContext);
        mimeMessageHelper.setText(htmlbodyMail,true);

        
	    File logo = new File(spDataConfigurationService.getPathCompanyAttach()+"//logo.jpg");
	    if (logo.exists()) {
	    	mimeMessageHelper.addInline("logo",logo);
	    }

    	mailSender.send(message);
        
	}
}
