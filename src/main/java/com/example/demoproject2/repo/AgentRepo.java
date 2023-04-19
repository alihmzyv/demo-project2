package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.data.domain.Pageable;

public interface AgentRepo {
    AgentRecord insertAgent(AgentRecord agentRecord);

    AgentRecord updateAgent(AgentRecord agentRecord);
    Result<Record> findAgentById(Integer agentId);
    Result<Record> findAllAgents(Pageable pageable);
    int deleteAgentById(Integer agentId);

    boolean agentExistsById(Integer agentId);
    int countActiveCashiers(Integer agentId);

    void updateAgentStatus(Integer agentId, Short newStatus);
    void updateCashiersStatusByAgentId(Integer agentId, Short newStatus);

    void updateAgentStatus(Integer agentId);
}
