package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.cashier.CashierFullRespDto;
import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.cashier.CreateCashierDto;
import com.example.demoproject2.model.dto.cashier.UpdateCashierDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CashierService {
    CashierFullRespDto createCashier(Integer agentId, CreateCashierDto createCashierDto);

    CashierFullRespDto updateCashier(UpdateCashierDto updateCashierDto);

    CashierFullRespDto findCashierById(Integer cashierId);

    Page<CashierRespDto> findAllCashiersByAgentId(Integer agentId, Pageable pageable);

    void deleteCashierById(Integer cashierId);

    void deactivateCashierById(Integer cashierId);
}
