package com.vallete.portfolio.backendjava.user.service.interfaces;

import com.vallete.portfolio.backendjava.user.model.UserModel;

import java.util.UUID;

public interface UserInterface {
    UserModel getUserById(UUID id);
}
