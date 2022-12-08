package com.vallete.portfolio.backendjava.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vallete.portfolio.backendjava.model.entity.Login;
import com.vallete.portfolio.backendjava.model.entity.Transaction;
import com.vallete.portfolio.backendjava.model.entity.TransactionStatus;
import com.vallete.portfolio.backendjava.model.entity.TransactionType;
import com.vallete.portfolio.backendjava.service.LoginService;
import com.vallete.portfolio.backendjava.service.TransactionService;

import lombok.RequiredArgsConstructor;

import com.vallete.portfolio.backendjava.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.exception.BusinessException;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;
	private final LoginService loginService;

	// or change the variables as final and add @RequiredArgsConstructor above the
	// class
//	public TransactionController(TransactionService transactionService) {
//		this.transactionService = transactionService;
//		this.loginService = loginService;
//	}

	@GetMapping("/transaction")
	public ResponseEntity seekTransactionsUsingFilter(
			// for all optionals, we could do @RequestParam java.util.Map<String, String>
			// params
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "observation", required = false) String observation,
			// add more fields
			@RequestParam("login") UUID login // login is required
	) {
		Transaction transactionFilter = new Transaction();
		transactionFilter.setName(name);
		transactionFilter.setObservation(observation);

		Login objLogin = loginService.getLoginById(login);
		transactionFilter.setLogin(objLogin);

		List<Transaction> transactions = transactionService.seek(transactionFilter);
		return ResponseEntity.ok(transactions);
	}

	@PostMapping("/transaction")
	@PutMapping("/transaction")
	public ResponseEntity saveTransaction(@RequestBody TransactionDTO transactionDTO) {
		try {
			Transaction transaction = convertDTO(transactionDTO);
			transaction = transactionService.save(transaction);
			return ResponseEntity.ok(transaction);
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

//	@PutMapping("/transaction")
//	public ResponseEntity update(@PathVariable("id") UUID id, @RequestBody TransactionDTO transactionDTO) {
//		return transactionService.getTransactionById(id).map(obj -> {
//			try {
//				Transaction transaction = convertDTO(transactionDTO);
//				transaction.setId(obj.getId());
//				transactionService.update(transaction);
//				return ResponseEntity.ok(transaction);
//			} catch (BusinessException e) {
//				return ResponseEntity.badRequest().body(e.getMessage());
//			}
//		}).orElseGet(() -> new ResponseEntity("Transaction not found.", HttpStatusCode.valueOf(400)));
//	}

	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity deleteByTransactionId(@PathVariable("transactionId") UUID transactionId) {
		transactionService.deleteByTransactionId(transactionId);
		return ResponseEntity.ok("Transaction has been deleted.");
	}

	private Transaction convertDTO(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();

		transaction.setId(transactionDTO.getId());
		transaction.setName(transactionDTO.getName());
		transaction.setObservation(transactionDTO.getObservation());
		transaction.setValue(transactionDTO.getValue());

		Login login = loginService.getLoginById(transactionDTO.getLogin());
		transaction.setLogin(login);

		if (transactionDTO.getType() != null)
			transaction.setType(TransactionType.valueOf(transactionDTO.getType()));

		if (transactionDTO.getStatus() != null)
			transaction.setStatus(TransactionStatus.valueOf(transactionDTO.getStatus()));

		transaction.setDueDate(transactionDTO.getDueDate());
		transaction.setRegistrationDate(transactionDTO.getRegistrationDate());

		return transaction;
	}
}
