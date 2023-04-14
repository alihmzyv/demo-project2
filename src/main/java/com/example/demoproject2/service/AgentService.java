package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;

public interface AgentService {
    AgentDto createAgent(CreateAgentDto createAgentDto);

    AgentDto updateAgent(UpdateAgentDto updateAgentDto);

    void deleteAgentById(Integer agentId);
}
