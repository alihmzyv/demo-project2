package com.example.demoproject2.model.dto.agent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;
import static com.example.demoproject2.consts.Validation.DEFAULT_VALID_EMAIL_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String voen;
    String phone;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String mobile;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String alertsEmail;
}
