package com.vallete.portfolio.backendjava.user.controller;

import com.vallete.portfolio.backendjava.user.service.BalanceUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BalanceUserController {
    private final BalanceUserService balanceUserService;

    @GetMapping("/user/{idUser}/balance")
    public ResponseEntity getBalance(@PathVariable("idUser") String idUser){
        //BigDecimal balance = balanceUserService.getBalanceByUserAndType(UUID.fromString(idUser));
        String balance = "";
        return ResponseEntity.ok(balance);
    }
}
