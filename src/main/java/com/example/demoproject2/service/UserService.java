package com.example.demoproject2.service;

import com.example.demoproject2.model.dto.permission.MenuRoleUpdateRequestDto;
import com.example.demoproject2.model.dto.user.req.UserBasicInfoUpdateDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestBasicDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestDetailedDto;
import com.example.demoproject2.model.dto.user.response.UserDetailedResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<UserDetailedResponseDto> findAllUsers(Pageable pageable);
    UserDetailedResponseDto findUserById(Long userId);
    UserDetailedResponseDto findUserByUsername(String username);
    UserDetailedResponseDto createUser(String username, UserCreateRequestDetailedDto userCreateRequestBasicDto);
    void updateUserMenuPermissions(String username, List<MenuRoleUpdateRequestDto> menuRoleUpdateRequestDtos);

    void updateUserDetails(String username, UserBasicInfoUpdateDto userBasicInfoUpdateDto);

    void deleteUserById(String username, Long userId);
    void requiresUsernameAndEmailIsUnique(UserCreateRequestBasicDto userCreateRequestBasicDto);
}
