package com.vallete.portfolio.backendjava.service;

import com.vallete.portfolio.backendjava.model.entity.Login;

public interface LoginService {

	Login authenticate(String email, String password);
	
	Login saveLogin(Login login);
	
	void validateEmail(String email);
}
