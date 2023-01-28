package com.vallete.portfolio.backendjava.transaction.repository;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionStatus;
import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import com.vallete.portfolio.backendjava.transaction.repository.jpa.TransactionInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class SaveTransactionRepository {
    @Autowired
    TransactionInterfaceRepositoryJPA transactionRepository;

    public ResponseEntity saveTransaction(TransactionDTO transactionDTO, UserModel userModel) {
        try {
            TransactionModel transactionModel = TransactionModel.builder()
                    .user(userModel)
                    .id(transactionDTO.getId())
                    .name(transactionDTO.getName())
                    .value(transactionDTO.getValue())
                    .observation(transactionDTO.getObservation())
                    .creationDate(transactionDTO.getCreationDate())
                    .dueDate(transactionDTO.getDueDate())
                    .status(TransactionStatus.valueOf(transactionDTO.getStatus()))
                    .type(TransactionType.valueOf(transactionDTO.getType()))
                    .build();

            return new ResponseEntity(transactionRepository.save(transactionModel), null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Error creating transaction." + e, null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
