package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.shared.exception.BusinessException;
import com.vallete.portfolio.backendjava.user.dto.UserDTO;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.RegisterUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RegisterUserService {
    private final RegisterUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity saveUser(UserDTO userDTO) {
        /* to be implemented
        if (!isUnique(userDTO.getEmail()))
            return new ResponseEntity("There is already one user registered using the same email.", null, HttpStatus.UNPROCESSABLE_ENTITY);
         */
        encryptPassword(userDTO);
        return userRepository.saveUser(userDTO);
    }

    /* to be implemented
    private boolean isUnique(String email) {
        if (userRepository.existsByEmail(email))
            return false;

        return true;
    }
     */

    private void encryptPassword(UserDTO userDTO) {
        String decryptedPassword = userDTO.getPassword();
        String encryptedPassword = passwordEncoder.encode(decryptedPassword);
        userDTO.setPassword(encryptedPassword);
    }
}
