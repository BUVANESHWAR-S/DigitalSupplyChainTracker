package com.Bridgelabz.DigitalSupplyChainTracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHnadler {
	
//	@ExceptionHandler(InvalidRoleException.class)
//	public ResponseEntity<String> HandlingRoleException(InvalidRoleException exception){
//		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//	}
	@ExceptionHandler(ShipmentNotFoundException.class)
	public ResponseEntity<String> HandlingShipmentNotFoundException(ShipmentNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> HandlingIdNotFoundException(IdNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}

}
