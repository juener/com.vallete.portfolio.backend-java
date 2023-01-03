package com.vallete.portfolio.backendjava.controller;

import java.util.Optional;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.User;

import javax.swing.text.html.Option;

public interface UserControllerInterface {

	User authenticate(String email, String password);
	
	User saveUser(User user);
	
	void validateEmail(String email);
	
	User getUserById(UUID id);

	Optional<User> findById(UUID id);

//	User getUserByEmail(String email);

	Optional<User> findByEmail(String email);
}
