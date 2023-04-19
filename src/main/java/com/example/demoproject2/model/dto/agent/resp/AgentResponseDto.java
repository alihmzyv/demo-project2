package com.example.demoproject2.model.dto.agent.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentResponseDto {
    Integer id;
    Integer agentCode;
    String voen;
    String fullName;
    String type;
    Integer cityId;
    String address;
    Short status;
    String idNumber;
    String phone;
    String mobile;
    String email;
    String salesRepEmail;
    //finance
    BigDecimal totalPermanentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
}
