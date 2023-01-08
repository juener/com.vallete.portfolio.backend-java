package com.vallete.portfolio.backendjava.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionStatus;
import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_transaction")
public class TransactionModel {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
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
    private UserModel user;
}
