package com.example.demoproject2.model.dto.cashier;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierSportsStakeLimitsDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Short sportsType;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    BigDecimal minStake;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    BigDecimal maxStake;
}
