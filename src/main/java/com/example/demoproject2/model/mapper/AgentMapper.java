package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentMapper {
    AgentRecord toRecord(CreateAgentDto createAgentDto);
}
