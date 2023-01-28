package com.vallete.portfolio.backendjava.user.service;

import com.vallete.portfolio.backendjava.config.JwtDTO;
import com.vallete.portfolio.backendjava.config.JwtInterface;
import com.vallete.portfolio.backendjava.user.dto.UserDTO;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUserService {
    private final LoginUserRepository loginUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtInterface jwtInterface;


    public ResponseEntity loginUser(UserDTO userDTO) {
        try {
            Optional<UserModel> optionalUserModel = loginUserRepository.findByEmail(userDTO.getEmail());
            if (!optionalUserModel.isPresent())
                return new ResponseEntity("This email is not registered yet.", null, HttpStatus.UNPROCESSABLE_ENTITY);

            UserModel userModel = optionalUserModel.get();

            if (!passwordEncoder.matches(userDTO.getPassword(), userModel.getPassword()))
                return new ResponseEntity("Password incorrect.", null, HttpStatus.FORBIDDEN);

            JwtDTO jwtDTO = new JwtDTO("Bearer " + jwtInterface.generateToken(userModel));

            return new ResponseEntity(jwtDTO, null, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
