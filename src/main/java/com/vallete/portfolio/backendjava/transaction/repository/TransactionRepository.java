package com.vallete.portfolio.backendjava.transaction.repository;

import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionModel, UUID> {

}
