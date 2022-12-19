package com.vallete.portfolio.backendjava.repository;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vallete.portfolio.backendjava.model.Transaction;
import com.vallete.portfolio.backendjava.model.TransactionType;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

	@Query(value = " select sum(T.value) from Transaction T join T.user U "
			+ " where U.id = :idUser and T.type = :type group by U ")
	BigDecimal getBalanceByUserAndType(@Param("idUser") UUID idUser, @Param("type") Enum<TransactionType> type);
}
