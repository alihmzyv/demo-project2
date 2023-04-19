package com.example.demoproject2.model.dto.cashier;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CashierCreateRequestDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer cashierCode;
    String provider;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    String fullName;
    String phone;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    String mobile;
    Integer zoneId;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer cityId;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer regionId;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    String address;
    String macAddress;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    BigDecimal nextPermanentBalance;
    @Size(min = 2, max = 2)
    List<CashierSportsStakeLimitsRequestDto> cashierSportsStakeLimitDtos;
    BigDecimal extraDebtCredit;
    BigDecimal debtCredit;
    BigDecimal betTicketPayoutLimit;
    String email;
    String salesRepEmail;
}
