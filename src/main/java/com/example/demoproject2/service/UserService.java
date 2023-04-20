package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.permission.MenuRoleUpdateRequestDto;
import com.example.demoproject2.model.dto.user.req.UserBasicInfoUpdateDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestBasicDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestDetailedDto;
import com.example.demoproject2.model.dto.user.response.UserDetailedResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    long createUser(UserCreateRequestDetailedDto userCreateRequestBasicDto);
    UserDetailedResponseDto findUserById(Long userId);
    void requiresUsernameAndEmailIsUnique(UserCreateRequestBasicDto userCreateRequestBasicDto);

    List<UserDetailedResponseDto> findAllUsers(Pageable pageable);

    void updateUser(UserBasicInfoUpdateDto userBasicInfoUpdateDto);

    void deleteUserById(Long userId);

    void updateUserPermissions(List<MenuRoleUpdateRequestDto> menuRoleUpdateRequestDtos);
}
