package com.Bridgelabz.DigitalSupplyChainTracker.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHnadler {
	
	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<String> HandlingRoleException(InvalidRoleException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ShipmentNotFoundException.class)
	public ResponseEntity<String> HandlingShipmentNotFoundException(ShipmentNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> HandlingIdNotFoundException(IdNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<String> HandlingIncorrectPasswordException(IncorrectPasswordException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> HandlingUserNotFoundException(UserNotFoundException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<String> HandlingInvalidIdException(InvalidIdException exception){
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	

}
