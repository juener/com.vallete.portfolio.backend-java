package com.vallete.portfolio.backendjava.api;

import java.math.BigDecimal;
import java.util.UUID;

import com.vallete.portfolio.backendjava.config.JwtUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import com.vallete.portfolio.backendjava.controller.TransactionControllerInterface;
import com.vallete.portfolio.backendjava.dto.UserDTO;
import com.vallete.portfolio.backendjava.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor // it replaces the constructor if we declare as final the interfaces
public class UserAPI {
	private final AuthenticationManager authenticationManager;
	private final UserControllerInterface userController;
	private final TransactionControllerInterface transactionController;
	private final JwtUtils jwtUtils;
	private final UserDetailsService userDetailsService;

	@GetMapping
	public ResponseEntity getMapping() {
		return null;
	}

	@PostMapping("/user/login")
	public ResponseEntity login(@RequestBody UserDTO userDTO) {
		try {
			User authenticatedLogin = userController.authenticate(userDTO.getEmail(), userDTO.getPassword());
			return ResponseEntity.ok(authenticatedLogin);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/user/authenticate")
	public ResponseEntity authenticate(@RequestBody UserDTO userDTO){
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
		);
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());

		if(userDetails == null){
			return  ResponseEntity.status(404).body("Credentials are forbidden.");
		}

		return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/user/register")
	public ResponseEntity register(@RequestBody UserDTO userDTO) {
		User user = User.builder().name(userDTO.getName()).email(userDTO.getEmail()).password(userDTO.getPassword())
				.build();

		try {
			return new ResponseEntity(userController.saveUser(user), HttpStatusCode.valueOf(200));
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/user/{idUser}/balance")
	public ResponseEntity getBalance(@PathVariable("idUser") String idUser) {
		BigDecimal balance = transactionController.getBalanceByUserAndType(UUID.fromString(idUser));
		return ResponseEntity.ok(balance);
	}
}
