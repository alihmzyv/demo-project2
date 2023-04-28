package com.example.demoproject2.service.impl;

import com.example.demoproject2.generated.jooq.tables.records.UserMenuRecord;
import com.example.demoproject2.generated.jooq.tables.records.UsersRecord;
import com.example.demoproject2.model.dto.permission.MenuRoleUpdateRequestDto;
import com.example.demoproject2.model.dto.user.req.UserBasicInfoUpdateDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestBasicDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestDetailedDto;
import com.example.demoproject2.model.dto.user.response.UserDetailedResponseDto;
import com.example.demoproject2.model.mapper.UserMapper;
import com.example.demoproject2.repo.UserRepo;
import com.example.demoproject2.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    UserRepo userRepo;
    UserMapper userMapper;

    @Override
    public UserDetailedResponseDto createUser(String username, UserCreateRequestDetailedDto userCreateRequestDetailedDto) {
        requiresUsernameAndEmailIsUnique(userCreateRequestDetailedDto.getUserCreateRequestBasicDto());
        UsersRecord usersRecord = userMapper.toRecord(userCreateRequestDetailedDto.getUserCreateRequestBasicDto());
        List<UserMenuRecord> userMenuRecords = userMapper.toUserMenuRecords(userCreateRequestDetailedDto.getMenuRoleDtos());
        userRepo.insertUser(usersRecord, userMenuRecords);
        return findUserById(usersRecord.getId());
    }

    @Override
    public UserDetailedResponseDto findUserById(Long userId) {
        Result<Record> userById = userRepo.findUserById(userId);
        if (userById.isEmpty()) {
            throw new IllegalArgumentException(String.format("User not found with id=%d", userId));
        }
        return userMapper.toDto(userById).get(0); //there can be only one user by the id
    }

    @Override
    public void requiresUsernameAndEmailIsUnique(UserCreateRequestBasicDto userCreateRequestBasicDto) {
        String username = userCreateRequestBasicDto.getUsername();
        if (userRepo.userExistsByUsername(username)) {
            throw new IllegalArgumentException(String.format("User already exists with the username=%s", username));
        }
        String email = userCreateRequestBasicDto.getEmail();
        if (userRepo.userExistsByEmail(email)) {
            throw new IllegalArgumentException(String.format("User already exists with the username=%s", email));
        }
    }

    @Override
    public List<UserDetailedResponseDto> findAllUsers(Pageable pageable) {
        Result<Record> allUsers = userRepo.findAllUsers(pageable);
        return userMapper.toDto(allUsers);
    }

    @Override
    public void updateUserDetails(String username, UserBasicInfoUpdateDto userBasicInfoUpdateDto) {
        UsersRecord usersRecord = userMapper.toDto(userBasicInfoUpdateDto);
        userRepo.updateUser(usersRecord);
    }

    @Override
    public void deleteUserById(String username, Long userId) {
        int deletedUsers = userRepo.deleteUserById(userId);
        if (deletedUsers == 0) {
            throw new IllegalArgumentException(String.format("User not found with id=%d", userId));
        }
    }

    @Override
    public void updateUserMenuPermissions(String username, List<MenuRoleUpdateRequestDto> menuRoleUpdateRequestDtos) {
        List<UserMenuRecord> userMenuRecords = userMapper.toRecord(menuRoleUpdateRequestDtos);
        userRepo.updateUser(userMenuRecords);
    }

    @Override
    public UserDetailedResponseDto findUserByUsername(String username) {
        Result<Record> userRecord = userRepo.findUserByUsername(username);
        if (userRecord.isEmpty()) {
            throw new IllegalArgumentException(String.format("User not found with username=%s", username));
        }
        return userMapper.toDto(userRecord).get(0);
    }
}