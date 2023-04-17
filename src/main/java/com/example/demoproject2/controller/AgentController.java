package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.agent.AgentCashiersRespDto;
import com.example.demoproject2.model.dto.agent.AgentDetailedRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import com.example.demoproject2.service.AgentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/agents")
@RestController
public class AgentController {
    AgentService agentService;

    @GetMapping("/2")
    public List<AgentCashiersRespDto> getAllAgents2(
            @ParameterObject Pageable pageable) {
        return agentService.findAllAgents2(pageable);
    }

    @GetMapping
    public Page<AgentDetailedRespDto> getAllAgents(
            @ParameterObject Pageable pageable) {
        return agentService.findAllAgents(pageable);
    }

    @GetMapping("/{agent-id}")
    public AgentDetailedRespDto getAgentById(
            @PathVariable("agent-id") Integer agentId) {
        return agentService.findAgentById(agentId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createAgent(
            @RequestBody @Valid CreateAgentDto createAgentDto) {
        log.info(createAgentDto.toString());
        agentService.createAgent(createAgentDto);
    }

    @PutMapping
    public AgentDetailedRespDto updateAgent(
            @RequestBody @Valid UpdateAgentDto updateAgentDto) {
        log.info(updateAgentDto.toString());
        return agentService.updateAgent(updateAgentDto);
    }

    @DeleteMapping("/{agent-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAgent(
            @PathVariable("agent-id") Integer agentId) {
        agentService.deleteAgentById(agentId);
        log.info("Deleted agent id: {}", agentId);
    }
}
