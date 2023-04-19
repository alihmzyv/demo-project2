package com.example.demoproject2.model.dto.cashier.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierSportsStakeLimitsResponseDto {
    Short sportsId;
    BigDecimal minStake;
    BigDecimal maxStake;
}
