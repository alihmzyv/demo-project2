package com.example.demoproject2.model.dto.user.req;

import com.example.demoproject2.model.dto.permission.MenuRoleDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static com.example.demoproject2.consts.Validation.DEFAULT_NOT_BLANK_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserCreateRequestDetailedDto {
    @NotNull(message = DEFAULT_NOT_BLANK_MESSAGE)
    UserCreateRequestBasicDto userCreateRequestBasicDto;
    List<MenuRoleDto> menuRoleDtos;
}
