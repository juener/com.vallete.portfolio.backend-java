package com.vallete.portfolio.backendjava.model.entity;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	private String name;
	
	private String observation;
	
	private Double value;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionType type;
	
	@Enumerated(value = EnumType.STRING)
	private TransactionStatus status;
	
	private boolean isDone;
	
	private Date dueDate;
	
	private Date registrationDate;
	
	@ManyToOne
	//@JoinColumn(name = id_login)
	private Login login;
}
