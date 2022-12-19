package com.vallete.portfolio.backendjava.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String email;
	private String name;
	private String password;
	
	@Builder.Default
	private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));	
}
