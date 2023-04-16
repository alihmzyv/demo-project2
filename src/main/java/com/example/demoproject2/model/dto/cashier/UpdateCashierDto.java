package com.example.demoproject2.model.dto.cashier;

import jakarta.validation.constraints.NotNull;
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
public class UpdateCashierDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer id;
    Integer cashierCode;
    String provider;
    String fullName;
    String phone;
    String mobile;
    Integer zoneId;
    Integer cityId;
    Integer regionId;
    String address;
    String macAddress;
    BigDecimal nextPermanentBalance;
    BigDecimal currentBalance;
    BigDecimal extraDebtCredit;
    BigDecimal debtCredit;
    BigDecimal betTicketPayoutLimit;
    Short status;
    Short frozenStatus;
    String email;
    String salesRepEmail;
    List<CashierSportsStakeLimitsDto> cashierSportsStakeLimits;
}
