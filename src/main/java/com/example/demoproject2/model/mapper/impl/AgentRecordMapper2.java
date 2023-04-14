package com.example.demoproject2.model.mapper.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.status.CountStatus;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record5;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class AgentRecordMapper2 implements RecordMapper<Record5<AgentRecord, Integer, Integer, Integer, Integer>, AgentRespDto> {
    AgentRecordMapper agentRecordMapper;

    @Override
    public @Nullable AgentRespDto map(Record5<AgentRecord, Integer, Integer, Integer, Integer> record) {
        Integer num_of_act_cashiers = record.component3();
        Integer num_of_inact_cashiers = record.component4();
        Integer num_of_del_cashiers = record.component5();
        CountStatus actCashiers = CountStatus.builder()
                .statusId((short) 1)
                .count(num_of_act_cashiers)
                .build();
        CountStatus inactCashiers = CountStatus.builder()
                .statusId((short) 2)
                .count(num_of_inact_cashiers)
                .build();
        CountStatus delCashiers = CountStatus.builder()
                .statusId((short) 3)
                .count(num_of_del_cashiers)
                .build();
        List<CountStatus> cashiersStatus = List.of(actCashiers, inactCashiers, delCashiers);
        return AgentRespDto.builder()
                .agentDto(agentRecordMapper.map(record.component1()))
                .numberOfCashiers(record.component2())
                .cashiersCountStatuses(cashiersStatus)
                .build();
    }
}
