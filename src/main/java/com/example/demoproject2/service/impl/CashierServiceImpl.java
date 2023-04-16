package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.CashierFullRespDto;
import com.example.demoproject2.model.dto.cashier.CashierRespDto;
import com.example.demoproject2.model.dto.cashier.CreateCashierDto;
import com.example.demoproject2.model.dto.cashier.UpdateCashierDto;
import com.example.demoproject2.model.mapper.CashierMapper;
import com.example.demoproject2.repo.CashierRepo;
import com.example.demoproject2.service.CashierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class CashierServiceImpl implements CashierService {
    CashierRepo cashierRepo;
    CashierMapper cashierMapper;

    @Override
    public CashierFullRespDto createCashier(Integer agentId, CreateCashierDto createCashierDto) {
        CashierRecord cashierRecord = cashierMapper.toCashierRecord(agentId, createCashierDto);
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecords = cashierMapper.toStakeLimitsRecords(createCashierDto.getCashierSportsStakeLimitDtos());
        CashierRecord cashierRecordInserted = cashierRepo.insertCashier(agentId, cashierRecord, stakeLimitsRecords);
        log.info("Cashier inserted id: {}", cashierRecordInserted.getId());
        Record2<CashierRecord, CashierSportsStakeLimitsRecord>[] cashierById = cashierRepo.findCashierById(cashierRecordInserted.getId());
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecordsInserted = Arrays.stream(cashierById)
                .map(Record2::component2)
                .toList();
        return cashierMapper.toCashierDto(cashierRecordInserted, stakeLimitsRecordsInserted);
    }

    @Override
    public CashierFullRespDto updateCashier(UpdateCashierDto updateCashierDto) {
        CashierRecord cashierRecord = cashierMapper.toCashierRecord(updateCashierDto);
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecords = cashierMapper.toStakeLimitsRecords(updateCashierDto.getCashierSportsStakeLimits());
        CashierRecord cashierRecordUpdated = cashierRepo.updateCashier(cashierRecord, stakeLimitsRecords);
        Record2<CashierRecord, CashierSportsStakeLimitsRecord>[] cashierById = cashierRepo.findCashierById(cashierRecord.getId());
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecordsUpdated = Arrays.stream(cashierById)
                .map(Record2::component2)
                .toList();
        return cashierMapper.toCashierDto(cashierRecordUpdated, stakeLimitsRecordsUpdated);
    }

    @Override
    public CashierFullRespDto findCashierById(Integer cashierId) {
        Record2<CashierRecord, CashierSportsStakeLimitsRecord>[] cashierById = Optional.ofNullable(cashierRepo.findCashierById(cashierId))
                .filter(record2s -> record2s.length != 0)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cashier not found with id: %d", cashierId)));
        CashierRecord cashierRecord = cashierById[0].component1();
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecordsFetched = Arrays.stream(cashierById)
                .map(Record2::component2)
                .toList();
        return cashierMapper.toCashierDto(cashierRecord, stakeLimitsRecordsFetched);
    }

    @Override
    public List<CashierRespDto> findAllCashiersByAgentId(Integer agentId, Integer page, Integer size) {
        List<CashierRecord> allCashiersByAgentId = cashierRepo.findAllCashiersByAgentId(agentId, page, size);
        return cashierMapper.toCashierDtos(allCashiersByAgentId);
    }

    @Override
    public void deleteCashierById(Integer cashierId) {
        int deletedRows = cashierRepo.deleteCashierById(cashierId);
        if (deletedRows == 0) throw new IllegalArgumentException(String.format("Cashier not found with id: %d", cashierId));
    }
}
