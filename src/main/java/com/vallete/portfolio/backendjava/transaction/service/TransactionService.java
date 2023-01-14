package com.vallete.portfolio.backendjava.transaction.service;

import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.transaction.repository.TransactionRepository;
import com.vallete.portfolio.backendjava.transaction.model.TransactionModel;
import com.vallete.portfolio.backendjava.transaction.service.interfaces.TransactionInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionService implements TransactionInterface {
    private final TransactionRepository transactionRepository;
    @Override
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

    @Override
    @Transactional
    public TransactionModel save(TransactionModel transactionModel) {
        validate(transactionModel);
        return transactionRepository.save(transactionModel);
    }

    private void validate(TransactionModel transactionModel){
        if(transactionModel.getUser() == null)
            throw new BusinessException("You must fill the userId in the body of this request");
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        transactionRepository.deleteById(id);
    }
}
