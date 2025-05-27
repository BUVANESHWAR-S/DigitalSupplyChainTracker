package com.Bridgelabz.DigitalSupplyChainTracker.service.User;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;



@Service
public interface UserService {
	public ResponseEntity<?> RegisterUser(UserRequestDto user);
	public ResponseEntity<?> LoginUser(LoginRequestDto user);
}
