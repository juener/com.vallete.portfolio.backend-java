package com.vallete.portfolio.backendjava.transaction.repository;

import com.vallete.portfolio.backendjava.transaction.dto.TransactionDTO;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import com.vallete.portfolio.backendjava.transaction.repository.jpa.TransactionInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FetchTransactionsRepository {
    @Autowired
    TransactionInterfaceRepositoryJPA transactionRepository;

    public ResponseEntity fetchTransactions(TransactionDTO transactionDTO, UserModel userModel) {
        try {
            TransactionModel transactionModel = TransactionModel.builder()
                    .user(userModel)
                    .name(transactionDTO.getName())
                    .observation(transactionDTO.getObservation())
                    .build();

            Example example = Example.of(
                    transactionModel,
                    ExampleMatcher
                            .matching()
                            .withIgnoreCase()
                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
            );

            return new ResponseEntity(transactionRepository.findAll(example), null, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Some error occurred while trying to fetch data.", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
