package com.example.demoproject2.model.dto.user.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserBasicResponseDto {
    Integer id;
    String firstName;
    String lastName;
    String username;
    String email;
    Short status;
    LocalDateTime timeCreated;
}
