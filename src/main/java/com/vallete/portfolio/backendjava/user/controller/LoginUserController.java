package com.vallete.portfolio.backendjava.user.controller;

import com.vallete.portfolio.backendjava.config.JwtInterface;
import com.vallete.portfolio.backendjava.config.JwtDTO;
import com.vallete.portfolio.backendjava.shared.exception.AuthenticationException;
import com.vallete.portfolio.backendjava.user.dto.UserDTO;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/login")
@RequiredArgsConstructor
public class LoginUserController {
    private final LoginUserService loginUserService;

    @PostMapping
    public ResponseEntity login(@RequestBody UserDTO userDTO) {
            return loginUserService.loginUser(userDTO);
    }
}
