package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentCashiersRespDto;
import com.example.demoproject2.model.dto.agent.AgentDetailedRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {
    Page<AgentDetailedRespDto> findAllAgents(Pageable pageable);
    List<AgentCashiersRespDto> findAllAgents2(Pageable pageable);
    AgentDetailedRespDto findAgentById(Integer agentId);
    AgentDetailedRespDto createAgent(CreateAgentDto createAgentDto);

    AgentDetailedRespDto updateAgent(UpdateAgentDto updateAgentDto);

    void deleteAgentById(Integer agentId);

    void deactivateAgentById(Integer agentId);
}
