package com.vallete.portfolio.backendjava.user.repository.jpa;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserInterfaceRepositoryJPA extends JpaRepository<UserModel, UUID> {
    //    boolean existsByEmail(String email);
    //UserModel getUserByEmail(String email);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findById(UUID id);
    UserModel getUserById(UUID id);

    @Query(value =
            "select sum(T.value) from TransactionModel T join T.user U " +
                    "where U.id = :idUser and T.type = :type group by U"
    )
    BigDecimal getBalanceByUserAndType(
            @Param("idUser") UUID idUser,
            @Param("type") Enum<TransactionType> type
    );
}
