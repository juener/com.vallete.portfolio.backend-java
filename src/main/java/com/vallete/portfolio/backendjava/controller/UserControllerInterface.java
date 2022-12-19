package com.vallete.portfolio.backendjava.controller;

import java.util.Optional;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.User;

public interface UserControllerInterface {

	User authenticate(String email, String password);
	
	User saveUser(User user);
	
	void validateEmail(String email);
	
	User getUserById(UUID id);
}
