package com.example.demoproject2.service;

import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.model.dto.cashier.*;

public interface CashierService {
    int createCashier(Integer agentId, CashierCreateRequestDto cashierCreateRequestDto);
    void deleteCashierById(Integer cashierId);

    void updateCashierStatus(CashierUpdateStatusRequestDto cashierUpdateStatusRequestDto);

    CashierDetailedResponseDto findCashierById(int cashierInsertedId);

    void updateCashier(CashierUpdateRequestDto cashierUpdateRequestDto);

    void updateBalance(CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto, BalanceType balanceType);
}
