package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.agent.*;
import com.example.demoproject2.model.dto.cashier.CashierDetailedResponseDto;
import com.example.demoproject2.model.dto.cashier.CashierCreateRequestDto;
import com.example.demoproject2.service.AgentService;
import com.example.demoproject2.service.CashierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
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

    @PostMapping
    public AgentDetailedResponseDto createAgent(
            @RequestBody @Valid AgentCreateRequestDto agentCreateRequestDto) {
        int agentInsertedId = agentService.createAgent(agentCreateRequestDto);
        return agentService.findAgentById(agentInsertedId);
    }

    @PatchMapping("/update-status")
    public void updateAgentStatus(
            @RequestBody @Valid AgentStatusUpdateRequestDto agentStatusUpdateRequestDto) {
        agentService.updateAgentStatus(agentStatusUpdateRequestDto);
    }

    @DeleteMapping("/{agent-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAgent(
            @PathVariable("agent-id") Integer agentId) {
        agentService.deleteAgentById(agentId);
        log.info("Deleted agent id: {}", agentId);
    }

    @PostMapping("/{agent-id}")
    public CashierDetailedResponseDto createCashier(
            @PathVariable("agent-id") Integer agentId,
            @RequestBody @Valid CashierCreateRequestDto cashierCreateRequestDto) {
        int cashierInsertedId = cashierService.createCashier(agentId, cashierCreateRequestDto);
        return cashierService.findCashierById(cashierInsertedId);
    }
}
