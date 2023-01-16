package com.vallete.portfolio.backendjava.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionStatus;
import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import lombok.*;
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
    @NonNull
    private String name;
    private String observation;
    @NonNull
    private BigDecimal value;
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private TransactionType type;
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private TransactionStatus status;
    private LocalDate dueDate;
    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    @ManyToOne //@JoinColumn(name = id_user)
    @NonNull
    private UserModel user;
}
