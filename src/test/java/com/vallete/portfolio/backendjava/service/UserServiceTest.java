package com.vallete.portfolio.backendjava.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.User;
import com.vallete.portfolio.backendjava.repository.UserRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Autowired
	UserControllerInterface userService;

	@Autowired
	UserRepository userRepository;

	@Test()
	public void mustThrowErrorIfTheEmailIsAlreadyRegistered() {
		Assertions.assertThrows(BusinessException.class, () -> { //or .assertDoesNotThrow
			// scenario
			User user = User.builder().name("name test").email("test@testLoginService2.com").build();
			userRepository.save(user);

			// action
			userService.validateEmail("test@testLoginService2.com");
		});
	}
}
