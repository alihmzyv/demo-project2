package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.AgentRecord;
import com.example.demoproject2.model.dto.agent.AgentDto;
import com.example.demoproject2.model.dto.agent.CreateAgentDto;
import com.example.demoproject2.model.dto.agent.UpdateAgentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgentMapper {
    AgentRecord toRecord(CreateAgentDto createAgentDto);
    AgentRecord toRecord(UpdateAgentDto updateAgentDto);
    AgentDto toDto(AgentRecord agentRecord);
}
