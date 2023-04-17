package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.agent.AgentFullRespDto;
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

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/agents")
@RestController
public class AgentController {
    AgentService agentService;

    @GetMapping
    public Page<AgentFullRespDto> getAllAgents(
            @ParameterObject Pageable pageable) {
        return agentService.findAllAgents(pageable);
    }

    @GetMapping("/{agent-id}")
    public AgentFullRespDto getAgentById(
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
    public AgentFullRespDto updateAgent(
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