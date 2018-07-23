package com.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlingController {
	 
	    @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ExceptionResponse> resourceNotFound(UserNotFoundException ex) {
	        ExceptionResponse response = new ExceptionResponse();
	        response.setErrorCode("Not Found");
	        response.setErrorMessage(ex.getMessage());
	 
	  
	        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	    }

	   
	}


