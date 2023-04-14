package com.example.demoproject2.model.mapper.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentDto;
import org.jetbrains.annotations.Nullable;
import org.jooq.RecordMapper;
import org.springframework.stereotype.Component;

@Component
public class AgentRecordMapper implements RecordMapper<AgentRecord, AgentDto> {
    @Override
    public @Nullable AgentDto map(AgentRecord record) {
        return AgentDto.builder()
                .fullName(record.getFullName())
                .city(record.getCity())
                .address(record.getAddress())
                .agentCode(record.getAgentCode())
                .idNumber(record.getİdNumber())
                .voen(record.getVoen())
                .phone(record.getPhone())
                .mobile(record.getMobile())
                .email(record.getEmail())
                .salesRepEmail(record.getSalesRepEmail())
                .totalPermanentBalance(record.getTotalPermanentBalance())
                .debtCredit(record.getDebtCredit())
                .extraDebtCredit(record.getExtraDebtCredit())
                .status(record.getStatus())
                .build();
    }
}
