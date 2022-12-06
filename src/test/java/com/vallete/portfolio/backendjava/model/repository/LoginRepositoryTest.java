package com.vallete.portfolio.backendjava.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vallete.portfolio.backendjava.model.entity.Login;

//integration test
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoginRepositoryTest {

	@Autowired
	LoginRepository loginRepository;
	
	@Test
	public void mustReturnTrueIfTheEmailExists() {
		//scenario
		Login login = Login.builder().name("nameTst").email("test@testLoginRepository.com").build();
		
		//execution
		loginRepository.save(login);
		boolean result = loginRepository.existsByEmail("test@testLoginRepository.com");
		
		//verification
		Assertions.assertThat(result).isTrue();
	}
}
