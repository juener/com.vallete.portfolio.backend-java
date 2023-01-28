package com.vallete.portfolio.backendjava.transaction.service;

import com.vallete.portfolio.backendjava.transaction.repository.DeleteTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteTransactionService {
    private final DeleteTransactionRepository deleteTransactionRepository;

    public ResponseEntity deleteTransaction(UUID id){
        return deleteTransactionRepository.deleteTransaction(id);
    }
}
