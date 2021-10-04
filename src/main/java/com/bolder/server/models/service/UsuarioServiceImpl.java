package com.bolder.server.models.service;

import java.lang.reflect.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolder.server.auth.JWTService;
import com.bolder.server.models.dao.IUsuarioDao;
import com.bolder.server.models.dto.UsuarioEditDto;
import com.bolder.server.models.dto.UsuarioListDto;
import com.bolder.server.models.entity.Usuario;
import com.bolder.server.util.PersonalException;
import com.bolder.server.util.Util;


@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
    @PersistenceContext
    private EntityManager em;
	
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	BCryptPasswordEncoder  passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	
	@Override
	@Transactional(readOnly=true)
	public List<UsuarioListDto> findAllNotMe() throws Exception{
		List<Usuario> usuario = usuarioDao.findAllNotMe(jwtService.getUserId());
		
		Type listType = new TypeToken<List<UsuarioListDto>>(){}.getType();
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		List<UsuarioListDto> usuarioDto= modelMapper.map(usuario, listType);
		return usuarioDto;
	}	
	
	
	
	@Override
	@Transactional(readOnly=true)
	public UsuarioEditDto findOne(Long id) throws Exception{
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		ModelMapper modelMapper = new ModelMapper();
		UsuarioEditDto usuarioDto= modelMapper.map(usuario, UsuarioEditDto.class);
		return usuarioDto;
	}
	@Override
	@Transactional(readOnly=true)
	public UsuarioEditDto getMe() throws Exception{
		
		Usuario usuario = usuarioDao.findById( jwtService.getUserId() ).orElse(null);
		UsuarioEditDto usuarioDto= new ModelMapper().map(usuario, UsuarioEditDto.class);
		return usuarioDto;
	}
	

	@Override
	@Transactional
	public Usuario patch(UsuarioEditDto dto, Long id) throws Exception{
		Usuario usuario = usuarioDao.findById(id).orElse(null);
		if (usuario==null) 
			throw new PersonalException("No existe el usuario");
		Usuario usuarioPached = (Usuario)new Util().mergeData(usuario, dto);
		return usuarioDao.save(usuarioPached);

	}
	@Override
	@Transactional
	public Usuario mePatch(UsuarioEditDto dto) throws Exception{
		Usuario usuario = usuarioDao.findById(jwtService.getUserId()).orElse(null);
		if (usuario==null) 
			throw new PersonalException("No existe el usuario");
		Usuario usuarioPached = (Usuario)new Util().mergeData(usuario, dto);
		
		return usuarioDao.save(usuarioPached);
	}
	
	
	@Override
	@Transactional
	public void meChangePass(String oldPassword, String newPassword, String rePassword) throws Exception{
		Usuario me = usuarioDao.findById(jwtService.getUserId()).orElse(null);
		if (me==null)
			throw new PersonalException("Error inesperado");
		
		if (!passwordEncoder.matches(oldPassword, me.getPassword()))
			throw new PersonalException("La contraseña actual no coincide");
		
		if (newPassword=="")
			throw new PersonalException("Debe indicar una contraseña");
		if (!newPassword.equals(rePassword))
			throw new PersonalException("Las contraseñas deben ser iguales");	
		
		
		usuarioDao.changePassword( passwordEncoder.encode(newPassword) , jwtService.getUserId() );
	}
	
	@Override
	@Transactional
	public void changePassword(String newPassword, String rePassword, long userId) throws Exception{
		if (newPassword=="")
			throw new PersonalException("Debe indicar una contraseña");
		if (!newPassword.equals(rePassword))
			throw new PersonalException("Las contraseñas deben ser iguales");		
		usuarioDao.changePassword(passwordEncoder.encode(newPassword), userId );	
	}
	
	
	
	
	

	@Override
	@Transactional
	public void delete(Long id) throws Exception {
		usuarioDao.deleteById(id);
	}
	
	@Override
	@Transactional
	public UsuarioEditDto save(Usuario usuario) throws Exception {
		if (usuario.getPassword().length()==0)
			throw new PersonalException("Debe indicar una contraseña");
		
		usuario.setPassword( passwordEncoder.encode(usuario.getPassword()) );
		Usuario newUsuario =usuarioDao.save(usuario);
		UsuarioEditDto usuarioDto= new ModelMapper().map(newUsuario, UsuarioEditDto.class);
		return usuarioDto;
	}

	@Override
	@Transactional
	public void save(Usuario usuario, String usernameActual) throws Exception {
		if (usuario.getPassword()==null)
			throw new PersonalException("Envie una contraseña en blaco si no quiere actualizarla");
		
		if ( usuario.getPassword()!=null && usuario.getPassword().length()<7 &&  usuario.getPassword().length()>0)
			throw new PersonalException("La contraseña tiene que ser mayor de 6 caracteres");
		
		if (usuario.getId()!=null && usuario.getId()!=0) {
			Usuario oldUsuario = usuarioDao.findById(usuario.getId()).orElse(null);
			if (oldUsuario==null)
				throw new PersonalException("No se puede modificar el usuario");
			if (usuario.getUsername().equals(usernameActual) )
				throw new PersonalException("No se puede modificar el usuario");
			

			if (usuario.getPassword().length()>0)
				usuario.setPassword( passwordEncoder.encode(usuario.getPassword()) );
			else
				usuario.setPassword(oldUsuario.getPassword());
		}else {
			if (usuario.getPassword().length()==0)
				throw new PersonalException("Debe introducir una contraseña");
			usuario.setPassword( passwordEncoder.encode(usuario.getPassword()) );
			Usuario ConMismoUsername = null;
			
			ConMismoUsername = usuarioDao.findByUsername(usuario.getUsername());
			
			if (ConMismoUsername!=null) 
				throw new PersonalException("Ya existe un usuario el nombre \""+usuario.getUsername()+"\"");
		}

			usuarioDao.save(usuario);

	}
	
	/*
	@Override
	@Transactional
	public void changePassword (String username, String password, String NewPass, String RePass) throws Exception {
		String passActual = getPassword( username );
		if (!passwordEncoder.matches(password, passActual))
			throw new PersonalException("La contraseña actual no coincide");

		if ( !NewPass.equals(RePass) )
			throw new PersonalException("La nueva contraseña no coincide");

		if (NewPass.length()<6)
			throw new PersonalException("La contraseña debe tener una longitud mínima de 6 caracteres");

		usuarioDao.changePassword(username, passwordEncoder.encode(NewPass));

	}
	
	
	@Override
	@Transactional
	public void modifyMyProfiless(Usuario usuario, String currUsername) throws Exception {
		Usuario oldUsuario = usuarioDao.findByUsername(currUsername);
		if (oldUsuario==null)
			throw new Exception("No es puede modificar el usuario");

		usuario.setId(oldUsuario.getId());
		usuario.setPassword(oldUsuario.getPassword());
		usuario.setEnabled(oldUsuario.getEnabled());
		usuario.setRole(oldUsuario.getRole());
		usuario.setUsername(oldUsuario.getUsername());
		usuarioDao.save(usuario);
	}
	*/
	

	@Override
	public String getPassword(String username) throws Exception {
		return usuarioDao.getPassword(username);
	}

}
