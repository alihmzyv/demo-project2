package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.model.dto.agent.*;
import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.status.StatusCountDto;
import org.jooq.Record5;
import org.jooq.Result;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mapstruct.ReportingPolicy.WARN;


@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public abstract class AgentMapper {
    private final CashierMapper cashierMapper;

    protected AgentMapper() {
        cashierMapper = new CashierMapperImpl();
    }

    public abstract AgentRecord toRecord(CreateAgentDto createAgentDto);
    public abstract AgentRecord toRecord(UpdateAgentDto updateAgentDto);
    public abstract AgentRespDto toDto(AgentRecord agentRecord);
    public AgentDetailedRespDto toDto(Record5<AgentRecord, Integer, Integer, Integer, Integer> record) {
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

    public List<AgentDetailedRespDto> toDto(List<Record5<AgentRecord, Integer, Integer, Integer, Integer>> allAgents) {
        return allAgents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AgentCashiersRespDto toDto(Map.Entry<AgentRecord, Result<CashierRecord>> entrySet) {
        List<CashierRespDto> cashierRespDtos = cashierMapper.toDto(entrySet.getValue().stream().toList());
        return AgentCashiersRespDto.builder()
                .agentRespDto(toDto(entrySet.getKey()))
                .cashiers(cashierRespDtos)
                .build();
    }
}
