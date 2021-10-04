package com.bolder.server.models.service;

public interface IDaoGlobalService {
	public <T> int patchFORDELETE(Class<T> cls, String body, Long id) throws Exception;
	public Object getValueFromPatchField(String nameField, String bodyPatch) throws Exception;
	public boolean existField(String nameField, String bodyPatch) throws Exception;

}
