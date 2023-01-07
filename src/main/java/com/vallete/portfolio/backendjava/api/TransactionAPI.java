package com.vallete.portfolio.backendjava.api;

import java.util.List;
import java.util.UUID;

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

import lombok.RequiredArgsConstructor;

import com.vallete.portfolio.backendjava.controller.UserControllerInterface;
import com.vallete.portfolio.backendjava.controller.TransactionControllerInterface;
import com.vallete.portfolio.backendjava.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.User;
import com.vallete.portfolio.backendjava.model.Transaction;
import com.vallete.portfolio.backendjava.model.TransactionStatus;
import com.vallete.portfolio.backendjava.model.TransactionType;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TransactionAPI {

	private final TransactionControllerInterface transactionController;
	private final UserControllerInterface userController;

	@GetMapping("/")
	public ResponseEntity ok(){ return ResponseEntity.ok("ok"); }

    @GetMapping("/ok")
	public ResponseEntity isItWorking() {
		return ResponseEntity.ok("Yeah! That's OK! :)");
	}

	@GetMapping("/transaction")
	public ResponseEntity searchTransactionsUsingFilter(
			// for all optionals, use @RequestParam java.util.Map<String, String> params
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "observation", required = false) String observation,
			// add more fields
			@RequestParam("user") UUID idUser // user is required
	) {
		Transaction transactionFilter = new Transaction();
		transactionFilter.setName(name);
		transactionFilter.setObservation(observation);

		User user = userController.getUserById(idUser);
		transactionFilter.setUser(user);

		List<Transaction> transactions = transactionController.search(transactionFilter);
		return ResponseEntity.ok(transactions);
	}

	@PostMapping("/transaction")
	@PutMapping("/transaction")
	public ResponseEntity saveTransaction(@RequestBody TransactionDTO transactionDTO) {
		try {
			Transaction transaction = convertDTO(transactionDTO);
			transaction = transactionController.save(transaction);
			return ResponseEntity.ok(transaction);
		} catch (BusinessException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/transaction/{transactionId}")
	public ResponseEntity deleteByTransactionId(@PathVariable("transactionId") UUID transactionId) {
		transactionController.deleteByTransactionId(transactionId);
		return ResponseEntity.ok("Transaction has been deleted.");
	}

	private Transaction convertDTO(TransactionDTO transactionDTO) {
		Transaction transaction = new Transaction();

		transaction.setId(transactionDTO.getId());
		transaction.setName(transactionDTO.getName());
		transaction.setObservation(transactionDTO.getObservation());
		transaction.setValue(transactionDTO.getValue());

		User user = userController.getUserById(transactionDTO.getUser());
		transaction.setUser(user);

		if (transactionDTO.getType() != null)
			transaction.setType(TransactionType.valueOf(transactionDTO.getType()));

		if (transactionDTO.getStatus() != null)
			transaction.setStatus(TransactionStatus.valueOf(transactionDTO.getStatus()));

		transaction.setDueDate(transactionDTO.getDueDate());
		transaction.setCreationDate(transactionDTO.getCreationDate());

		return transaction;
	}
}
