package com.vallete.portfolio.backendjava.transaction.repository;

import com.vallete.portfolio.backendjava.transaction.repository.jpa.TransactionInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DeleteTransactionRepository {
    @Autowired
    TransactionInterfaceRepositoryJPA transactionRepository;

    public ResponseEntity deleteTransaction(UUID id){
        try{
            return new ResponseEntity("The transaction has been deleted.", null, HttpStatus.OK);
        }catch (Exception e){
         return new ResponseEntity("Error deleting transaction." + e, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
