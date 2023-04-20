package com.example.demoproject2.model.dto.user.req;

import jakarta.validation.constraints.*;
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
@ToString
public class UserBasicInfoUpdateDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Long id;
    String firstName;
    String lastName;
    String username;
    @Email(message = DEFAULT_VALID_EMAIL_MESSAGE)
    String email;
    @Min(1)
    @Max(3)
    Short status;
}
