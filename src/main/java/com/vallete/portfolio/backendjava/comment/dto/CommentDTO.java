package com.vallete.portfolio.backendjava.comment.dto;

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
public class CommentDTO {

    private UUID id;

    private String body;

    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    private UUID user;

    private UUID post;

}
