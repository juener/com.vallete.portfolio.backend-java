package com.vallete.portfolio.backendjava.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.vallete.portfolio.backendjava.model.User;

//integration test
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

//	@Autowired
//    DELETEUserRepository userRepository;
	
//	@Test
//	public void mustReturnTrueIfTheEmailExists() {
//		//scenario
//		User user= User.builder().name("nameTst").email("test@testLoginRepository.com").build();
//
//		//execution
//		userRepository.save(user);
//		boolean result = userRepository.existsByEmail("test@testLoginRepository.com");
//
//		//verification
//		Assertions.assertThat(result).isTrue();
//	}
}
