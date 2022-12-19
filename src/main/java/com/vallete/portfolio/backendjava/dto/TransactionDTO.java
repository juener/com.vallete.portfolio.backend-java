package com.vallete.portfolio.backendjava.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
	
	private UUID id;
	
	private String name;
	
	private String observation;
	
	private BigDecimal value;
	
	private UUID user;
	
	private String type;
	
	private String status;
	
	private LocalDate dueDate;
	
	@Builder.Default
	private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
	
}
