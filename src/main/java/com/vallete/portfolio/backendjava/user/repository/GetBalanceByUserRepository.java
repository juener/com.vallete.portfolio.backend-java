package com.vallete.portfolio.backendjava.user.repository;

import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GetBalanceByUserRepository {
    @Autowired
    UserInterfaceRepositoryJPA userRepositoryJPA;

    /*
    to be implemented

    public ResponseEntity GetBalanceByUserAndType(...){
    ...
    }
     */
}
