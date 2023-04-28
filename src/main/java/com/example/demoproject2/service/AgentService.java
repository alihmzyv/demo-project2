package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.agent.req.AgentCreateRequestDto;
import com.example.demoproject2.model.dto.agent.req.AgentStatusUpdateRequestDto;
import com.example.demoproject2.model.dto.agent.req.AgentUpdateRequestDto;
import com.example.demoproject2.model.dto.agent.resp.AgentDetailedResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AgentService {
    List<AgentDetailedResponseDto> findAllAgents(Pageable pageable);

    AgentDetailedResponseDto findAgentById(Integer agentId);

    AgentDetailedResponseDto createAgent(String username, AgentCreateRequestDto agentCreateRequestDto);

    void updateAgentStatus(String username, AgentStatusUpdateRequestDto agentStatusUpdateRequestDto);

    void updateAgentDetails(String username, AgentUpdateRequestDto agentUpdateRequestDto);

    void deleteAgentById(String username, Integer agentId);

    void requiresAgentExistsById(Integer agentId);
}
