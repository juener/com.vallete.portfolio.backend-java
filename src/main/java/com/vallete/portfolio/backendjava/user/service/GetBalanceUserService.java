package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.user.repository.GetBalanceByUserRepository;
import com.vallete.portfolio.backendjava.user.repository.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetBalanceUserService {
    private final GetBalanceByUserRepository getBalanceByUserRepository;

    /*to be implemented
    public ResponseEntity getBalanceByUser(UUID idUser){
        BigDecimal spent = ;
        BigDecimal revenue = ;
        BigDecimal balance;
        return getBalanceByUser.get
    }
    */
    //@Override
    //public BigDecimal getBalanceByUserAndType(UUID idUser) {
        //if(userRepository.getUserById(idUser) == null)
        //    throw new BusinessException("The required user doesn't exist.");

        //BigDecimal totalRevenue = userRepository.getBalanceByUserAndType(idUser, TransactionType.REVENUE);
        //if (totalRevenue == null)
        //      totalRevenue = BigDecimal.ZERO;

        //BigDecimal totalSpent = userRepository.getBalanceByUserAndType(idUser, TransactionType.SPENT);
        //if (totalSpent == null)
        //    totalSpent = BigDecimal.ZERO;

        //return totalRevenue.subtract(totalSpent); // return the balance from the user
    //}
}
