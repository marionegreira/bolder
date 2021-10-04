package com.bolder.server.models.service;

import java.util.List;

import com.bolder.server.models.dto.UsuarioEditDto;
import com.bolder.server.models.dto.UsuarioListDto;
import com.bolder.server.models.entity.Usuario;

public interface IUsuarioService {
	
	public UsuarioEditDto save(Usuario centro) throws Exception;
	public Usuario patch(UsuarioEditDto dto, Long id) throws Exception;
	public Usuario mePatch(UsuarioEditDto dto) throws Exception;
	public void delete(Long id) throws Exception;
	public UsuarioEditDto findOne(Long id) throws Exception;
	public void save(Usuario usuario, String usernameActual) throws Exception;
	public void changePassword(String password, String rePassword, long userId) throws Exception;
	public String getPassword(String username) throws Exception;
	public UsuarioEditDto getMe() throws Exception;
	public List<UsuarioListDto> findAllNotMe() throws Exception;
	public void meChangePass(String oldPassword, String NewPassword, String rePassword) throws Exception;
}
