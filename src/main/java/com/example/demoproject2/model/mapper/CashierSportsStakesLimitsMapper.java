package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.req.CashierSportsStakeLimitsRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierSportsStakeLimitsResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public abstract class CashierSportsStakesLimitsMapper {
    public abstract CashierSportsStakeLimitsResponseDto toDto(CashierSportsStakeLimitsRecord cashierSportsStakeLimitsRecord);
    public abstract List<CashierSportsStakeLimitsResponseDto> toDto(List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords);
    public abstract List<CashierSportsStakeLimitsRecord> toRecord(List<CashierSportsStakeLimitsRequestDto> cashierSportsStakeLimitsRequestDtoList);
}
