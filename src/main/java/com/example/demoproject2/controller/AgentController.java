package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.agent.req.AgentCreateRequestDto;
import com.example.demoproject2.model.dto.agent.req.AgentStatusUpdateRequestDto;
import com.example.demoproject2.model.dto.agent.req.AgentUpdateRequestDto;
import com.example.demoproject2.model.dto.agent.resp.AgentDetailedResponseDto;
import com.example.demoproject2.model.dto.cashier.req.CashierCreateRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.service.AgentService;
import com.example.demoproject2.service.CashierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
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
    CashierService cashierService;


    @SneakyThrows
    @PostMapping
    public AgentDetailedResponseDto createAgent(
            @RequestBody @Valid AgentCreateRequestDto agentCreateRequestDto,
            @RequestHeader("Authorization") String username) {
        return agentService.createAgent(username, agentCreateRequestDto);
    }

    @SneakyThrows
    @GetMapping
    public List<AgentDetailedResponseDto> getAllAgents(
            @ParameterObject Pageable pageable) {
        return agentService.findAllAgents(pageable);
    }

    @GetMapping("/{agent-id}")
    public AgentDetailedResponseDto getAgentById(
            @PathVariable("agent-id") Integer agentId) {
        return agentService.findAgentById(agentId);
    }

    @PutMapping
    public AgentDetailedResponseDto updateAgent(
            @RequestBody @Valid AgentUpdateRequestDto agentUpdateRequestDto,
            @RequestHeader("Authorization") String username) {
        agentService.updateAgentDetails(username, agentUpdateRequestDto);
        return agentService.findAgentById(agentUpdateRequestDto.getId());
    }

    @PatchMapping("/update-status")
    public void updateAgentStatus(
            @RequestBody @Valid AgentStatusUpdateRequestDto agentStatusUpdateRequestDto,
            @RequestHeader("Authorization") String username) {
        agentService.updateAgentStatus(username, agentStatusUpdateRequestDto);
    }

    @DeleteMapping("/{agent-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAgent(
            @PathVariable("agent-id") Integer agentId,
            @RequestHeader("Authorization") String username) {
        agentService.deleteAgentById(username, agentId);
    }

    @PostMapping("/{agent-id}")
    public CashierDetailedResponseDto createCashier(
            @PathVariable("agent-id") Integer agentId,
            @RequestBody @Valid CashierCreateRequestDto cashierCreateRequestDto,
            @RequestHeader("Authorization") String username) {
        return cashierService.createCashier(username, agentId, cashierCreateRequestDto);
    }
}
