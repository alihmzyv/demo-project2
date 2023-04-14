package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;

public interface AgentService {
    AgentRespDto createAgent(CreateAgentDto createAgentDto);
}
