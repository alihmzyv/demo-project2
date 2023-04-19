package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentCreateRequestDto;
import com.example.demoproject2.model.dto.agent.AgentDetailedResponseDto;
import com.example.demoproject2.model.dto.agent.AgentStatusUpdateRequestDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRepo agentRepo;

    @Override
    public List<AgentDetailedResponseDto> findAllAgents(Pageable pageable) {
        Result<Record> allAgents = agentRepo.findAllAgents(pageable);
        return agentMapper.toAgentDetailedResponseDto(allAgents);
    }

    @Override
    public AgentDetailedResponseDto findAgentById(Integer agentId) {
        Result<Record> agentById = agentRepo.findAgentById(agentId);
        if (agentById.isEmpty()) {
            throw new IllegalArgumentException(String.format("Agent not found with id = %d", agentId));
        } else return agentMapper.toAgentDetailedResponseDto(agentById).get(0); //there can be one agent only by the id
    }

    @Override
    public int createAgent(AgentCreateRequestDto agentCreateRequestDto) {
        AgentRecord agentRecord = agentMapper.toRecord(agentCreateRequestDto);
        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
        return agentRecordInserted.getId();
    }

    @Override
    public void deleteAgentById(Integer agentId) {
        int deletedRows = agentRepo.deleteAgentById(agentId);
        if (deletedRows == 0) throw new IllegalArgumentException(String.format("Agent not found with id: %d", agentId));
    }

    @Override
    public void updateAgentStatus(AgentStatusUpdateRequestDto agentStatusUpdateRequestDto, Integer agentId) {
        log.info(agentStatusUpdateRequestDto.getComment()); //log to db TODO
        Short newStatus = agentStatusUpdateRequestDto.getNewStatus();
        if (!agentRepo.agentExistsById(agentId)) {
            throw new IllegalArgumentException(String.format("Agent not found with id: %d", agentId));
        }
        agentRepo.updateAgentStatus(agentId, newStatus);
    }
}
