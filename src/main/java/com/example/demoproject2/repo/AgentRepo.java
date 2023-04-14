package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import org.jooq.Record5;

public interface AgentRepo {
    Record5<AgentRecord, Integer, Integer, Integer, Integer> insertAgent(AgentRecord agentRecord);
}
