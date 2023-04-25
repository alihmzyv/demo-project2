package com.example.demoproject2.service.impl;

import com.example.demoproject2.consts.BalanceChangeType;
import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.generated.jooq.tables.records.CashierRecord;
import com.example.demoproject2.generated.jooq.tables.records.CashierSportsStakeLimitsRecord;
import com.example.demoproject2.model.dto.cashier.req.CashierCreateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateBalanceRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateStatusRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.model.mapper.CashierMapper;
import com.example.demoproject2.model.mapper.CashierSportsStakesLimitsMapper;
import com.example.demoproject2.repo.CashierRepo;
import com.example.demoproject2.service.CashierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class CashierServiceImpl implements CashierService {
    CashierRepo cashierRepo;
    CashierMapper cashierMapper;
    CashierSportsStakesLimitsMapper cashierSportsStakesLimitsMapper;

    @Override
    public int createCashier(Integer agentId, CashierCreateRequestDto cashierCreateRequestDto) {
        CashierRecord cashierRecord = cashierMapper.toRecord(cashierCreateRequestDto);
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecords = cashierSportsStakesLimitsMapper.toRecord(cashierCreateRequestDto.getCashierSportsStakeLimitDtos());
        CashierRecord cashierRecordInserted = cashierRepo.insertCashier(agentId, cashierRecord, stakeLimitsRecords);
        return cashierRecordInserted.getId();
    }

    @Override
    public void deleteCashierById(Integer cashierId) {
        int deletedRows = cashierRepo.deleteCashierById(cashierId);
        if (deletedRows == 0)
            throw new IllegalArgumentException(String.format("Cashier not found with id: %d", cashierId));
    }

    @Override
    public void updateCashierStatus(CashierUpdateStatusRequestDto cashierUpdateStatusRequestDto) {
        Integer cashierId = cashierUpdateStatusRequestDto.getCashierId();
        requiresCashierExistsById(cashierId);
        log.info(cashierUpdateStatusRequestDto.getComment()); //TODO: log to db
        Short newStatus = cashierUpdateStatusRequestDto.getNewStatus();
        cashierRepo.updateCashierStatus(cashierId, newStatus);
    }

    @Override
    public CashierDetailedResponseDto findCashierById(int cashierId) {
        Result<Record> cashierById = cashierRepo.findCashierById(cashierId);
        if (cashierById.isEmpty()) {
            throw new IllegalArgumentException(String.format("Cashier not found with id=%d", cashierId));
        } else return cashierMapper.toDto(cashierById).get(0);
    }

    @Override
    public void updateCashier(CashierUpdateRequestDto cashierUpdateRequestDto) {
        CashierRecord cashierRecord = cashierMapper.toRecord(cashierUpdateRequestDto);
        List<CashierSportsStakeLimitsRecord> stakeLimitsRecords = cashierSportsStakesLimitsMapper.toRecord(cashierUpdateRequestDto.getCashierSportsStakeLimits());
        cashierRepo.updateCashier(cashierRecord, stakeLimitsRecords);
    }

    @Override
    public void updateBalance(CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto, BalanceType balanceType) {
        Integer cashierId = cashierUpdateBalanceRequestDto.getCashierId();
        if (!cashierRepo.cashierExistsById(cashierId)) {
            throw new IllegalArgumentException(String.format("Cashier not found with id=%d", cashierId));
        }
        BalanceChangeType balanceChangeType = cashierUpdateBalanceRequestDto.getBalanceChangeType();
        BigDecimal amount = cashierUpdateBalanceRequestDto.getAmount();
        cashierRepo.updateBalance(cashierId, balanceType, balanceChangeType, amount);
    }

    @Override
    public void requiresCashierExistsById(Integer cashierId) {
        if (!cashierRepo.cashierExistsById(cashierId)) {
            throw new IllegalArgumentException(String.format("Cashier not found with id=%d", cashierId));
        }
    }
}
