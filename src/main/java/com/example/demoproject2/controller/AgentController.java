package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.agent.AgentRespDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.service.AgentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/agents")
@RestController
public class AgentController {
    AgentService agentService;

    @PostMapping
    public AgentRespDto createAgent(@RequestBody @Valid CreateAgentDto createAgentDto) {
        log.info(createAgentDto.toString());
        return agentService.createAgent(createAgentDto);
    }
}
