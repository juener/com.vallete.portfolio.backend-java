package com.vallete.portfolio.backendjava.service.implementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.entity.Login;
import com.vallete.portfolio.backendjava.model.entity.TransactionType;
import com.vallete.portfolio.backendjava.model.repository.LoginRepository;
import com.vallete.portfolio.backendjava.service.LoginService;

import jakarta.transaction.Transactional;

@Service
public class LoginServiceImplementation implements LoginService {

	private LoginRepository loginRepository;

	public LoginServiceImplementation(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public Login authenticate(String email, String password) {
		Optional<Login> login = loginRepository.findByEmail(email);

		if (!login.isPresent())
			throw new AuthenticationException("This email is not registered yet.");

		if (!login.get().getPassword().equals(password))
			throw new AuthenticationException("Email and password don't match.");

		return login.get();
	}

	@Override
	@Transactional
	public Login saveLogin(Login login) {
		validateEmail(login.getEmail());
		return loginRepository.save(login);
	}

	@Override
	public void validateEmail(String email) {
		if (loginRepository.existsByEmail(email)) {
			throw new BusinessException("There is already one user registered using this email.");
		}
	}

	@Override
	public Login getLoginById(UUID id) {
		return loginRepository.getLoginById(id);
	}
	

}
