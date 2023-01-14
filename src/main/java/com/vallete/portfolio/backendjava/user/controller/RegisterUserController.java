package com.vallete.portfolio.backendjava.user.controller;

import com.vallete.portfolio.backendjava.user.dto.UserDTO;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import com.vallete.portfolio.backendjava.user.service.RegisterUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/register")
@RequiredArgsConstructor
public class RegisterUserController {

    private final RegisterUserService registerUserService;

    @PostMapping
    public ResponseEntity register(@RequestBody UserDTO userDTO){
        UserModel userModel = UserModel.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        return ResponseEntity.ok(registerUserService.save(userModel));
    }
}
