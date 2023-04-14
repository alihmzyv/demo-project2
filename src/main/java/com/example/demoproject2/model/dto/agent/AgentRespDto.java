package com.example.demoproject2.model.dto.agent;

import com.example.demoproject2.model.dto.status.CountStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AgentRespDto {
    AgentDto agentDto;
    Integer numberOfCashiers;
    List<CountStatus> cashiersCountStatuses;
}
