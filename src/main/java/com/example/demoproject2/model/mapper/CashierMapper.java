package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class CashierMapper {

    public abstract CashierSportsStakeLimitsRecord toStakeLimitsRecord(CashierSportsStakeLimitsDto cashierSportsStakeLimitsDto);
    public abstract List<CashierSportsStakeLimitsRecord> toStakeLimitsRecords(List<CashierSportsStakeLimitsDto> stakeLimits);

    public abstract CashierSportsStakeLimitsDto toStakeLimitsDto(CashierSportsStakeLimitsRecord cashierSportsStakeLimitsDto);

    public abstract CashierRecord toCashierRecord(Integer agentId, CreateCashierDto createCashierDto);

    public abstract CashierRecord toCashierRecord(UpdateCashierDto updateCashierDto);

    @Mapping(target = "cashierRespDto", source = "cashierRecord")
    public abstract CashierFullRespDto toCashierDto(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> stakeLimits);

    public abstract List<CashierRespDto> toCashierDtos(List<CashierRecord> allCashiersByAgentId);
}
