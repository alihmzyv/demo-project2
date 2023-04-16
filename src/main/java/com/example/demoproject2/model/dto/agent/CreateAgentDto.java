package com.example.demoproject2.model.dto.agent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import static com.example.demoproject2.consts.Validation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CreateAgentDto {
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String fullName;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer cityId;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String address;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer agentCode;
    String idNumber;
    @Length(min = 10, max = 10, message = DEFAULT_VALID_VOEN_MESSAGE)
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String voen;
    String phone;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String mobile;
    Short status;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    BigDecimal totalPermanentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
}
