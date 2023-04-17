package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.model.dto.agent.AgentCashiersRespDto;
import com.example.demoproject2.model.dto.agent.AgentDetailedRespDto;
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
import org.jooq.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class AgentServiceImpl implements AgentService {
    AgentMapper agentMapper;
    AgentRepo agentRepo;

    @Override
    public Page<AgentDetailedRespDto> findAllAgents(Pageable pageable) {
        Page<Record5<AgentRecord, Integer, Integer, Integer, Integer>> allAgentsPage = agentRepo.findAllAgents(pageable);
        List<AgentDetailedRespDto> agentDetailedRespDtos = agentMapper.toDto(allAgentsPage.getContent());
        return new PageImpl<>(agentDetailedRespDtos, pageable, allAgentsPage.getTotalElements());
    }

    @Override
    public List<AgentCashiersRespDto> findAllAgents2(Pageable pageable) {
        Map<AgentRecord, Result<CashierRecord>> allAgents = agentRepo.findAllAgents2(pageable);
        return allAgents.entrySet().stream()
                .map(agentMapper::toDto)
                .toList();
    }

    @Override
    public AgentDetailedRespDto findAgentById(Integer agentId) {
        Record5<AgentRecord, Integer, Integer, Integer, Integer> agentById = agentRepo.findAgentById(agentId);
        AgentDetailedRespDto agentDetailedRespDto = agentMapper.toDto(agentById);
        return Optional.ofNullable(agentDetailedRespDto)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Agent not found with id: %d", agentId)));
    }

    @Override
    public AgentDetailedRespDto createAgent(CreateAgentDto createAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(createAgentDto);
        AgentRecord agentRecordInserted = agentRepo.insertAgent(agentRecord);
        log.info("Id of agentRecord after insertion:{}", agentRecord.getId());
        Record5<AgentRecord, Integer, Integer, Integer, Integer> agentById = agentRepo.findAgentById(agentRecordInserted.getId());
        return agentMapper.toDto(agentById);
    }

    @Override
    public AgentDetailedRespDto updateAgent(UpdateAgentDto updateAgentDto) {
        AgentRecord agentRecord = agentMapper.toRecord(updateAgentDto);
        return Optional.ofNullable(agentRepo.updateAgent(agentRecord))
                .map(agentRecordUpdated -> agentRepo.findAgentById(agentRecordUpdated.getId()))
                .map(agentMapper::toDto)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Agent not found with id: %d", updateAgentDto.getId())));
    }

    @Override
    public void deleteAgentById(Integer agentId) {
        int deletedRows = agentRepo.deleteAgentById(agentId);
        if (deletedRows == 0) throw new IllegalArgumentException(String.format("Agent not found with id: %d", deletedRows));
    }
}
