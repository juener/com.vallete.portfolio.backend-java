package com.vallete.portfolio.backendjava.transaction.service;

import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.repository.SaveTransactionRepository;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaveTransactionService {
    private final SaveTransactionRepository saveTransactionRepository;
    private final UserInterfaceRepositoryJPA userInterfaceRepository;

    public ResponseEntity saveTransaction(TransactionDTO transactionDTO){
        UserModel userModel = userInterfaceRepository.getUserById(transactionDTO.getUser());
        return saveTransactionRepository.saveTransaction(transactionDTO, userModel);
    }
}
