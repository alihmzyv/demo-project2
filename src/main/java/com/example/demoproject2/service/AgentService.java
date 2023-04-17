package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentFullRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AgentService {
    Page<AgentFullRespDto> findAllAgents(Pageable pageable);
    AgentFullRespDto findAgentById(Integer agentId);
    AgentFullRespDto createAgent(CreateAgentDto createAgentDto);

    AgentFullRespDto updateAgent(UpdateAgentDto updateAgentDto);

    void deleteAgentById(Integer agentId);
}
