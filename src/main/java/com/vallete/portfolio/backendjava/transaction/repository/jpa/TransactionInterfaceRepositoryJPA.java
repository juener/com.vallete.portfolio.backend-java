package com.vallete.portfolio.backendjava.transaction.repository.jpa;

import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionInterfaceRepositoryJPA extends JpaRepository<TransactionModel, UUID> {
}
