package com.example.demoproject2.service;

import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.model.dto.cashier.req.CashierCreateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateBalanceRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateStatusRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;

public interface CashierService {
    int createCashier(Integer agentId, CashierCreateRequestDto cashierCreateRequestDto);
    void deleteCashierById(Integer cashierId);

    void updateCashierStatus(Integer cashierId, CashierUpdateStatusRequestDto cashierUpdateStatusRequestDto);

    CashierDetailedResponseDto findCashierById(int cashierId);

    void updateCashier(CashierUpdateRequestDto cashierUpdateRequestDto);

    void updateBalance(CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto, BalanceType balanceType);
    void requiresCashierExistsById(Integer cashierId);
}
