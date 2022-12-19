package com.vallete.portfolio.backendjava.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.User;
import com.vallete.portfolio.backendjava.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserController implements UserControllerInterface {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User authenticate(String email, String password) {
		Optional<User> user = userRepository.findByEmail(email);

		if (!user.isPresent())
			throw new AuthenticationException("This email is not registered yet.");

		if (!user.get().getPassword().equals(password))
			throw new AuthenticationException("Email and password don't match.");

		return user.get();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		validateEmail(user.getEmail());
		return userRepository.save(user);
	}

	@Override
	public void validateEmail(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new BusinessException("There is already one user registered using this email.");
		}
	}

	@Override
	public User getUserById(UUID id) {
		return userRepository.getUserById(id);
	}
	

}
