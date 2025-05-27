package com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto;

import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;

public class LoginResponseDto {
	private int id;
	private String name;
	private Role role;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public LoginResponseDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.role = user.getRole();
	}
	
	
}
