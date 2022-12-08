package com.vallete.portfolio.backendjava.model.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vallete.portfolio.backendjava.model.entity.Transaction;
import com.vallete.portfolio.backendjava.model.entity.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

//	List<Transaction> buscar(Transaction transactionFilter);
	
//	Optional<Transaction> getAllById(UUID id);
	
//	Transaction save(Transaction transaction);
	
//	Transaction update(Transaction transaction);
	
//	void deleteById(UUID id);
	
//	void validate(Transaction transaction);

	@Query(value = " select sum(T.value) from Transaction T join T.login L "
			+ " where L.id = :idLogin and T.type = :type group by L ")
	BigDecimal getBalanceByUserAndType(@Param("idLogin") UUID idLogin, @Param("type") Enum<TransactionType> type);
}
