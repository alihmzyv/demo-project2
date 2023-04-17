package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class CashierMapper {

    public abstract CashierSportsStakeLimitsRecord toRecord(CashierSportsStakeLimitsDto cashierSportsStakeLimitsDto);
    public abstract List<CashierSportsStakeLimitsRecord> toRecord(List<CashierSportsStakeLimitsDto> stakeLimits);

    public abstract CashierSportsStakeLimitsDto toDto(CashierSportsStakeLimitsRecord cashierSportsStakeLimitsDto);
    public abstract CashierRespDto toDto(CashierRecord cashierRecord);

    public abstract CashierRecord toRecord(Integer agentId, CreateCashierDto createCashierDto);

    public abstract CashierRecord toRecord(UpdateCashierDto updateCashierDto);

    @Mapping(target = "cashierRespDto", source = "cashierRecord")
    public abstract CashierFullRespDto toDto(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimits);

    public List<CashierRespDto> toDto(List<CashierRecord> allCashiersByAgentId) {
        if ( allCashiersByAgentId == null ) {
            return null;
        }

        List<CashierRespDto> list = new ArrayList<CashierRespDto>( allCashiersByAgentId.size() );
        for ( CashierRecord cashierRecord : allCashiersByAgentId ) {
            if (cashierRecord.getId() != null)
            list.add( toDto( cashierRecord ) );
        }

        return list;
    }
}
