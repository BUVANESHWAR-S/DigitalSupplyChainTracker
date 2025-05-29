package com.Bridgelabz.DigitalSupplyChainTracker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Bridgelabz.DigitalSupplyChainTracker.dto.UserDto.UserRequestDto;
import com.Bridgelabz.DigitalSupplyChainTracker.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	//User save(UserRequestDto user);

	Optional<User> findByEmail(String email);

}
