package com.vallete.portfolio.backendjava.transaction.service.interfaces;

import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;

import java.util.List;
import java.util.UUID;

public interface TransactionInterface {
    List<TransactionModel> fetch(TransactionModel transactionFilter);
    TransactionModel save(TransactionModel transactionModel);
    void deleteById(UUID id);
}
