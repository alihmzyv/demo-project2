package com.example.demoproject2.model.mapper.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.status.CountStatus;
import org.jetbrains.annotations.Nullable;
import org.jooq.Record5;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AgentRecordMapper implements RecordMapper<Record5<AgentRecord, Integer, Integer, Integer, Integer>, AgentRespDto> {
    @Override
    public @Nullable AgentRespDto map(Record5<AgentRecord, Integer, Integer, Integer, Integer> record) {
        Integer num_of_act_cashiers = record.getValue("num_of_act_cashiers", Integer.class);
        Integer num_of_inact_cashiers = record.getValue("num_of_inact_cashiers", Integer.class);
        Integer num_of_del_cashiers = record.getValue("num_of_del_cashiers", Integer.class);
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
                .agentCode(record.getValue("agent_code", Integer.class))
                .fullName(record.getValue("full_name", String.class))
                .cityİd(record.getValue("city_id", Integer.class))
                .address(record.getValue("address", String.class))
                .idNumber(record.getValue("id_number", String.class))
                .voen(record.getValue("voen", String.class))
                .phone(record.getValue("phone", String.class))
                .mobile(record.getValue("mobile", String.class))
                .email(record.getValue("email", String.class))
                .salesRepEmail(record.getValue("sales_rep_email", String.class))
                .totalPermanentBalance(record.getValue("total_permanent_balance", BigDecimal.class))
                .debtCredit(record.getValue("debt_credit", BigDecimal.class))
                .extraDebtCredit(record.getValue("extra_debt_credit", BigDecimal.class))
                .status(record.getValue("status", Short.class))
                .numberOfCashiers(record.getValue("num_of_cashiers", Integer.class))
                .cashiersCountStatuses(cashiersStatus)
                .build();
    }
}
