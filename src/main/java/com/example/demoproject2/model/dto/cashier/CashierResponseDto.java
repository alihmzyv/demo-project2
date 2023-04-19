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
public class CashierResponseDto {
    Integer id;
    Integer cashierCode;
    String fullName;
    String type;
    BigDecimal nextPermanentBalance;
    BigDecimal currentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
    Integer cityId;
    String address;
    String mobile;
    Short status;
    String providerId;
    String phone;
    String email;
    String salesRepEmail;
    Integer zoneId;
    Integer regionId;
    String macAddress;
}
