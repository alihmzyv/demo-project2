package com.example.demoproject2.model.dto.cashier;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.example.demoproject2.consts.Validation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierSportsStakeLimitsRequestDto {
    @Min(value = 1, message = DEFAULT_VALID_SPORTS_TYPE_ID_MESSAGE)
    @Max(value = 2, message = DEFAULT_VALID_SPORTS_TYPE_ID_MESSAGE)
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Short sportsId;
    @Min(0)
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    BigDecimal minStake;
    @Min(0)
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    BigDecimal maxStake;
}