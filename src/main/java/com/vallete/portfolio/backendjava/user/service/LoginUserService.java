package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.shared.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.UserRepository;
import com.vallete.portfolio.backendjava.user.service.interfaces.LoginUserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUserService implements LoginUserInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel login(String email, String password) {
        Optional<UserModel> userModel = userRepository.findByEmail(email);

        if (!userModel.isPresent())
            throw new AuthenticationException("This email is not registered yet.");

        if (!passwordEncoder.matches(password, userModel.get().getPassword()))
            throw new AuthenticationException("Password incorrect.");

        return userModel.get();
    }
}
