package com.example.demoproject2.model.dto.agent;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import static com.example.demoproject2.consts.Validation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class UpdateAgentDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Integer id;
    Integer agentCode;
    String fullName;
    String idNumber;
    Integer cityId;
    String address;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    @Length(min = 10, max = 10, message = DEFAULT_VALID_VOEN_MESSAGE)
    String voen;
    String phone;
    String mobile;
    Short status;
}
