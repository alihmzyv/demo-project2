package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import org.jooq.Record5;
import org.jooq.Result;

public interface AgentRepo {
    Result<Record5<AgentRecord, Integer, Integer, Integer, Integer>> insertAgent(AgentRecord agentRecord);
}
