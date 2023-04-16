package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.Keys;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.repo.CashierRepo;
import com.example.demoproject2.util.PageUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demoproject2.consts.Conditions.CASHIER_IS_DELETED;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER_SPORTS_STAKE_LIMITS;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Repository
public class CashierRepoImpl implements CashierRepo {
    DSLContext dslContext;

    @Override
    public CashierRecord insertCashier(Integer agentId, CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimitsRecords) {
        final CashierRecord[] cashierRecordInserted = new CashierRecord[1];
        dslContext.transaction(ctx -> {
            cashierRecord.setAgentId(agentId);
            cashierRecordInserted[0] = Optional.ofNullable(ctx.dsl().insertInto(CASHIER)
                            .set(cashierRecord)
                            .returning()
                            .fetchOne())
                    .orElseThrow(() -> new RuntimeException("Something went wrong"));
            stakeLimitsRecords.forEach(stakeLimitsRecord -> stakeLimitsRecord.setCashierId(cashierRecordInserted[0].getId()));
            ctx.dsl().batchInsert(stakeLimitsRecords).execute();
        });
        return cashierRecordInserted[0];
    }

    @Override
    public Record2<CashierRecord, CashierSportsStakeLimitsRecord> [] findCashierById(Integer cashierId) {
        return dslContext.select(CASHIER, CASHIER_SPORTS_STAKE_LIMITS)
                .from(CASHIER)
                .leftJoin(CASHIER_SPORTS_STAKE_LIMITS)
                .onKey(Keys.CASHIER_SPORTS_STAKE_LIMITS__CASHIER_SPORTS_STAKE_LIMITS_CASHIER_ID_FK)
                .where(CASHIER_IS_DELETED.isFalse().and(CASHIER.ID.eq(cashierId)))
                .fetchArray();
    }

    @Override
    public CashierRecord updateCashier(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords) {
        Map<String, Object> nonNullFields = cashierRecord.fieldStream()
                .filter(field -> field.getValue(cashierRecord) != null)
                .collect(Collectors.toMap(Field::getName, field -> field.getValue(cashierRecord)));
        final CashierRecord[] cashierRecordUpdated = new CashierRecord[1];
        dslContext.transaction(ctx -> {
            cashierRecordUpdated[0] = Optional.ofNullable(ctx.dsl().update(CASHIER)
                    .set(nonNullFields)
                    .where(CASHIER_IS_DELETED.isFalse().and(CASHIER.ID.eq(cashierRecord.getId())))
                    .returning()
                    .fetchOne())
                    .orElseThrow(() -> new RuntimeException("Something went wrong"));
            ctx.dsl().batched(config -> cashierSportsStakeLimitsRecords
                    .forEach(cashierSportsStakeLimitsRecord -> ctx.dsl().update(CASHIER_SPORTS_STAKE_LIMITS)
                            .set(CASHIER_SPORTS_STAKE_LIMITS.SPORTS_ID, cashierSportsStakeLimitsRecord.getSportsId())
                            .set(CASHIER_SPORTS_STAKE_LIMITS.MIN_STAKE, cashierSportsStakeLimitsRecord.getMinStake())
                            .set(CASHIER_SPORTS_STAKE_LIMITS.MAX_STAKE, cashierSportsStakeLimitsRecord.getMaxStake())
                            .where(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID.eq(cashierRecord.getId()))
                            .execute()));
        });
        return cashierRecordUpdated[0];
    }

    @Override
    public List<CashierRecord> findAllCashiersByAgentId(Integer agentId, Integer page, Integer size) {
        int offset = PageUtil.findOffset(page, size);
        return dslContext.selectFrom(CASHIER)
                .where(CASHIER_IS_DELETED.isFalse().and(CASHIER.AGENT_ID.eq(agentId)))
                .groupBy(CASHIER.ID)
                .offset(offset)
                .limit(size)
                .fetchStream()
                .toList();
    }

    @Override
    public int deleteCashierById(Integer cashierId) {
        return dslContext.update(CASHIER)
                .set(CASHIER.STATUS, (short) 3)
                .where(CASHIER_IS_DELETED.isFalse().and(CASHIER.ID.eq(cashierId)))
                .execute();
    }
}
