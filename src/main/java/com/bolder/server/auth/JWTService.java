package com.bolder.server.auth;

import org.springframework.security.core.Authentication;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.jsonwebtoken.Claims;

public interface JWTService {

	public String createTokenUser(Authentication auth, Long userId) throws JsonProcessingException;
	public String createTokenMobile(Authentication auth,Long idCliente,Long dispositivoid) throws JsonProcessingException;
	public Claims getUserWebClaims(String token);
	public Claims getUserMobileClaims(String token);
	public String getUsername();
	boolean requiresAuthentication(String token);
	public Long getClienteId();
	public Long getDispositivoId();
	public Long getUserId();
}
