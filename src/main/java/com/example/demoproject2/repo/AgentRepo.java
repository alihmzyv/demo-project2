package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import org.jooq.Record5;
import org.jooq.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface AgentRepo {
    AgentRecord insertAgent(AgentRecord agentRecord);

    AgentRecord updateAgent(AgentRecord agentRecord);
    Record5<AgentRecord, Integer, Integer, Integer, Integer> findAgentById(Integer agentId);

    int deleteAgentById(Integer agentId);

    Page<Record5<AgentRecord, Integer, Integer, Integer, Integer>> findAllAgents(Pageable pageable);
    Map<AgentRecord, Result<CashierRecord>> findAllAgents2(Pageable pageable);
}
