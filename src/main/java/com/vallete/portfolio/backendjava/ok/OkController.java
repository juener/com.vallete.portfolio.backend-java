package com.vallete.portfolio.backendjava.ok;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OkController {
    @GetMapping("/ok")
    public ResponseEntity ok() {
        return ResponseEntity.ok("You got in a protected route using a token successfully!");
    }
}
