package com.example.demoproject2.service;

import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.model.dto.cashier.req.CashierCreateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateBalanceRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateStatusRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;

public interface CashierService {
    CashierDetailedResponseDto findCashierById(int cashierId);

    CashierDetailedResponseDto createCashier(String username, Integer agentId, CashierCreateRequestDto cashierCreateRequestDto);
    void updateCashierStatus(String username, CashierUpdateStatusRequestDto cashierUpdateStatusRequestDto);
    void updateCashierDetails(String username, CashierUpdateRequestDto cashierUpdateRequestDto);
    void updateCashierBalance(String username, CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto, BalanceType balanceType);
    void deleteCashierById(String username, Integer cashierId);
    void requiresCashierExistsById(Integer cashierId);
}
