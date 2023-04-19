package com.example.demoproject2.model.dto.cashier;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;
import static com.example.demoproject2.consts.Validation.DEFAULT_VALID_FROZEN_STATUS_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CashierUpdateRequestDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer id;
    Integer cashierCode;
    String provider;
    String fullName;
    String phone;
    String mobile;
    String email;
    String salesRepEmail;
    Integer zoneId;
    Integer cityId;
    Integer regionId;
    String address;
    String macAddress;
    @Min(value = 1, message = DEFAULT_VALID_FROZEN_STATUS_MESSAGE)
    @Max(value = 2, message = DEFAULT_VALID_FROZEN_STATUS_MESSAGE)
    Short frozenStatus;
    @Size(min = 1)
    List<CashierSportsStakeLimitsRequestDto> cashierSportsStakeLimits;
}
