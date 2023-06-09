package com.example.demoproject2.controller;

import com.example.demoproject2.consts.BalanceType;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateBalanceRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateRequestDto;
import com.example.demoproject2.model.dto.cashier.req.CashierUpdateStatusRequestDto;
import com.example.demoproject2.model.dto.cashier.resp.CashierDetailedResponseDto;
import com.example.demoproject2.service.CashierService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/cashiers")
@RestController
public class CashierController {
    CashierService cashierService;

    @PatchMapping("/update-status")
    public void updateCashierStatus(
            @RequestBody @Valid CashierUpdateStatusRequestDto cashierUpdateStatusRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierStatus(username, cashierUpdateStatusRequestDto);
    }

    @PutMapping
    public CashierDetailedResponseDto updateCashier(
            @RequestBody @Valid CashierUpdateRequestDto cashierUpdateRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierDetails(username, cashierUpdateRequestDto);
        return cashierService.findCashierById(cashierUpdateRequestDto.getId());
    }

    @DeleteMapping("/{cashier-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCashierById(
            @PathVariable("cashier-id") Integer cashierId,
            @RequestHeader("Authorization") String username) {
        cashierService.deleteCashierById(username, cashierId);
    }

    @PatchMapping("/update-next-permanent-balance")
    public void updateCashierPermanentBalance(
            @RequestBody @Valid CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierBalance(username, cashierUpdateBalanceRequestDto, BalanceType.NEXT_PERMANENT_BALANCE);
    }

    @PatchMapping("/update-current-balance")
    public void updateCashierCurrentBalance(
            @RequestBody @Valid CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierBalance(username, cashierUpdateBalanceRequestDto, BalanceType.CURRENT_BALANCE);
    }

    @PatchMapping("/update-debt-credit")
    public void updateDebtCredit(
            @RequestBody @Valid CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierBalance(username, cashierUpdateBalanceRequestDto, BalanceType.DEBT_CREDIT);
    }

    @PatchMapping("/update-extra-debt-credit")
    public void updateExtraDebtCredit(
            @RequestBody @Valid CashierUpdateBalanceRequestDto cashierUpdateBalanceRequestDto,
            @RequestHeader("Authorization") String username) {
        cashierService.updateCashierBalance(username, cashierUpdateBalanceRequestDto, BalanceType.EXTRA_DEBT_CREDIT);
    }
}
