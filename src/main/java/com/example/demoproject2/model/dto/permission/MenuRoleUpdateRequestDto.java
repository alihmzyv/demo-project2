package com.example.demoproject2.model.dto.permission;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class MenuRoleUpdateRequestDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Long userId;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Long menuId;
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    Long roleId;
}
