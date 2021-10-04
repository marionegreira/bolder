package com.bolder.server.models.service;

import org.springframework.web.multipart.MultipartFile;

import com.bolder.server.models.dto.ConectorSmtpDto;
import com.bolder.server.models.dto.EmpresaEditDto;
import com.bolder.server.models.entity.Empresa;

public interface IEmpresaService {
	public EmpresaEditDto findFirst() throws Exception;
	public EmpresaEditDto save(EmpresaEditDto configuracionEditDto) throws Exception;
	public Empresa patch(EmpresaEditDto dto) throws Exception;
	public void textStmp(ConectorSmtpDto conectorSmtpDto, String email) throws Exception;
	public void uploadFile(MultipartFile multipartFile, String tipo) throws Exception;
	public void updateBean() throws Exception;
}
