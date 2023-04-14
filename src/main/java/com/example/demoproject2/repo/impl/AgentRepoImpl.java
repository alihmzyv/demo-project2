package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.repo.AgentRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record5;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .returning()
                .fetchOne();
    }

    @Override
    public Optional<Record5<AgentRecord, Integer, Integer, Integer, Integer>> findAgentById(Integer agentId) {
        Record5<AgentRecord, Integer, Integer, Integer, Integer> agentRespRec = dslContext.select(
                        AGENT,
                        NUM_OF_CASHIERS,
                        NUM_OF_ACT_CASHIERS,
                        NUM_OF_INACT_CASHIERS,
                        NUM_OF_DEL_CASHIERS)
                .from(AGENT)
                .leftJoin(CASHIER).on(CASHIER.AGENT_ID.eq(agentId))
                .groupBy(AGENT.ID)
                .fetchAny();
        return Optional.ofNullable(agentRespRec);
    }
}
