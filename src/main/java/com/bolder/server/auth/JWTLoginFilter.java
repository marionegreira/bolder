package com.bolder.server.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bolder.server.models.dto.UserPassDto;


public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter  {

	//private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	

	
    public JWTLoginFilter(String url, AuthenticationManager authManager,JWTService jwtService) {
		this.authenticationManager = authManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(url, "POST"));
		this.jwtService = jwtService;
    }
    
    
    
    @Override
    
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException{

    	
    	UserPassDto user =null;
    	try {
	        InputStream body = req.getInputStream();
	        user = new ObjectMapper().readValue(body, UserPassDto.class);
    	} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String username = user.getUsername();
		String password = user.getPassword();
		username = username.trim();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authToken);
		
    }

    @Override
	@Transactional
    protected void successfulAuthentication( HttpServletRequest req, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
    	String token = "";
    	Map<String, Object> body = new HashMap<String,Object>();

        
        	try {
        		Long userId = (Long)req.getSession().getAttribute("userId") ;
            	token = jwtService.createTokenUser(auth,userId);
            	response.setStatus(200);

        	} catch (Exception e) {
        		body.put("error inserccion", "Intentelo mas tarde");
        		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        		response.setStatus(400);
        		e.printStackTrace();
        		return;
        	}
        	

       
        //agregamos al encabezado el token
        response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

    }

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
		response.getWriter().write(new ObjectMapper().writeValueAsString("Error de autenticacion: username o password incorrecto"));
		response.setStatus(401);
		response.setContentType("application/json");
	}
    
}

