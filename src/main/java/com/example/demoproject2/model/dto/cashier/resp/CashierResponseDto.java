package com.example.demoproject2.model.dto.cashier.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor(force = true)
public class CashierResponseDto {
    Integer id;
    Integer agentId;
    Integer cashierCode;
    String fullName;
    String type;
    BigDecimal nextPermanentBalance;
    BigDecimal currentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
    BigDecimal betTicketPayoutLimit;
    Integer cityId;
    String address;
    String mobile;
    Short status;
    Short frozenStatus;
    String providerId;
    String phone;
    String email;
    String salesRepEmail;
    Integer zoneId;
    Integer regionId;
    String macAddress;
}
