package com.vallete.portfolio.backendjava.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.entity.Transaction;

public interface TransactionService {

	List<Transaction> seek(Transaction transaction);
	
	//Optional<Transaction> getAllById(UUID id);
	
	Transaction save(Transaction transaction);
	
	Transaction update(Transaction transaction);
	
	void deleteByTransactionId(UUID delete);
	
//	void validate(Transaction transaction);
	
	BigDecimal getBalanceByUserAndType(UUID idLogin);

}
