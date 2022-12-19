package com.vallete.portfolio.backendjava.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.Transaction;

public interface TransactionControllerInterface {

	List<Transaction> seek(Transaction transaction);
	
	Transaction save(Transaction transaction);
	
	Transaction update(Transaction transaction);
	
	void deleteByTransactionId(UUID delete);
		
	BigDecimal getBalanceByUserAndType(UUID idLogin);

}
