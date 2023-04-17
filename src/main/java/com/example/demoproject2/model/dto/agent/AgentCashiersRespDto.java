package com.example.demoproject2.model.dto.agent;

import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentCashiersRespDto {
    AgentRespDto agentRespDto;
    List<CashierRespDto> cashiers;
}
