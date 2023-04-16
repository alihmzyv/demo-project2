package com.example.demoproject2.model.dto.agent;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentRespDto {
    Integer id;
    String fullName;
    Integer city;
    String address;
    Integer agentCode;
    String idNumber;
    String voen;
    String phone;
    String mobile;
    String email;
    String salesRepEmail;
    BigDecimal totalPermanentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
    Short status;
}
