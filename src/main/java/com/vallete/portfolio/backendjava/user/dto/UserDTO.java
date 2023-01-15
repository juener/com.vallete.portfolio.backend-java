package com.vallete.portfolio.backendjava.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private UUID id;
    private String email;

    private String name;

    private String password;

    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
}
