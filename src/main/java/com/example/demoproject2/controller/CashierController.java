package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.cashier.CashierFullRespDto;
import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.cashier.CreateCashierDto;
import com.example.demoproject2.model.dto.cashier.UpdateCashierDto;
import com.example.demoproject2.service.CashierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/agents/{agent-id}/cashiers")
@RestController
public class CashierController {
    CashierService cashierService;

    @GetMapping("/{cashier-id}")
    public CashierFullRespDto getCashierById(
            @PathVariable("cashier-id") Integer cashierId) {
        return cashierService.findCashierById(cashierId);
    }

    @GetMapping
    public List<CashierRespDto> getAllCashiersByAgentId(
            @PathVariable("agent-id") Integer agentId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return cashierService.findAllCashiersByAgentId(agentId, page, size);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CashierFullRespDto createCashier(
            @PathVariable("agent-id") Integer agentId,
            @RequestBody @Valid CreateCashierDto createCashierDto) {
        return cashierService.createCashier(agentId, createCashierDto);
    }

    @PutMapping
    public CashierFullRespDto updateCashier(
            @RequestBody @Valid UpdateCashierDto updateCashierDto) {
        return cashierService.updateCashier(updateCashierDto);
    }

    @DeleteMapping("/{cashier-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCashierById(
            @PathVariable("cashier-id") Integer cashierId) {
        cashierService.deleteCashierById(cashierId);
    }
}
