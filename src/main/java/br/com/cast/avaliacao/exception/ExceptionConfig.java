package br.com.cast.avaliacao.exception;

import java.io.Serializable;

import org.apache.coyote.Request;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler({
		RuntimeException.class
	})
	public ResponseEntity<Object> runtimeException(Exception ex) {
		return new ResponseEntity<>(new ExceptionError(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({
		EmptyResultDataAccessException.class
	})
	public ResponseEntity<Object> errorNotFound(Exception ex) {
		return new ResponseEntity<>(new ExceptionError(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler({
		IllegalArgumentException.class
	})
	public ResponseEntity<Object> errorBadRequest(Exception ex) {
		return new ResponseEntity<>(new ExceptionError(ex.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(new ExceptionError("Operação não permitida"), HttpStatus.METHOD_NOT_ALLOWED);
	}
}

class ExceptionError implements Serializable{
	private static final long serialVersionUID = 3987166208274599884L;

	private String error;
	ExceptionError(String error){
		this.error = error;
	}
	public String getError() {
		return error;
	}
}