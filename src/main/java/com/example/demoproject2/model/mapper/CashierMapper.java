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

    public List<CashierSportsStakeLimitsRecord> toStakeLimitsRecord(List<CashierSportsStakeLimitsDto> cashierSportsStakeLimitDtos) {
        return cashierSportsStakeLimitDtos.stream()
                .map(this::toStakeLimitsRecord)
                .toList();
    }

    public CashierSportsStakeLimitsRecord toStakeLimitsRecord(CashierSportsStakeLimitsDto cashierSportsStakeLimitsDto) {
        CashierSportsStakeLimitsRecord cashierSportsStakeLimitsRecord = new CashierSportsStakeLimitsRecord();
        cashierSportsStakeLimitsRecord.setSportsİd(cashierSportsStakeLimitsDto.getSportsType());
        cashierSportsStakeLimitsRecord.setMinStake(cashierSportsStakeLimitsDto.getMinStake());
        cashierSportsStakeLimitsRecord.setMaxStake(cashierSportsStakeLimitsDto.getMaxStake());
        return cashierSportsStakeLimitsRecord;
    }

    @Mapping(source = "sportsİd", target = "sportsType")
    public abstract CashierSportsStakeLimitsDto toStakeLimitsDto(CashierSportsStakeLimitsRecord cashierSportsStakeLimitsDto);

    public abstract CashierRecord toCashierRecord(Integer agentId, CreateCashierDto createCashierDto);

    public abstract CashierRecord toCashierRecord(UpdateCashierDto updateCashierDto);

    public abstract CashierFullRespDto toCashierDto(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimits);
    public abstract ArrayList<CashierFullRespDto> toCashierDtos(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimits);

    public abstract List<CashierRespDto> toCashierDtos(List<CashierRecord> allCashiersByAgentId);
}
