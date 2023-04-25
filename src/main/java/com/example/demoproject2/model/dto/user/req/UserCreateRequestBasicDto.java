package com.example.demoproject2.model.dto.user.req;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;
import static com.example.demoproject2.consts.Validation.DEFAULT_VALID_EMAIL_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserCreateRequestBasicDto {
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String firstName;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String lastName;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String username;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String email;
    @NotBlank(message = DEFAULT_NOT_BLANK_MESSAGE)
    String password;
    @Min(1)
    @Max(3)
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Short status;
}
