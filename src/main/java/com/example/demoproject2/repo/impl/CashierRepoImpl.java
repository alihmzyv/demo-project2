package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.repo.CashierRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.jooq.Record2;
import org.jooq.UpdatableRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demoproject2.generated.jooq.Tables.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class CashierRepoImpl implements CashierRepo {
    DSLContext dslContext;

    @Override
    public CashierRecord insertCashier(Integer agentId, CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimitsRecords) {
        dslContext.transaction(ctx -> {
            cashierRecord.setAgentİd(agentId);
            CashierRecord cashierRecordInserted = Optional.ofNullable(dslContext.insertInto(CASHIER)
                            .set(cashierRecord)
                            .returning()
                            .fetchOne())
                    .orElseThrow(() -> new RuntimeException("Something went wrong"));
            stakeLimitsRecords.forEach(stakeLimitsRecord -> stakeLimitsRecord.setCashierİd(cashierRecordInserted.getİd()));
            dslContext.batchInsert(stakeLimitsRecords).execute();
        });
        return cashierRecord;
    }

    @Override
    public Record2<CashierRecord, CashierSportsStakeLimitsRecord> [] findCashierById(Integer cashierId) {
        return dslContext.select(CASHIER, CASHIER_SPORTS_STAKE_LIMITS)
                .from(CASHIER)
                .leftJoin(CASHIER_SPORTS_STAKE_LIMITS)
                .on(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID.eq(cashierId))
                .groupBy(CASHIER.ID)
                .fetchArray();
    }

    @Override
    public CashierRecord updateCashier(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords) {
        dslContext.transaction(ctx -> {
            CashierRecord cashierRecordUpdated = Optional.ofNullable(ctx.dsl().update(CASHIER)
                    .set(cashierRecord)
                    .where(CASHIER.ID.eq(cashierRecord.getİd()))
                    .returning()
                    .fetchOne())
                    .orElseThrow(() -> new RuntimeException("Something went wrong"));
            cashierSportsStakeLimitsRecords.forEach(stakeLimitsRecord -> stakeLimitsRecord.setCashierİd(cashierRecordUpdated.getİd()));
            ctx.dsl().batchUpdate((UpdatableRecord<?>) cashierSportsStakeLimitsRecords)
                    .execute();
        });
        return cashierRecord;
    }

    @Override
    public List<CashierRecord> findAllCashiersByAgentId(Integer agentId, Integer page, Integer size) {
        return dslContext.selectFrom(CASHIER)
                .where(CASHIER.AGENT_ID.eq(agentId))
                .groupBy(CASHIER.ID)
                .fetchStream()
                .toList();
    }

    @Override
    public int deleteCashierById(Integer cashierId) {
        return dslContext.deleteFrom(CASHIER)
                .where(CASHIER.ID.eq(cashierId))
                .execute();
    }
}
