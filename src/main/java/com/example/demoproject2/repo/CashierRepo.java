package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import org.jooq.Record2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CashierRepo {
    CashierRecord insertCashier(Integer agentId, CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimitsRecord);
    Record2<CashierRecord, CashierSportsStakeLimitsRecord> [] findCashierById(Integer cashierId);

    CashierRecord updateCashier(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords);

    Page<CashierRecord> findAllCashiersByAgentId(Integer agentId, Pageable pageable);

    int deleteCashierById(Integer cashierId);

    int deactivateCashierById(Integer cashierId);

    boolean cashierExistsById(Integer cashierId);
}
