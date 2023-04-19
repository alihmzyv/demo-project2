package com.example.demoproject2.model.dto.agent.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
public class AgentUpdateRequestDto {
    @NotNull
    Integer id;
    Integer agentCode;
    String fullName;
    String idNumber;
    @Length(min = 10, max = 10, message = DEFAULT_VALID_VOEN_MESSAGE)
    String voen;
    Integer cityId;
    String address;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String salesRepEmail;
    String phone;
    String mobile;
}
