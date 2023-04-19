package com.example.demoproject2.repo;

import com.example.demoproject2.consts.BalanceChangeType;
import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Result;

import java.math.BigDecimal;
import java.util.List;

public interface CashierRepo {
    CashierRecord insertCashier(Integer agentId, CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimitsRecord);
    Result<Record> findCashierById(Integer cashierId);
    CashierRecord updateCashier(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords);
    int deleteCashierById(Integer cashierId);
    void updateCashierStatus(Integer cashierId, Short newStatus);
    boolean cashierExistsById(Integer cashierId);
    Integer findAgentIdByCashierId(Integer cashierId);
    void updateBalance(Integer cashierId, BalanceType balanceType, BalanceChangeType balanceChangeType, BigDecimal amount);
    void updateBalance(Integer cashierId, Field<BigDecimal> field, BigDecimal newAmount);
}
