package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.cashier.CashierFullRespDto;
import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.cashier.CreateCashierDto;
import com.example.demoproject2.model.dto.cashier.UpdateCashierDto;

import java.util.List;

public interface CashierService {
    CashierFullRespDto createCashier(Integer agentId, CreateCashierDto createCashierDto);

    CashierFullRespDto updateCashier(UpdateCashierDto updateCashierDto);

    CashierFullRespDto findCashierById(Integer cashierId);

    List<CashierRespDto> findAllCashiersByAgentId(Integer agentId, Integer page, Integer size);

    void deleteCashierById(Integer cashierId);
}
