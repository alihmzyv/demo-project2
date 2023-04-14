package com.example.demoproject2.service.impl;

import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRepo agentRepo;

    @Override
    public AgentRespDto createAgent(CreateAgentDto createAgentDto) {
        throw new RuntimeException("not implemented");
//        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
//        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
    }
}
