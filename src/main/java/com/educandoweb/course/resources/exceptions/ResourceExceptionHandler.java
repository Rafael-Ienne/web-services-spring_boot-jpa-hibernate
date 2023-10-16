package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DataBaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/*A annotation @ControllerAdvice é para interceptar as excessões que ocorrerem para que o objeto
  ResourceExceptionHandler realize um possível tratamento*/
@ControllerAdvice
/*A classe ResourceExceptionHandler é para dar o tratamento manual ao erro*/
public class ResourceExceptionHandler {

	/*A annotation @ExceptionHandler intercepta o ResourceNotFoundException*/
	@ExceptionHandler(ResourceNotFoundException.class)
	/*A classe resourceNotFound trata o ResourceNotFoundException*/
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		String error = "Resource not found";
		/*O status NOT_FOUND é o erro 404*/
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		/*O status permite retornar uma resposta com código personalizado*/
		return ResponseEntity.status(status).body(err);

	}
	
	/*A classe database trata a DataBaseException*/
	@ExceptionHandler(DataBaseException.class)
	public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request){
		String error = "DataBase error ";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);

	}
}
