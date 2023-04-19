package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.req.CashierCreateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierResponseDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierSportsStakeLimitsResponseDto;
import org.jooq.Record;
import org.jooq.Result;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER_SPORTS_STAKE_LIMITS;
import static java.util.stream.Collectors.*;
import static org.mapstruct.ReportingPolicy.WARN;

@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public abstract class CashierMapper {
    @Autowired
    CashierSportsStakesLimitsMapper cashierSportsStakesLimitsMapper;

    public CashierDetailedResponseDto toDto(CashierRecord cashierRecord, List<CashierSportsStakeLimitsRecord> cashierSportsStakeLimitsRecords) {
        if (cashierRecord == null) {
            return null;
        }

        CashierResponseDto cashierResponseDto = toDto(cashierRecord);
        List<CashierSportsStakeLimitsResponseDto> stakeLimitsRecords = cashierSportsStakesLimitsMapper.toDto(cashierSportsStakeLimitsRecords);
        return CashierDetailedResponseDto.builder()
                .cashierResponseDto(cashierResponseDto)
                .stakeLimits(stakeLimitsRecords)
                .build();
    }

    public List<CashierDetailedResponseDto> toDto(Result<Record> cashierStakeLimits) {
        Map<CashierRecord, List<CashierSportsStakeLimitsRecord>> agentCashierMap = cashierStakeLimits.collect(
                groupingBy(r -> r.into(CASHIER),
                        filtering(r -> r.get(CASHIER_SPORTS_STAKE_LIMITS.SPORTS_ID) != null,
                                mapping(r -> r.into(CASHIER_SPORTS_STAKE_LIMITS),
                                        toList()))));
        List<CashierDetailedResponseDto> cashierDetailedResponseDtos = new ArrayList<>();
        agentCashierMap.forEach((cashierRecord, stakeLimitsRecords) -> {
            CashierDetailedResponseDto cashierDetailedResponseDto = toDto(cashierRecord, stakeLimitsRecords);
            cashierDetailedResponseDtos.add(cashierDetailedResponseDto);
        });
        return cashierDetailedResponseDtos;
    }

    public abstract CashierResponseDto toDto(CashierRecord cashierRecord);
    public abstract CashierRecord toRecord(CashierCreateRequestDto cashierCreateRequestDto);
    public abstract CashierRecord toRecord(CashierUpdateRequestDto cashierUpdateRequestDto);
}
