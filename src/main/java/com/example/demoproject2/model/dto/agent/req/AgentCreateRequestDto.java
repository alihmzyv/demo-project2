package com.example.demoproject2.model.dto.agent.req;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

import static com.example.demoproject2.consts.Validation.*;

@NoArgsConstructor(force = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class AgentCreateRequestDto {
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
    @Min(value = 1, message = DEFAULT_VALID_AGENT_STATUS_MESSAGE)
    @Max(value = 2, message = DEFAULT_VALID_AGENT_STATUS_MESSAGE)
    Short status;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    BigDecimal totalPermanentBalance;
    BigDecimal debtCredit;
    BigDecimal extraDebtCredit;
}
