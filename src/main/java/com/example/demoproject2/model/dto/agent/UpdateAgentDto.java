package com.example.demoproject2.model.dto.agent;

import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import static com.example.demoproject2.consts.Validation.DEFAULT_VALID_EMAIL_MESSAGE;
import static com.example.demoproject2.consts.Validation.DEFAULT_VALID_VOEN_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class UpdateAgentDto {
    String fullName;
    Integer city;
    String address;
    Integer agentCode;
    String idNumber;
    @Length(min = 10, max = 10, message = DEFAULT_VALID_VOEN_MESSAGE)
    String voen;
    String phone;
    String mobile;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    Short status;
}
