package com.vallete.portfolio.backendjava.controller;

import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.User;
import com.vallete.portfolio.backendjava.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserController implements UserControllerInterface {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;


//	public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//	}

	@Override
	public User authenticate(String email, String password) {
		
		Optional<User> user = userRepository.findByEmail(email);

		if (!user.isPresent())
			throw new AuthenticationException("This email is not registered yet.");

		if (!passwordEncoder.matches(password, user.get().getPassword()))
			throw new AuthenticationException("Email and password don't match.");

		return user.get();
		
	}

	@Override
	@Transactional
	public User saveUser(User user) {

		validateEmail(user.getEmail());
		encryptPassword(user);
		return userRepository.save(user);

	}
	
	private void encryptPassword(User user) {
		
		String regularPassword = user.getPassword();
		String encryptedPassword = passwordEncoder.encode(regularPassword);
		user.setPassword(encryptedPassword);
		
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

	@Override
	public User getUserByEmail(String email){
		return userRepository.getUserByEmail(email);
	}
}
