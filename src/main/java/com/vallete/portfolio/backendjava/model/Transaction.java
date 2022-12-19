package com.vallete.portfolio.backendjava.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_transaction")
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
	

	private LocalDate dueDate;
	

	private LocalDateTime creationDate;
	
	@ManyToOne
	//@JoinColumn(name = id_user)
	private User user;
}
