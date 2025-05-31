package com.Bridgelabz.DigitalSupplyChainTracker.service.User;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.LoginDto.LoginResponseDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserResponseDto;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;
import com.Bridgelabz.DigitalSupplyChainTracker.repository.UserRepository;
import com.Bridgelabz.DigitalSupplyChainTracker.service.User.UserServiceImplementation;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.IncorrectPasswordException;
import com.Bridgelabz.DigitalSupplyChainTracker.Exception.UserNotFoundException;
import com.Bridgelabz.DigitalSupplyChainTracker.Utility.Role;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImplementation userService;

    @Test
    void testRegisterUser() {
        UserRequestDto request = new UserRequestDto();
        request.setName("Alice");
        request.setEmail("alice@example.com");
        request.setPassword("Password@123");
        request.setRole(Role.Admin);

        User savedUser = new User(request);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        ResponseEntity<?> response = userService.RegisterUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        UserResponseDto responseBody = (UserResponseDto) response.getBody();
        assertNotNull(responseBody);
        assertEquals("alice@example.com", responseBody.getEmail());
        assertEquals("Alice", responseBody.getName());
        assertEquals(Role.Admin, responseBody.getRole());
    }

    @Test
    void testLoginUser() {
        LoginRequestDto request = new LoginRequestDto();
        request.setEmail("bob@example.com");
        request.setPassword("Secret@123");

        User mockUser = new User();
        mockUser.setId(1);
        mockUser.setName("Bob");
        mockUser.setEmail("bob@example.com");
        mockUser.setPassword("Secret@123");
        mockUser.setRole(Role.Supplier);

        when(userRepository.findByEmail("bob@example.com")).thenReturn(Optional.of(mockUser));

        ResponseEntity<?> response = userService.LoginUser(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        LoginResponseDto responseBody = (LoginResponseDto) response.getBody();
        assertNotNull(responseBody);
        assertEquals("Bob", responseBody.getName());
        assertEquals(Role.Supplier, responseBody.getRole());
        assertEquals(1, responseBody.getId());
    }

    @Test
    void testLoginUser_InvalidEmail() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("invalid@example.com");
        loginRequestDto.setPassword("Password@123");

        when(userRepository.findByEmail("invalid@example.com")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.LoginUser(loginRequestDto);
        });
    }


    @Test
    void testLoginUser_InvalidPassword() {
        // Arrange
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("test@example.com");
        loginRequestDto.setPassword("WrongPassword@123");

        User testUser = new User();
        testUser.setId(1);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setPassword("CorrectPassword@123");
        testUser.setRole(Role.Admin);

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(testUser));

        // Act & Assert
        assertThrows(IncorrectPasswordException.class, () -> {
            userService.LoginUser(loginRequestDto);
        });
    }

}
