package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentFullRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;

import java.util.List;

public interface AgentService {
    List<AgentFullRespDto> findAllAgents(Integer page, Integer size);
    AgentFullRespDto findAgentById(Integer agentId);
    AgentFullRespDto createAgent(CreateAgentDto createAgentDto);

    AgentFullRespDto updateAgent(UpdateAgentDto updateAgentDto);

    void deleteAgentById(Integer agentId);
}
