package com.vallete.portfolio.backendjava.user.service.interfaces;

import java.math.BigDecimal;
import java.util.UUID;

public interface BalanceUserInterface {
    BigDecimal getBalanceByUserAndType(UUID idUser);
}
