package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.AgentCreateRequestDto;
import com.example.demoproject2.model.dto.agent.AgentDetailedResponseDto;
import com.example.demoproject2.model.dto.agent.AgentStatusUpdateRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {
    List<AgentDetailedResponseDto> findAllAgents(Pageable pageable);
    AgentDetailedResponseDto findAgentById(Integer agentId);
    int createAgent(AgentCreateRequestDto agentCreateRequestDto);
    void deleteAgentById(Integer agentId);
    void updateAgentStatus(AgentStatusUpdateRequestDto agentStatusUpdateRequestDto, Integer agentId);
}
