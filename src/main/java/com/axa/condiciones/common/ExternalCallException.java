package com.axa.condiciones.common;

public class ExternalCallException extends GenericException{
	private static final long serialVersionUID = -6863180167151414596L;

	public ExternalCallException(String errorMessage) {
		super(errorMessage);
	}

	public ExternalCallException(String errorMessage, Throwable cause) {
		super(errorMessage, cause);
	}
}
