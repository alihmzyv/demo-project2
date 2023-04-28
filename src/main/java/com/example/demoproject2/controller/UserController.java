package com.example.demoproject2.controller;

import com.example.demoproject2.model.dto.permission.MenuRoleUpdateRequestDto;
import com.example.demoproject2.model.dto.user.req.UserBasicInfoUpdateDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestDetailedDto;
import com.example.demoproject2.model.dto.user.response.UserDetailedResponseDto;
import com.example.demoproject2.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequestMapping("/api/users")
@RestController
public class UserController {
    UserService userService;

    @GetMapping("/{user-id}")
    public UserDetailedResponseDto getUserById(
            @PathVariable("user-id") Long userId) {
        return userService.findUserById(userId);
    }

    @GetMapping
    public List<UserDetailedResponseDto> getAllUsers(
            Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @PostMapping
    public UserDetailedResponseDto createUser(
            @RequestBody @Valid UserCreateRequestDetailedDto userCreateRequestDetailedDto,
            @RequestHeader("Authorization") String username) {
        return userService.createUser(username, userCreateRequestDetailedDto);
    }

    @PutMapping
    public void updateUser(
            @RequestBody @Valid UserBasicInfoUpdateDto userBasicInfoUpdateDto,
            @RequestHeader("Authorization") String username) {
        userService.updateUserDetails(username, userBasicInfoUpdateDto);
    }

    @PatchMapping("/update-permissions")
    public void updateUserPermissions(
            @RequestBody @Valid List<MenuRoleUpdateRequestDto> menuRoleUpdateRequestDtos,
            @RequestHeader("Authorization") String username) {
        userService.updateUserMenuPermissions(username, menuRoleUpdateRequestDtos);
    }

    @DeleteMapping("/{user-id}")
    public void deleteUserById(
            @PathVariable("user-id") Long userId,
            @RequestHeader("Authorization") String username) {
        userService.deleteUserById(username, userId);
    }
}
