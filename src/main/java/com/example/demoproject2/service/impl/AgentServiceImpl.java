package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.model.mapper.impl.AgentRecordMapper;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.Record5;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRecordMapper agentRecordMapper;
    AgentRepo agentRepo;

    @Override
    public AgentRespDto createAgent(CreateAgentDto createAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
        Record5<AgentRecord, Integer, Integer, Integer, Integer> record5Result =
                agentRepo.insertAgent(agentRecord);
        return agentRecordMapper.map(record5Result);
    }
}
