package com.example.demo;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomeResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object>handleUserNoFoundException(UserNotFoundException ex, WebRequest request) {

		 ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(),ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity<Object>(exceptionMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object>handlelAllException(Exception ex, WebRequest request) {

		 ExceptionMessage exceptionMessage = new ExceptionMessage(new Date(),ex.getMessage(),request.getDescription(false));
		 return new ResponseEntity<Object>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionMessage message = new ExceptionMessage(new Date(), "Validation fail", ex.getBindingResult().toString());
		return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
	}

}
