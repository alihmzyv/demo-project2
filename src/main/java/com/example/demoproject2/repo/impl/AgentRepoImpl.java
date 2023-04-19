package com.example.demoproject2.repo.impl;

import com.example.demoproject2.consts.Status;
import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.util.PageUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.demoproject2.consts.Condition.*;
import static com.example.demoproject2.consts.Status.DELETED_STATUS_VALUE;
import static com.example.demoproject2.generated.jooq.Tables.*;
import static org.jooq.impl.DSL.count;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Repository
public class AgentRepoImpl implements AgentRepo {
    DSLContext dslContext;

    @Override
    public Result<Record> findAgentById(Integer agentId) {
        return dslContext.select()
                .from(AGENT)
                .leftJoin(CASHIER)
                .on(CASHIER.AGENT_ID.eq(AGENT.ID))
                .leftJoin(CASHIER_SPORTS_STAKE_LIMITS)
                .on(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID.eq(CASHIER.ID))
                .where(AGENT_IS_NOT_DELETED.and(AGENT.ID.eq(agentId)))
                .fetch();
    }

    @Override
    public Result<Record> findAllAgents(Pageable pageable) {
        Collection<SortField<?>> orderByFields = PageUtil.getOrderByFields(AGENT, pageable.getSort());
        return dslContext.select()
                .from(AGENT)
                .leftJoin(CASHIER)
                .on(CASHIER.AGENT_ID.eq(AGENT.ID))
                .leftJoin(CASHIER_SPORTS_STAKE_LIMITS)
                .on(CASHIER_SPORTS_STAKE_LIMITS.CASHIER_ID.eq(CASHIER.ID))
                .where(AGENT_IS_NOT_DELETED)
                .orderBy(orderByFields)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public AgentRecord insertAgent(AgentRecord agentRecord) {
        return dslContext.insertInto(AGENT)
                .set(agentRecord)
                .returning()
                .fetchOne();
    }

    @Override
    public AgentRecord updateAgent(AgentRecord agentRecord) {
        Map<String, Object> nonNullFields = agentRecord.fieldStream()
                .filter(field -> field.getValue(agentRecord) != null)
                .collect(Collectors.toMap(Field::getName, field -> field.getValue(agentRecord)));
        return dslContext.update(AGENT)
                .set(nonNullFields)
                .where(AGENT_IS_DELETED.isFalse().and(AGENT.ID.eq(agentRecord.getId())))
                .returning()
                .fetchOne();
    }

    @Override
    public int deleteAgentById(Integer agentId) {
        return dslContext.update(AGENT)
                .set(AGENT.STATUS, DELETED_STATUS_VALUE)
                .where(AGENT_IS_DELETED.isFalse().and(AGENT.ID.eq(agentId)))
                .execute();
    }

    @Override
    public boolean agentExistsById(Integer agentId) {
        return dslContext.fetchCount(AGENT, AGENT.ID.eq(agentId)) != 0;
    }

    @Override
    public int countActiveCashiers(Integer agentId) {
        return Optional.ofNullable(dslContext.select(count())
                .from(AGENT)
                .leftJoin(CASHIER).on(CASHIER.AGENT_ID.eq(agentId))
                .where(CASHIER_ACTIVE)
                .groupBy(CASHIER.STATUS)
                .fetchOne())
                .map(Record1::component1)
                .orElse(0);
    }

    @Override
    public void updateAgentStatus(Integer agentId, Short newStatus) {
        int updatedAgents = dslContext.update(AGENT)
                .set(AGENT.STATUS, newStatus)
                .where(AGENT.ID.eq(agentId).and(AGENT.STATUS.ne(newStatus)))
                .execute();
        if (updatedAgents != 0) {
            updateCashiersStatusByAgentId(agentId, newStatus);
        }
    }

    @Override
    public void updateCashiersStatusByAgentId(Integer agentId, Short newStatus) {
        dslContext.update(CASHIER)
                .set(CASHIER.STATUS, newStatus)
                .where(CASHIER.AGENT_ID.eq(agentId).and(CASHIER_IS_NOT_DELETED))
                .execute();
    }

    @Override
    public void updateAgentStatus(Integer agentId) {
        int activeCashiers = countActiveCashiers(agentId);
        if (activeCashiers == 0) {
            updateAgentStatus(agentId, Status.INACTIVE_STATUS_VALUE);
        } else {
            updateAgentStatus(agentId, Status.ACTIVE_STATUS_VALUE);
        }
    }


    private long findCount() {
        return dslContext.fetchCount(AGENT);
    }
}
