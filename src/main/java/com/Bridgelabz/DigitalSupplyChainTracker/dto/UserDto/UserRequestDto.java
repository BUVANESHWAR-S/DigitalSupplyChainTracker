package com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto;

import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class UserRequestDto {
	
	private String name;
	
	@NotBlank(message = "Email Cannot Be Empty!!!!")
	@Pattern(
		    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$",
		    message = "Invalid email format"
		)	
	private String email;
	
	@NotBlank(message = "Password Cannot Be Empty!!!!")
	@Pattern(
		    regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$",
		    message = "Password must contain at least 8 characters, 1 uppercase, 1 lowercase, 1 digit, and 1 special character"
		)
	private String password;
	
	
	private Role role;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	

}
