package com.bolder.server.models.service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;
import javax.mail.internet.MimeMessage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bolder.server.SPMailSender;
import com.bolder.server.models.dao.IEmpresaDao;
import com.bolder.server.models.dto.EmpresaEditDto;
import com.bolder.server.models.dto.SmtpDto;
import com.bolder.server.models.dto.ConectorSmtpDto;
import com.bolder.server.models.entity.Empresa;
import com.bolder.server.system.entity.SpConfiguracionBean;
import com.bolder.server.system.entity.SpSmtpConfiguration;
import com.bolder.server.system.service.SpDataConfigurationService;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;

@Service
public class EmpresaServiceImpl implements IEmpresaService{
	//private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IEmpresaDao configuracionDao;
	@Autowired
	private IDaoGlobalService daoGlobalService;
	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	@Autowired
	private SPMailSender mailSender;
	@Autowired
	private IEmpresaDao empresaDao;

	
	@Override
	@Transactional(readOnly=true)
	public EmpresaEditDto findFirst() throws Exception{
		Empresa configuracion = configuracionDao.findFirst();
		ModelMapper modelMapper = new ModelMapper();
		EmpresaEditDto configuracionEditDto = modelMapper.map(configuracion, EmpresaEditDto.class);
		
		File logoFile = new File(spDataConfigurationService.getPathCompanyAttach()+"//logo.jpg");
		if (logoFile.exists()) {
			byte[] fileContent = Files.readAllBytes(logoFile.toPath());
			configuracionEditDto.setLogo("data:image/jpeg;base64,"+Base64.encodeBase64String(fileContent));
		}
		File logorFile = new File(spDataConfigurationService.getPathCompanyAttach()+"//logor.jpg");
		if (logorFile.exists()) {
			byte[] fileContent = Files.readAllBytes(logorFile.toPath());
			configuracionEditDto.setLogor("data:image/jpeg;base64,"+Base64.encodeBase64String(fileContent));
		}
	
		return configuracionEditDto;
	}


	@Override
	@Transactional
	public EmpresaEditDto save(EmpresaEditDto configuracionEditDto) throws Exception{
		Empresa configuracionNow = configuracionDao.findFirst();
		if (configuracionNow!=null)
			configuracionEditDto.setId(configuracionNow.getId());
		else
			configuracionEditDto.setId(null);
		Empresa configuracion = new ModelMapper().map(configuracionEditDto, Empresa.class);
		configuracion=configuracionDao.save(configuracion);
		configuracionEditDto = new ModelMapper().map(configuracion, EmpresaEditDto.class);

		return configuracionEditDto;
	}
	
	
	@Override
	public void textStmp(ConectorSmtpDto conectorSmtpDto, String email) throws Exception{
		// atencion si se envia desde smtp de google hay que validarse con contraseña de aplicacion
		// https://support.google.com/accounts/answer/185833?p=InvalidSecondFactor
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
        Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.host", conectorSmtpDto.getHost());
		if (conectorSmtpDto.getPort()==465) {
			props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");			
		}else
			props.put("mail.smtp.port", conectorSmtpDto.getPort());
		
		if (conectorSmtpDto.isSmtpTtls())
			props.put("mail.smtp.starttls.enable", "true");
		if ( conectorSmtpDto.isSmtpAuth() ) {
			props.put("mail.smtp.auth", "true");
		    mailSender.setUsername(conectorSmtpDto.getUsername());
		    mailSender.setPassword(conectorSmtpDto.getPassword());
	    
		}
		else 
			props.put("mail.smtp.auth", "false");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.timeout", 3000);


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

    
        mimeMessageHelper.setFrom(conectorSmtpDto.getMailSender());
        mimeMessageHelper.setTo(email); 
        mimeMessageHelper.setSubject("Test Bolder"); 
        mimeMessageHelper.setText("Test Bolder");
        try {
        	mailSender.send(message);
        }catch (MailSendException e) {
        	throw new PersonalException(e.getMessageExceptions()[0].toString());
        }
	}
	
	
	@Override
	@Transactional
	public Empresa patch(EmpresaEditDto dto) throws Exception{
		
		Empresa configuracion = configuracionDao.findFirst();
		if (configuracion==null) 
			throw new PersonalException("No existe el configuracion");
		Empresa configuracionPached = (Empresa)new Util().mergeData(configuracion, dto);
		configuracionPached.setId(1L);
		configuracionPached = configuracionDao.save(configuracionPached);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String StmpTxt = dto.getSmtpConfiguracion();
		
		if (StmpTxt!=null) {

			SmtpDto smtpDto = objectMapper.readValue(configuracion.getSmtpConfiguracion(), SmtpDto.class);
	        Properties props = mailSender.getJavaMailProperties();
			props.put("mail.smtp.host", smtpDto.getHost() );
			props.put("mail.smtp.port", smtpDto.getPort());
			if (smtpDto.isSmtpTtls())
				props.put("mail.smtp.starttls.enable", "true");
			if ( smtpDto.isSmtpAuth() ) {
				props.put("mail.smtp.auth", "true");
			    mailSender.setUsername(smtpDto.getUsername());
			    mailSender.setPassword(smtpDto.getPassword());
			}
			else 
				props.put("mail.smtp.auth", "false");
			
		}

		if (dto.getLogo()!=null) {
			String b64Logo=dto.getLogo().replace("data:image/jpeg;base64,", "");
		    FileOutputStream fos = new FileOutputStream(new File( spDataConfigurationService.getPathCompanyAttach()+"//logo.jpg" )); 
		    fos.write( Base64.decodeBase64( b64Logo ) ); 
		    fos.close();
		}
		
		return configuracionPached;

	}
	

	
	@Override
	@Transactional
	public void uploadFile(MultipartFile multipartFile, String tipo) throws Exception{
		try {
			File file = new File("");
			switch (tipo) {
			case "logo":
				file = ResourceUtils.getFile(spDataConfigurationService.getPathCompanyAttach()+"/logo.jpg");
				multipartFile.transferTo(new File(file.getAbsolutePath()));
				break;
				
			case "logor":
				file = ResourceUtils.getFile(spDataConfigurationService.getPathCompanyAttach()+"/logor.jpg");
				multipartFile.transferTo(new File(file.getAbsolutePath()));
				break;				

			default:
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	@Override
	@Transactional(readOnly = true)
	public void updateBean() throws Exception{
		//configuracionDao.flush();

		Empresa empresa = configuracionDao.findFirst();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		spDataConfigurationService.setSmtpConfiguration(objectMapper.readValue(empresa.getSmtpConfiguracion(), SpSmtpConfiguration.class));
		
		SpConfiguracionBean configuracionBean = new ModelMapper().map(empresa, SpConfiguracionBean.class) ;
		
		spDataConfigurationService.setConfiguracion( configuracionBean );		
	}
	

}
