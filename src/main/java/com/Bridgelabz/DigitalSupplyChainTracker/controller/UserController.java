package com.Bridgelabz.DigitalSupplyChainTracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;


import com.Bridgelabz.DigitalSupplyChainTracker.service.User.UserService;
import java.util.*;

import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;

@RestController

public class UserController {
	@Autowired 
	UserService userservice;
	@GetMapping("/api/users")
	public ResponseEntity<List<User>> getAllUsers(){
		return userservice.getAllUsers();
	}
	
	@PutMapping("api/users/{id}/role")
	public ResponseEntity<?> updateRole(@PathVariable int id,@RequestParam Role role) {
		return userservice.updateUserRole(id,role);
	}
	
	@DeleteMapping("api/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		return userservice.deleteUser(id);
	}
	
	//karun
	
	
}
	
