package com.vallete.portfolio.backendjava.transaction.service;

import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.repository.FetchTransactionsRepository;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FetchTransactionsService {
    private final FetchTransactionsRepository fetchTransactionsRepository;
    private final UserInterfaceRepositoryJPA userRepository;

    public ResponseEntity fetchTransactions(TransactionDTO transactionDTO, Map params){
        UserModel userModel = userRepository.getUserById(UUID.fromString((String) params.get("user")));

        return fetchTransactionsRepository.fetchTransactions(transactionDTO, userModel);
    }
}
