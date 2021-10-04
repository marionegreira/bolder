package com.bolder.server.util;

import java.beans.PropertyDescriptor;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.bolder.server.models.dto.ClienteEditDto;
import com.bolder.server.models.dto.UsuarioEditDto;
import com.bolder.server.models.entity.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {

	
	public String getUuidNumbers(int size) {
		
		SecureRandom sr = new SecureRandom();
		String key="";
		for(int i=0; i<size; i++) {
			String a = sr.nextInt()+"";
			key +=a.substring(a.length() - 1);
		}
		return key;
	}
	
    public String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<>();
	    for(PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue != null) emptyNames.add(pd.getName());
	    }
	    return emptyNames.toArray(new String[0]);
	}
    
    @SuppressWarnings("unchecked")
	public Object mergeData(Object now,Object updateDto) throws Exception{
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
    		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    		
    		Object  nowDto = new ModelMapper().map(now, updateDto.getClass());
			BeanUtils.copyProperties(nowDto,updateDto, new Util().getNullPropertyNames(updateDto));
			return new ModelMapper().map(updateDto, now.getClass());
			
		} catch (Exception e) {
			throw new Exception();
		}
    	
    }
}
