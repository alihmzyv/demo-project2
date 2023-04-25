package com.example.demoproject2.model.dto.agent.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
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
