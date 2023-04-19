package com.example.demoproject2.repo.impl;

import com.example.demoproject2.consts.BalanceChangeType;
import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.repo.CashierRepo;
import com.example.demoproject2.util.JooqUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demoproject2.consts.BalanceChangeType.DECREASE;
import static com.example.demoproject2.consts.BalanceChangeType.INCREASE;
import static com.example.demoproject2.consts.Condition.CASHIER_IS_DELETED;
import static com.example.demoproject2.consts.Condition.CASHIER_IS_NOT_DELETED;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER_SPORTS_STAKE_LIMITS;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Repository
public class CashierRepoImpl implements CashierRepo {
    DSLContext dslContext;
    AgentRepo agentRepo;
    JooqUtil jooqUtil;

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
    public Result<Record> findCashierById(Integer cashierId) {
        return dslContext.select()
                .from(CASHIER)
                .leftJoin(CASHIER_SPORTS_STAKE_LIMITS)
                .on(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID.eq(CASHIER.ID))
                .where(CASHIER_IS_NOT_DELETED.and(CASHIER.ID.eq(cashierId)))
                .fetch();
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
    public int deleteCashierById(Integer cashierId) {
        return dslContext.update(CASHIER)
                .set(CASHIER.STATUS, (short) 3)
                .where(CASHIER_IS_DELETED.isFalse().and(CASHIER.ID.eq(cashierId)))
                .execute();
    }

    @Override
    public void updateCashierStatus(Integer cashierId, Short newStatus) {
        int updatedCashiers = dslContext.update(CASHIER)
                .set(CASHIER.STATUS, newStatus)
                .where(CASHIER.ID.eq(cashierId).and(CASHIER_IS_NOT_DELETED))
                .execute();
        if (updatedCashiers != 0) {
            Integer agentId = findAgentIdByCashierId(cashierId);
            agentRepo.updateAgentStatus(agentId);
        }
    }

    @Override
    public boolean cashierExistsById(Integer cashierId) {
        return dslContext.fetchCount(CASHIER, CASHIER_IS_NOT_DELETED.and(CASHIER.ID.eq(cashierId))) != 0;
    }

    @Override
    public Integer findAgentIdByCashierId(Integer cashierId) {
        return Optional.ofNullable(dslContext.select(CASHIER.AGENT_ID)
                        .from(CASHIER)
                        .where(CASHIER.ID.eq(cashierId))
                        .fetchOne())
                .map(Record1::component1)
                .orElse(null);
    }

    @Override
    public void updateBalance(Integer cashierId, BalanceType balanceType, BalanceChangeType balanceChangeType, BigDecimal amount) {
        //find balance field
        Optional<Field<BigDecimal>> fieldOptional = jooqUtil.findField(
                CASHIER,
                balanceType.name(),
                BigDecimal.class);
        fieldOptional.ifPresentOrElse(
                field -> {
                    Record cashier = findCashierById(cashierId).get(0);
                    BigDecimal previousAmount = cashier.get(field); //previous amount
                    BigDecimal updatedAmount;
                    if (balanceChangeType.equals(DECREASE)) {
                        updatedAmount = previousAmount.subtract(amount); //decrease amount
                    } else if (balanceChangeType.equals(INCREASE)) {
                        updatedAmount = previousAmount.add(amount); //increase amount
                    } else {
                        throw new IllegalArgumentException(String.format("Balance change type is incorrect: %s", balanceChangeType));
                    }
                    updateBalance(cashierId, field, updatedAmount); //set the balance to the updatedAmount
        }, () -> {
                    //balance field not found
            throw new IllegalArgumentException(String.format("Balance type is incorrect: %s", balanceType));
        });
    }

    @Override
    public void updateBalance(Integer cashierId, Field<BigDecimal> field, BigDecimal newAmount) {
        dslContext.update(CASHIER)
                .set(field, newAmount)
                .where(CASHIER.ID.eq(cashierId))
                .execute();
    }

    private long findCount() {
        return dslContext.fetchCount(CASHIER);
    }
}
