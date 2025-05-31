package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.service.User.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import jakarta.validation.Valid;


@RestController
public class AuthController {
	@Autowired 
	UserService userservice;
	
	
	@PostMapping("/api/auth/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserRequestDto user, BindingResult bindingresult){
		if (bindingresult.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
			for(FieldError error: bindingresult.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		return userservice.RegisterUser(user);
	}
	@PostMapping("/api/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginuser){
		return userservice.LoginUser(loginuser);
	}
}
