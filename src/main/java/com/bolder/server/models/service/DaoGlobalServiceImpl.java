package com.bolder.server.models.service;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DaoGlobalServiceImpl implements IDaoGlobalService{
	
    @PersistenceContext
    private EntityManager em;
    

	@Override
	public <T> int patchFORDELETE(Class<T> cls,String body, Long id) throws Exception{
		/*
		Map<String, Object> bodyMap = new ObjectMapper().readValue(body, HashMap.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<T> update = (CriteriaUpdate<T>) cb.createCriteriaUpdate(cls);
		Root<T> root = update.from(cls);
		int countSet=0;
		for (Field field : cls.getDeclaredFields()) {
			
			boolean setValue=true;
			for (Annotation obj: field.getAnnotations()) {
				if ( obj.annotationType().equals(javax.persistence.Id.class) ||
					 //obj.annotationType().equals(javax.persistence.JoinColumn.class) ||
					 obj.annotationType().equals(javax.persistence.OneToMany.class)  ||
					 obj.annotationType().equals(com.bolder.server.util.NotPacheable.class) 
				   ) 
					setValue=false;
			}
			if ( Arrays.stream(field.getAnnotations()).anyMatch(obj -> obj.annotationType().equals(javax.persistence.JoinColumn.class))) {
				if (bodyMap.containsKey(field.getName()+"Id") && setValue && !Modifier.isStatic(field.getModifiers())) {
					update.set(field.getName(), bodyMap.get( field.getName()+"Id" ));
					countSet+=1;
				}				
				//update.set(field.getName(), bodyMap.get( field.getName() ));
			}else {
				if (bodyMap.containsKey(field.getName()) && setValue && !Modifier.isStatic(field.getModifiers())) {
					update.set(field.getName(), bodyMap.get( field.getName() ));
					countSet+=1;
				}
			}
		}
		
		if (countSet>0) {
			update.where(cb.equal(root.get("id"), id));
			return em.createQuery(update).executeUpdate();	
		}else {
			return 0;
		}
		
		*/
		return 0;
	}
	
	
		
	@SuppressWarnings("unchecked")
	@Override
	public Object getValueFromPatchField(String nameField, String bodyPatch) throws Exception{
		Map<String, Object> bodyMap = new ObjectMapper().readValue(bodyPatch, HashMap.class);
		if ( bodyMap.containsKey(nameField) ) 
			return bodyMap.get( nameField );
			
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean existField(String nameField, String bodyPatch) throws Exception{
		Map<String, Object> bodyMap = new ObjectMapper().readValue(bodyPatch, HashMap.class);
		if ( bodyMap.containsKey(nameField) ) 
			return true;
		else	
			return false;
	}


}
