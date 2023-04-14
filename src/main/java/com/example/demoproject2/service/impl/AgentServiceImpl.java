package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.model.mapper.impl.AgentRecordMapper2;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.Record5;
import org.springframework.stereotype.Service;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRecordMapper2 agentRecordMapper2;
    AgentRepo agentRepo;

    @Override
    public AgentRespDto createAgent(CreateAgentDto createAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
        Optional<Record5<AgentRecord, Integer, Integer, Integer, Integer>> agentRespRec = agentRepo.findAgentById(agentRecordInserted.getİd());
        return agentRecordMapper2.map(agentRespRec
                .orElseThrow(() -> new RuntimeException("An error occured"))); //can't be normally
    }

    @Override
    public AgentRespDto updateAgent(UpdateAgentDto updateAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(updateAgentDto);
        AgentRecord agentRecordUpdated = agentRepo.updateAgent(agentRecord);
        Optional<Record5<AgentRecord, Integer, Integer, Integer, Integer>> agentRespRec = agentRepo.findAgentById(agentRecordUpdated.getİd());
        return agentRecordMapper2.map(agentRespRec
                .orElseThrow(() -> new RuntimeException("An error occured"))); //can't be normally
    }
}
