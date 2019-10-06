package com.axa.condiciones.common;

public class GenericException extends RuntimeException{

	private static final long serialVersionUID = -8459322137419688262L;
	
	public GenericException(String errorMessage) {
		super(errorMessage);
	}

	public GenericException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}

}
