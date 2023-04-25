package com.example.demoproject2.model.dto.cashier.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor(force = true)
public class CashierSportsStakeLimitsResponseDto {
    Short sportsId;
    BigDecimal minStake;
    BigDecimal maxStake;
}
