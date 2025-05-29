package com.Bridgelabz.DigitalSupplyChainTracker.service.User;

import java.util.Optional;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserResponseDto;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginResponseDto;


@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired UserRepository userrepository;

	@Override
	public ResponseEntity<?> RegisterUser(UserRequestDto user) {
		User newUser = new User(user);
		
		userrepository.save(newUser);
		UserResponseDto userresponse = new UserResponseDto(newUser);
		return new ResponseEntity<>(userresponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> LoginUser(LoginRequestDto loginuser) {
		Optional<User> optionaluser= userrepository.findByEmail(loginuser.getEmail());
		if(optionaluser.isEmpty()) {
			return new ResponseEntity<>("User Doesn't Exist or Invalid Emain", HttpStatus.UNAUTHORIZED);
		}
		User searchUser = optionaluser.get();
		if(!searchUser.getPassword().equals(loginuser.getPassword())) {
			return new ResponseEntity<>("Incorrect Password", HttpStatus.UNAUTHORIZED);
		}
		LoginResponseDto loginResponse = new LoginResponseDto(searchUser);
		return new ResponseEntity<>(loginResponse, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<>(userrepository.findAll(), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<?> updateUserRole(int id,Role role) {
		Optional<User> optionalUser = userrepository.findById(id);
		if (optionalUser.isPresent()) {
			User user=optionalUser.get();
			user.setRole(role);
			userrepository.save(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("WRONG USER ACCESS",HttpStatus.OK);
		}
		
	}
	@Override
	public ResponseEntity<?> deleteUser(int id){
		Optional<User> optionalUser=userrepository.findById(id);
		if(optionalUser.isPresent()) {
			User user=optionalUser.get();
			userrepository.deleteById(user.getId());
			return new ResponseEntity<>(userrepository.findAll(),HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("WRONG ID!!! CANNOT BE DELETED",HttpStatus.OK);
			
		}
	}
	
	

}
