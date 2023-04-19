package com.example.demoproject2.model.dto.agent;

import com.example.demoproject2.model.dto.cashier.CashierDetailedResponseDto;
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
public class AgentDetailedResponseDto {
    AgentResponseDto agentDto;
    List<CashierDetailedResponseDto> cashierRespDtos;
    Integer numberOfCashiers;
    List<StatusCountDto> cashiersStatusCountDtos;
}
