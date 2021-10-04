package com.bolder.server.auth;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JWTService jwtService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,JWTService jwtService) {
		super(authenticationManager);
		this.jwtService=jwtService;
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String token = request.getHeader("Authorization");

		if (!jwtService.requiresAuthentication(token) || request.getRequestURI().startsWith("/out/") ) {
			chain.doFilter(request, response);
			return;
		}
		

		
		Claims claims=null;
		try {
			if  ( request.getRequestURI().startsWith("/mob/") ) 
				claims=jwtService.getUserMobileClaims(token);
			else
				claims=jwtService.getUserWebClaims(token);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(403);response.setHeader("status","error token");
		}
		
		UsernamePasswordAuthenticationToken authenticationToken = null;
		
		if (response.getStatus()==200) {
			String username=claims.getSubject();
			Object roles = claims.get("authorities");
			
			lRol[] rolesArray = new ObjectMapper().readValue(roles.toString(),lRol[].class);
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			for (lRol reg : rolesArray) {
				authorities.add(new SimpleGrantedAuthority(reg.getAuthority()) );
			}
			
			authenticationToken = new UsernamePasswordAuthenticationToken(username,null, authorities);
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			chain.doFilter(request, response);	
					
		}
		


	}
}

class lRol {
    private String authority;
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }
}
