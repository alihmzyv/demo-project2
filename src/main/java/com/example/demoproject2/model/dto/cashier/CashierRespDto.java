package com.example.demoproject2.model.dto.cashier;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierRespDto {
    Integer cashierId;
    Integer agentId;
    Integer cashierCode;
    String providerId;
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
}
