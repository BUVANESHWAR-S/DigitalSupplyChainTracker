package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.service.User.UserService;


@RestController
public class AuthController {
	@Autowired 
	UserService userservice;
	
	
	@PostMapping("/api/auth/register")
	public ResponseEntity<?> register(@RequestBody UserRequestDto user){

		 return userservice.RegisterUser(user);
	}
	@PostMapping("/api/auth/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto loginuser){
		return userservice.LoginUser(loginuser);
	}
}
