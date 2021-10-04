package com.bolder.server.util;

public class PersonalException extends Exception{
	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public PersonalException(String mensaje) {
		this.mensaje=mensaje;
	}

	@Override
    public String getMessage(){
		return mensaje;
	}
}
