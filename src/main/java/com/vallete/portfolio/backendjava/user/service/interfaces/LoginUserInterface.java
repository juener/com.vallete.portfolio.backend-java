package com.vallete.portfolio.backendjava.user.service.interfaces;

import com.vallete.portfolio.backendjava.user.model.UserModel;

import java.util.Optional;
import java.util.UUID;

public interface LoginUserInterface {
    UserModel login(String email, String password);
//    UserModel getUserById(UUID id);
//    Optional<UserModel> findById(UUID id);
}
