package com.vallete.portfolio.backendjava.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDTO {

	private String email;
	private String name;
	private String password;
}
