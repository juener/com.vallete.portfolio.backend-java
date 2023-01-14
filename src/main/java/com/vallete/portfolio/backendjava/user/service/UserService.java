package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.UserRepository;
import com.vallete.portfolio.backendjava.user.service.interfaces.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {
    private final UserRepository userRepository;

    @Override
    public UserModel getUserById(UUID id) {
        return userRepository.getUserById(id);
    }
}
