package com.vallete.portfolio.backendjava.controller;

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
import com.vallete.portfolio.backendjava.model.Transaction;
import com.vallete.portfolio.backendjava.model.TransactionType;
import com.vallete.portfolio.backendjava.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionController implements TransactionControllerInterface {

	private TransactionRepository transactionRepository;

	public TransactionController(TransactionRepository transactionRepository) {
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
	public BigDecimal getBalanceByUserAndType(UUID idUser) {

		BigDecimal totalRevenue = transactionRepository.getBalanceByUserAndType(idUser, TransactionType.REVENUE);
		if (totalRevenue == null)
			totalRevenue = BigDecimal.ZERO;

		BigDecimal totalSpent = transactionRepository.getBalanceByUserAndType(idUser, TransactionType.SPENT);
		if (totalSpent == null)
			totalSpent = BigDecimal.ZERO;

		return totalRevenue.subtract(totalSpent); // return the balance from the user

	}
}
