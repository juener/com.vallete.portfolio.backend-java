package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.UserRepository;
import com.vallete.portfolio.backendjava.user.service.interfaces.RegisterUserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserInterface {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserModel save(UserModel userModel){
        validateEmail(userModel.getEmail());
        encryptPassword(userModel);
        return userRepository.save(userModel);
    }

    private void validateEmail(String email){
        if (userRepository.existsByEmail(email))
            throw new BusinessException("There is already one user registered using this email");
    }

    private void encryptPassword(UserModel userModel){
        String decryptedPassword = userModel.getPassword();
        String encryptedPassword = passwordEncoder.encode(decryptedPassword);
        userModel.setPassword(encryptedPassword);
    }
}
