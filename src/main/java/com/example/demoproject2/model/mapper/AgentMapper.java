package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentDetailedRespDto;
import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import com.example.demoproject2.model.dto.status.StatusCountDto;
import org.jooq.Record5;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public interface AgentMapper {
    AgentRecord toRecord(CreateAgentDto createAgentDto);
    AgentRecord toRecord(UpdateAgentDto updateAgentDto);
    AgentRespDto toDto(AgentRecord agentRecord);
    default AgentDetailedRespDto toDto(Record5<AgentRecord, Integer, Integer, Integer, Integer> record) {
        if (record == null) {
            return null;
        }
        Integer num_of_act_cashiers = record.component3();
        Integer num_of_inact_cashiers = record.component4();
        Integer num_of_del_cashiers = record.component5();
        StatusCountDto actCashiers = StatusCountDto.builder()
                .statusId((short) 1)
                .count(num_of_act_cashiers)
                .build();
        StatusCountDto inactCashiers = StatusCountDto.builder()
                .statusId((short) 2)
                .count(num_of_inact_cashiers)
                .build();
        StatusCountDto delCashiers = StatusCountDto.builder()
                .statusId((short) 3)
                .count(num_of_del_cashiers)
                .build();
        List<StatusCountDto> cashiersStatus = List.of(actCashiers, inactCashiers, delCashiers);
        return AgentDetailedRespDto.builder()
                .agentDto(toDto(record.component1()))
                .numberOfCashiers(record.component2())
                .cashiersStatusCountDtos(cashiersStatus)
                .build();
    }

    default List<AgentDetailedRespDto> toAgentRespDto(List<Record5<AgentRecord, Integer, Integer, Integer, Integer>> allAgents) {
        return allAgents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
