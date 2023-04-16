package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import org.jooq.Record5;

import java.util.List;

public interface AgentRepo {
    AgentRecord insertAgent(AgentRecord agentRecord);

    AgentRecord updateAgent(AgentRecord agentRecord);
    Record5<AgentRecord, Integer, Integer, Integer, Integer> findAgentById(Integer agentId);

    int deleteAgentById(Integer agentId);

    List<Record5<AgentRecord, Integer, Integer, Integer, Integer>> findAllAgents(Integer page, Integer size);
}
