package com.Bridgelabz.DigitalSupplyChainTracker.service.User;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;

import java.util.*;

@Service
public interface UserService {
	public ResponseEntity<?> RegisterUser(UserRequestDto user);
	public ResponseEntity<?> LoginUser(LoginRequestDto user);
	public ResponseEntity<List<User>> getAllUsers();
	public ResponseEntity<?> updateUserRole(int id,Role role);
	public ResponseEntity<?> deleteUser(int id);
	
}
