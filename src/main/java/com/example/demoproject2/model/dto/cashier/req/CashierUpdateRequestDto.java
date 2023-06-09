package com.example.demoproject2.model.dto.cashier.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.example.demoproject2.consts.Validation.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CashierUpdateRequestDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer id;
    Integer cashierCode;
    String provider;
    String fullName;
    String phone;
    String mobile;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    Integer zoneId;
    Integer cityId;
    Integer regionId;
    String address;
    String macAddress;
    @Min(value = 1, message = DEFAULT_VALID_FROZEN_STATUS_MESSAGE)
    @Max(value = 2, message = DEFAULT_VALID_FROZEN_STATUS_MESSAGE) //frozen
    Short frozenStatus;
    List<CashierSportsStakeLimitsRequestDto> cashierSportsStakeLimits;
}
