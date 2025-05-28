package com.Bridgelabz.DigitalSupplyChainTracker.service.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	

}
