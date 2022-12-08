package com.vallete.portfolio.backendjava.model.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String name;
	
	private String observation;
	
	private BigDecimal value;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionType type;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionStatus status;
	
	private Date dueDate;
	
	private Date registrationDate;
	
	@ManyToOne
	//@JoinColumn(name = id_login)
	private Login login;
}
