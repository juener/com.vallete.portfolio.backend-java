package com.vallete.portfolio.backendjava.transaction.service;

import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.transaction.repository.jpa.TransactionInterfaceRepositoryJPA;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionInterfaceRepositoryJPA transactionRepository;

    public List<TransactionModel> fetch(TransactionModel transactionFilter) {
        Example example = Example.of(
                transactionFilter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );

        return transactionRepository.findAll(example);
    }

    @Transactional
    public TransactionModel save(TransactionModel transactionModel) {
        validate(transactionModel);
        return transactionRepository.save(transactionModel);
    }

    private void validate(TransactionModel transactionModel){
        if(transactionModel.getUser() == null)
            throw new BusinessException("You must fill the userId in the body of this request");
    }

    @Transactional
    public void deleteById(UUID id) {
        transactionRepository.deleteById(id);
    }
}
