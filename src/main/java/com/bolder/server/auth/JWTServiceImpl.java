package com.bolder.server.auth;

import java.util.Collection;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bolder.server.system.service.SpDataConfigurationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements JWTService {

	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private SpDataConfigurationService spDataConfigurationService;
	
	//public static final String SECRET = Base64Utils.encodeToString("jg89745u;37kjmg*78h#mvc9%mf672".getBytes());
	//public static final long EXPIRATION_DATE = System.currentTimeMillis()+10000000;
	public static final long EXPIRATION_TIME = 86400000L;
	//public static final long EXPIRATION_TIME = 1200000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	

	
	
	@Override
	public String createTokenUser(Authentication auth, Long userId) throws JsonProcessingException {
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		claims.put("userId", userId);
		
        String username = auth.getName();
        String token = Jwts.builder()
        		.setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, spDataConfigurationService.getSecretUserWeb())
                .compact();
        return token;
	}
	
	@Override
	public String createTokenMobile(Authentication auth,Long idCliente,Long dispositivoid) throws JsonProcessingException {
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
		Claims claims = Jwts.claims();
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		claims.put("idcliente", idCliente);
		claims.put("dispositivoid", dispositivoid);
		
        String username = auth.getName();
        String token = Jwts.builder()
        		.setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, spDataConfigurationService.getSecretMobile())
                .compact();
        return token;
	}

	@Override
	public Long getClienteId() {
		String token = request.getHeader("Authorization");
		Claims claims = getUserMobileClaims(token);
		return new Long(claims.get("idcliente",Long.class));
	}
	
	@Override
	public Long getDispositivoId() {
		String token = request.getHeader("Authorization");
		if (token!=null) {
			Claims claims = getUserMobileClaims(token);
			return claims.get("dispositivoid",Long.class);
		}else {
			return null;
		}
	}
	@Override
	public Long getUserId() {
		String token = request.getHeader("Authorization");
		Claims claims = getUserWebClaims(token);
		return claims.get("userId",Long.class);
	}
	
	@Override
	public String getUsername() {
		String token = request.getHeader("Authorization");
		return getUserMobileClaims(token).getSubject();
	}

	@Override
	public Claims getUserWebClaims(String token) {
		Claims claims=null;
		claims = Jwts.parser()
		        .setSigningKey(spDataConfigurationService.getSecretUserWeb())
		        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
		        .getBody(); //devuelve los tokens
		return claims;
	}
	
	@Override
	public Claims getUserMobileClaims(String token) {
		Claims claims=null;
		claims = Jwts.parser()
		        .setSigningKey(spDataConfigurationService.getSecretMobile())
		        .parseClaimsJws(token.replace(TOKEN_PREFIX, "")) //este metodo es el que valida
		        .getBody(); //devuelve los tokens
		return claims;
	}

	
	@Override
	public boolean requiresAuthentication(String token) {

		if (token == null || !token.startsWith(TOKEN_PREFIX)) {
			return false;
		}
		return true;
	}

}
