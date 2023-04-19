package com.example.demoproject2.model.dto.cashier.req;

import com.example.demoproject2.consts.BalanceChangeType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CashierUpdateBalanceRequestDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer cashierId;
    @NotNull
    BalanceChangeType balanceChangeType;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    @Min(0)
    BigDecimal amount;
}
