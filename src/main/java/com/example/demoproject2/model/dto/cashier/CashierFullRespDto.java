package com.example.demoproject2.model.dto.cashier;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierFullRespDto {
    CashierRespDto cashierRespDto;
    List<CashierSportsStakeLimitsDto> cashierSportsStakeLimits;
}
