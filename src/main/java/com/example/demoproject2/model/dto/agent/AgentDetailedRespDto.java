package com.example.demoproject2.model.dto.agent;

import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.status.StatusCountDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentDetailedRespDto {
    AgentRespDto agentDto;
    List<CashierRespDto> cashierRespDtos;
    Integer numberOfCashiers;
    List<StatusCountDto> cashiersStatusCountDtos;
}
