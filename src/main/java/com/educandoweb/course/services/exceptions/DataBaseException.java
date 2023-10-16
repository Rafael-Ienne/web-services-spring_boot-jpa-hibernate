package com.educandoweb.course.services.exceptions;

public class DataBaseException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/*A DataBaseException é uma excessão personalizada da camada de serviço*/
	public DataBaseException(String msg) {
		super(msg);
	}

}
