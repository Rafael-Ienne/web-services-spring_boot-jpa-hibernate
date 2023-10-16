package com.educandoweb.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	/*O ResourceNotFoundException é uma excessão personalizada da camada de serviço*/
	public ResourceNotFoundException(Object id) {
		/*O super é paa chamar o construtor da superclasse*/
		super("Resource not found. Id " + id);
	}

}
