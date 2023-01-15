package com.vallete.portfolio.backendjava.user.repository;

import com.vallete.portfolio.backendjava.shared.exception.DatabaseException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.jpa.UserInterfaceRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginUserRepository {

    @Autowired
    UserInterfaceRepositoryJPA userRepository;

    public Optional<UserModel> findByEmail(String email){
        try {
            return userRepository.findByEmail(email);
        }catch (Exception e){
            throw new DatabaseException("There was some error to fetch the user.\n\n" + e);
        }
    }
}
