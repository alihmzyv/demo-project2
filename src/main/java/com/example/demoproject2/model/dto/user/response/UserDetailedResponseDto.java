package com.example.demoproject2.model.dto.user.response;

import com.example.demoproject2.model.dto.permission.MenuRoleDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailedResponseDto {
    UserBasicResponseDto userBasicResponseDto;
    List<MenuRoleDto> menuRoleDtos;
}
