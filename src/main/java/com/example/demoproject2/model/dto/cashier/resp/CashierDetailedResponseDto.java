package com.example.demoproject2.model.dto.cashier.resp;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
@Data
public class CashierDetailedResponseDto {
    CashierResponseDto cashierResponseDto;
    List<CashierSportsStakeLimitsResponseDto> stakeLimits;
}
