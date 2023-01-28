package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.user.repository.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService  {
    private final RegisterUserRepository userRepository;

    //public UserModel getUserById(UUID id) {
    //  return userRepository.getUserById(id);
    //}
}
