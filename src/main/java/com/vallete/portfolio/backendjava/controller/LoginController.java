package com.vallete.portfolio.backendjava.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vallete.portfolio.backendjava.dto.LoginDTO;
import com.vallete.portfolio.backendjava.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.entity.Login;
import com.vallete.portfolio.backendjava.service.LoginService;
import com.vallete.portfolio.backendjava.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;
	private final TransactionService transactionService;

	//public LoginController(LoginService loginService) {
	//	this.loginService = loginService;
	//	this.transactionService = transactionService;
	//}

	@GetMapping
	public ResponseEntity getMapping() {
		return null;
	}
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
		try {
			Login authenticatedLogin = loginService.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
			return ResponseEntity.ok(authenticatedLogin);
		}catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage()); 
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody LoginDTO loginDTO) {
		Login login = Login.builder()
				.name(loginDTO.getName())
				.email(loginDTO.getEmail())
				.password(loginDTO.getPassword()).build();
		
		try {
			return new ResponseEntity(loginService.saveLogin(login), HttpStatusCode.valueOf(200));
		}catch(BusinessException e){
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("/login/{idLogin}/balance")
	public ResponseEntity getBalance(@PathVariable("idLogin") String idLogin) {
		BigDecimal balance = transactionService.getBalanceByUserAndType(UUID.fromString(idLogin));
		return ResponseEntity.ok(balance);
	}
}
