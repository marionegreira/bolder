package com.bolder.server.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bolder.server.models.entity.Empresa;
import com.bolder.server.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername(String username);
	
	public Usuario findFirstByUsername(String username);
	
	@Query(value="select u.password from Usuario u where u.username=:username LIMIT 1", nativeQuery = true)
	public String getPassword(String username);
	
	@Modifying
	@Query(value="UPDATE Usuario SET password=:password WHERE id=:userId",nativeQuery = true)
	public void  changePassword (String password, Long userId);	
	
	
	@Query("SELECT u FROM Usuario u WHERE u.id<>:meId")
	public List<Usuario> findAllNotMe(Long meId);
	
	@Query(value="SELECT u.id FROM Usuario u LIMIT 1",nativeQuery = true)
	public Long findFirst();
}
