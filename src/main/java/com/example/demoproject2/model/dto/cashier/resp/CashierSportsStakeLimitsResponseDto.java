package com.example.demoproject2.model.dto.cashier.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class CashierSportsStakeLimitsResponseDto {
    Short sportsId;
    BigDecimal minStake;
    BigDecimal maxStake;
}
