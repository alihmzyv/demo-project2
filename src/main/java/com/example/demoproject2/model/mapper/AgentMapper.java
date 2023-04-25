package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.agent.req.AgentCreateRequestDto;
import com.example.demoproject2.model.dto.agent.req.AgentUpdateRequestDto;
import com.example.demoproject2.model.dto.agent.resp.AgentDetailedResponseDto;
import com.example.demoproject2.model.dto.agent.resp.AgentResponseDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.model.dto.status.resp.StatusCountRespDto;
import com.example.demoproject2.util.JooqUtil;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.example.demoproject2.consts.Status.*;
import static com.example.demoproject2.generated.jooq.Tables.*;
import static java.util.stream.Collectors.*;
import static org.mapstruct.ReportingPolicy.WARN;


@Slf4j
@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public abstract class AgentMapper {
    @Autowired
    JooqUtil jooqUtil;
    @Autowired
    CashierMapper cashierMapper;
    @Autowired
    CashierSportsStakesLimitsMapper cashierSportsStakesLimitsMapper;

    public List<AgentDetailedResponseDto> toDto(Result<Record> agentCashiersLimitsRecords) {
        if (agentCashiersLimitsRecords.isEmpty()) {
            return Collections.emptyList();
        }
        //map
        Map<AgentRecord, List<CashierRecord>> agentCashierMap = agentCashiersLimitsRecords.collect(
                groupingBy(r -> r.into(AGENT),
                        filtering(r -> r.get(CASHIER.ID) != null,
                                mapping(r -> r.into(CASHIER),
                                        toList()))));
        Map<CashierRecord, List<CashierSportsStakeLimitsRecord>> cashierLimitsMap = agentCashiersLimitsRecords.collect(
                groupingBy(r -> r.into(CASHIER),
                        filtering(r -> r.get(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID) != null,
                                mapping(r -> r.into(CASHIER_SPORTS_STAKE_LIMITS),
                                        toList()))));

        List<AgentDetailedResponseDto> agentDtos = new ArrayList<>(); //result to return

        //map cashier records, limits records to Cashier dto
        agentCashierMap.forEach((agentRecord, cashierRecords) -> {
            List<CashierDetailedResponseDto> cashierDtos = new ArrayList<>();
            cashierRecords.forEach(cashierRecord -> {
                List<CashierSportsStakeLimitsRecord> limitsRecords = cashierLimitsMap.getOrDefault(cashierRecord, jooqUtil.emptyResult(CASHIER_SPORTS_STAKE_LIMITS));
                CashierDetailedResponseDto cashierDto = cashierMapper.toDto(cashierRecord, limitsRecords);
                cashierDtos.add(cashierDto);
            });

            //map agent records, cashier dtos to Agent dto
            AgentDetailedResponseDto agentDto = toDto(agentRecord, cashierDtos);
            agentDtos.add(agentDto);
        });

        return agentDtos;
    }

    public AgentDetailedResponseDto toDto(AgentRecord agentRecord, List<CashierDetailedResponseDto> cashierDetailedResponseDtos) {
        StatusCountRespDto countActives = StatusCountRespDto.builder()
                .statusId(ACTIVE_STATUS_VALUE)
                .count(0)
                .build();
        StatusCountRespDto countInactives = StatusCountRespDto.builder()
                .statusId(INACTIVE_STATUS_VALUE)
                .count(0)
                .build();
        StatusCountRespDto countDeleted = StatusCountRespDto.builder()
                .statusId(DELETED_STATUS_VALUE)
                .count(0)
                .build();
        cashierDetailedResponseDtos.forEach(cashierDetailedResponseDto -> {
            Short status = cashierDetailedResponseDto.getCashierResponseDto().getStatus();
            switch (status) {
                case 1 -> countActives.setCount(countActives.getCount() + 1);
                case 2 -> countInactives.setCount(countInactives.getCount() + 1);
                default -> countDeleted.setCount(countDeleted.getCount() + 1);
            }
        });
        AgentResponseDto agentResponseDto = toDto(agentRecord);
        int numberOfCashiers = cashierDetailedResponseDtos.size();
        List<StatusCountRespDto> cashierStatusCounts = List.of(countActives, countInactives, countDeleted);
        return AgentDetailedResponseDto.builder()
                .agentDto(agentResponseDto)
                .cashierRespDtos(cashierDetailedResponseDtos)
                .numberOfCashiers(numberOfCashiers)
                .cashiersStatusCountRespDtos(cashierStatusCounts)
                .build();
    }

    public abstract AgentResponseDto toDto(AgentRecord agentRecord);

    public abstract AgentRecord toRecord(AgentCreateRequestDto agentCreateRequestDto);

    public abstract AgentRecord toRecord(AgentUpdateRequestDto agentUpdateRequestDto);
}
