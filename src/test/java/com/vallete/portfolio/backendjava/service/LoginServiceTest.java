package com.vallete.portfolio.backendjava.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.entity.Login;
import com.vallete.portfolio.backendjava.model.repository.LoginRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoginServiceTest {

	@Autowired
	LoginService loginService;

	@Autowired
	LoginRepository loginRepository;

	@Test()
	public void mustThrowErrorIfTheEmailIsAlreadyRegistered() {
		Assertions.assertThrows(BusinessException.class, () -> { //or .assertDoesNotThrow
			// scenario
			Login login = Login.builder().name("name test").email("test@testLoginService2.com").build();
			loginRepository.save(login);

			// action
			loginService.validateEmail("test@testLoginService2.com");
		});
	}
}
