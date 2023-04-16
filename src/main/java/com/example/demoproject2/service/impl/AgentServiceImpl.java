package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentFullRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import com.example.demoproject2.model.mapper.AgentMapper;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.service.AgentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record5;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRepo agentRepo;

    @Override
    public List<AgentFullRespDto> findAllAgents(Integer page, Integer size) {
        List<Record5<AgentRecord, Integer, Integer, Integer, Integer>> allAgents = agentRepo.findAllAgents(page, size);
        return agentMapper.toAgentRespDto(allAgents);
    }

    @Override
    public AgentFullRespDto findAgentById(Integer agentId) {
        Record5<AgentRecord, Integer, Integer, Integer, Integer> agentById = agentRepo.findAgentById(agentId);
        AgentFullRespDto agentFullRespDto = agentMapper.toDto(agentById);
        return Optional.ofNullable(agentFullRespDto)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Agent not found with id: %d", agentId)));
    }

    @Override
    public AgentFullRespDto createAgent(CreateAgentDto createAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
        log.info("Id of agentRecord after insertion:{}", agentRecord.getİd());
        Record5<AgentRecord, Integer, Integer, Integer, Integer> agentById = agentRepo.findAgentById(agentRecordInserted.getİd());
        return agentMapper.toDto(agentById);
    }

    @Override
    public AgentFullRespDto updateAgent(UpdateAgentDto updateAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(updateAgentDto);
        return Optional.ofNullable(agentRepo.updateAgent(agentRecord))
                .map(agentRecordUpdated -> agentRepo.findAgentById(agentRecordUpdated.getİd()))
                .map(agentMapper::toDto)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Agent not found with id: %d", updateAgentDto.getAgentId())));
    }

    @Override
    public void deleteAgentById(Integer agentId) {
        int deletedRows = agentRepo.deleteAgentById(agentId);
        if (deletedRows == 0) throw new IllegalArgumentException(String.format("Agent not found with id: %d", deletedRows));
    }
}
