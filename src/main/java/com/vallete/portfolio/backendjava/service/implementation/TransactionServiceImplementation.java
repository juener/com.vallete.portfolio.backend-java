package com.vallete.portfolio.backendjava.service.implementation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vallete.portfolio.backendjava.exception.BusinessException;
import com.vallete.portfolio.backendjava.model.entity.Transaction;
import com.vallete.portfolio.backendjava.model.entity.TransactionType;
import com.vallete.portfolio.backendjava.model.repository.TransactionRepository;
import com.vallete.portfolio.backendjava.service.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImplementation implements TransactionService {

	private TransactionRepository transactionRepository;

	public TransactionServiceImplementation(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	@Override
	public List<Transaction> seek(Transaction transactionFilter) {
		Example example = Example.of(transactionFilter,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));
		return transactionRepository.findAll(example);
	}

	@Override
	@Transactional
	public Transaction save(Transaction transaction) {
		validate(transaction);
		
		return transactionRepository.save(transaction);
	}

	private void validate(Transaction transaction) {

		if (transaction.getName() == null || transaction.getStatus() == null || transaction.getName().trim() == "")
			throw new BusinessException("Some fields are required: Name, Status, //etc");
		// add more filters

	}
	
	@Override
	@Transactional
	public Transaction update(Transaction transaction) {
		validate(transaction);
		
		return transactionRepository.save(transaction);
	}


	@Override
	@Transactional
	public void deleteByTransactionId(UUID id) {		
		transactionRepository.deleteById(id);
	}
	
	@Override
	public BigDecimal getBalanceByUserAndType(UUID idLogin) {
		BigDecimal totalRevenue = transactionRepository.getBalanceByUserAndType(idLogin, TransactionType.REVENUE);
		if(totalRevenue == null) 
			totalRevenue = BigDecimal.ZERO;
		
		BigDecimal totalSpent = transactionRepository.getBalanceByUserAndType(idLogin, TransactionType.SPENT);
		if(totalSpent == null)
			totalSpent = BigDecimal.ZERO;
		
		return totalRevenue.subtract(totalSpent); // return the balance from the login
	}
}
