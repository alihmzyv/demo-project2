package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.Keys;
import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.repo.AgentRepo;
import com.example.demoproject2.util.PageUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.SortField;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.demoproject2.consts.Conditions.AGENT_IS_DELETED;
import static com.example.demoproject2.consts.Fields.*;
import static com.example.demoproject2.generated.jooq.Tables.AGENT;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Repository
public class AgentRepoImpl implements AgentRepo {
    DSLContext dslContext;

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
    public Record5<AgentRecord, Integer, Integer, Integer, Integer> findAgentById(Integer agentId) {
        return dslContext.select(
                        AGENT,
                        NUM_OF_CASHIERS,
                        NUM_OF_ACT_CASHIERS,
                        NUM_OF_INACT_CASHIERS,
                        NUM_OF_DEL_CASHIERS)
                .from(AGENT)
                .leftJoin(CASHIER).onKey(Keys.CASHIER__CASHIER_AGENT_ID_FK)
                .where(AGENT_IS_DELETED.isFalse().and(AGENT.ID.eq(agentId)))
                .groupBy(AGENT.ID)
                .fetchAny();
    }

    @Override
    public int deleteAgentById(Integer agentId) {
        return dslContext.update(AGENT)
                .set(AGENT.STATUS, (short) 3)
                .where(AGENT_IS_DELETED.isFalse().and(AGENT.ID.eq(agentId)))
                .execute();
    }

    @Override
    public PageImpl<Record5<AgentRecord, Integer, Integer, Integer, Integer>> findAllAgents(Pageable pageable) {
        Collection<SortField<?>> orderByFields = PageUtil.getOrderByFields(AGENT, pageable.getSort());
        List<Record5<AgentRecord, Integer, Integer, Integer, Integer>> records = dslContext.select(
                        AGENT,
                        NUM_OF_CASHIERS,
                        NUM_OF_ACT_CASHIERS,
                        NUM_OF_INACT_CASHIERS,
                        NUM_OF_DEL_CASHIERS)
                .from(AGENT)
                .leftJoin(CASHIER).onKey(Keys.CASHIER__CASHIER_AGENT_ID_FK)
                .where(AGENT_IS_DELETED.isFalse())
                .groupBy(AGENT.ID)
                .orderBy(orderByFields)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchStream().toList();
        return new PageImpl<>(records, pageable, findCount());
    }

    private long findCount() {
        return dslContext.fetchCount(AGENT);
    }
}
