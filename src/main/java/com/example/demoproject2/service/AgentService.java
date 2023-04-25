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

    int createAgent(AgentCreateRequestDto agentCreateRequestDto);

    void deleteAgentById(Integer agentId);

    void updateAgentStatus(AgentStatusUpdateRequestDto agentStatusUpdateRequestDto);

    void updateAgent(AgentUpdateRequestDto agentUpdateRequestDto);

    void requiresAgentExistsById(Integer agentId);
}
