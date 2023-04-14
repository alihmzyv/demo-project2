package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRepo agentRepo;

    @Override
    public AgentDto createAgent(CreateAgentDto createAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
        return agentMapper.toDto(agentRecordInserted);
    }

    @Override
    public AgentDto updateAgent(UpdateAgentDto updateAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(updateAgentDto);
        return Optional.ofNullable(agentRepo.updateAgent(agentRecord))
                .map(agentMapper::toDto)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Agent not found with id: %d", updateAgentDto.getİd())));
    }

    @Override
    public void deleteAgentById(Integer agentId) {
        int deletedRows = agentRepo.deleteAgentById(agentId);
        if (deletedRows == 0) throw new IllegalArgumentException(String.format("Agent not found with id: %d", deletedRows));
    }
}
