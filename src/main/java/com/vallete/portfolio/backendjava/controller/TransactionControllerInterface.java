package com.vallete.portfolio.backendjava.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.Transaction;

public interface TransactionControllerInterface {

	List<Transaction> search(Transaction transaction);
	
	Transaction save(Transaction transaction);
	
	Transaction update(Transaction transaction);
	
	void deleteByTransactionId(UUID idTransaction);
		
	BigDecimal getBalanceByUserAndType(UUID idUser);

}
