package com.vallete.portfolio.backendjava.user.repository;

import com.vallete.portfolio.backendjava.shared.enumerated.TransactionType;
import com.vallete.portfolio.backendjava.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByEmail(String email);

    Optional<UserModel> findByEmail(String email);

    Optional<UserModel> findById(UUID id);

    UserModel getUserById(UUID id);

    UserModel getUserByEmail(String email);

    @Query(value =
            "select sum(T.value) from TransactionModel T join T.user U " +
                    "where U.id = :idUser and T.type = :type group by U"
    )
    BigDecimal getBalanceByUserAndType(
            @Param("idUser") UUID idUser,
            @Param("type") Enum<TransactionType> type
    );
}
/*"select sum(T.value) from Transaction T join T.user U " +
                    "where U.id = :idUser and T.type = :type group by U"*/