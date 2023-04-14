package com.example.demoproject2.model.dto.agent;

import com.example.demoproject2.model.dto.status.CountStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentRespDto {
    String fullName;
    Integer cityİd;
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
    Integer numberOfCashiers;
    List<CountStatus> cashiersCountStatuses;
}
