package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.repo.AgentRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.jooq.Record5;
import org.springframework.stereotype.Repository;

import static com.example.demoproject2.generated.jooq.Tables.AGENT;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static com.example.demoproject2.consts.Fields.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Repository
public class AgentRepoImpl implements AgentRepo {
    DSLContext dslContext;

    @Override
    public Record5<AgentRecord, Integer, Integer, Integer, Integer> insertAgent(AgentRecord agentRecord) {
        Integer agentId = dslContext.insertInto(AGENT)
                .set(agentRecord)
                .returning()
                .fetchOne("id", Integer.class);
        return dslContext.select(AGENT,
                NUM_OF_CASHIERS,
                NUM_OF_ACT_CASHIERS,
                NUM_OF_INACT_CASHIERS,
                NUM_OF_DEL_CASHIERS)
                .from(AGENT)
                .leftJoin(CASHIER).on(CASHIER.AGENT_ID.eq(agentId))
                .fetchAny();
    }
}
