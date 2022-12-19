package com.vallete.portfolio.backendjava.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.vallete.portfolio.backendjava.model.Post;
import com.vallete.portfolio.backendjava.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
