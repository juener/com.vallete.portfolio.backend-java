package com.vallete.portfolio.backendjava.dto;

import java.math.BigDecimal;
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
	
	private UUID login;
	
	private String type;
	
	private String status;
	
	private Date dueDate;
	
	private Date registrationDate;
}
