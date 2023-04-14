package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import org.jooq.Record5;

import java.util.Optional;

public interface AgentRepo {
    AgentRecord insertAgent(AgentRecord agentRecord);

    AgentRecord updateAgent(AgentRecord agentRecord);
    Optional<Record5<AgentRecord, Integer, Integer, Integer, Integer>> findAgentById(Integer agentId);
}
