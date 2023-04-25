package com.example.demoproject2.model.dto.agent.resp;

import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.model.dto.status.resp.StatusCountRespDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class AgentDetailedResponseDto {
    AgentResponseDto agentDto;
    List<CashierDetailedResponseDto> cashierRespDtos;
    Integer numberOfCashiers;
    List<StatusCountRespDto> cashiersStatusCountRespDtos;
}
