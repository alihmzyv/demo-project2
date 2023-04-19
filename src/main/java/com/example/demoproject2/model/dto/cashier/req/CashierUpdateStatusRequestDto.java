package com.example.demoproject2.model.dto.cashier.req;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.example.demoproject2.consts.Validation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashierUpdateStatusRequestDto {
    @Min(value = 1, message = DEFAULT_VALID_CASHIER_STATUS_MESSAGE)
    @Max(value = 3, message = DEFAULT_VALID_CASHIER_STATUS_MESSAGE)
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Short newStatus;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String comment;
}
