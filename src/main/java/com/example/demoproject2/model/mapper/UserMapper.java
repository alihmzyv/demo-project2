package com.example.demoproject2.model.mapper;

import com.example.demoproject2.generated.jooq.tables.records.UserMenuRecord;
import com.example.demoproject2.generated.jooq.tables.records.UsersRecord;
import com.example.demoproject2.model.dto.permission.MenuRoleDto;
import com.example.demoproject2.model.dto.permission.MenuRoleUpdateRequestDto;
import com.example.demoproject2.model.dto.user.req.UserBasicInfoUpdateDto;
import com.example.demoproject2.model.dto.user.req.UserCreateRequestBasicDto;
import com.example.demoproject2.model.dto.user.response.UserBasicResponseDto;
import com.example.demoproject2.model.dto.user.response.UserDetailedResponseDto;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.Result;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.demoproject2.generated.jooq.Tables.USERS;
import static com.example.demoproject2.generated.jooq.Tables.USER_MENU;
import static java.util.stream.Collectors.*;
import static org.mapstruct.ReportingPolicy.WARN;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Mapper(componentModel = "spring", unmappedSourcePolicy = WARN)
public abstract class UserMapper {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    public UsersRecord toRecord(UserCreateRequestBasicDto userCreateRequestBasicDto) {
        UsersRecord usersRecord = new UsersRecord();
        usersRecord.setFirstName(userCreateRequestBasicDto.getFirstName());
        usersRecord.setLastName(userCreateRequestBasicDto.getLastName());
        usersRecord.setUsername(userCreateRequestBasicDto.getUsername());
        usersRecord.setEmail(userCreateRequestBasicDto.getEmail());
        usersRecord.setPassword(bCryptPasswordEncoder.encode(userCreateRequestBasicDto.getPassword()));
        usersRecord.setStatus(userCreateRequestBasicDto.getStatus());
        usersRecord.setTimeCreated(LocalDateTime.now().atZone(ZoneId.systemDefault()).toLocalDateTime());
        return usersRecord;
    }

    public List<UserDetailedResponseDto> toDto(Result<Record> userPermissionsRecords) {
        if (userPermissionsRecords.isEmpty()) {
            return null;
        }
        Map<UsersRecord, List<UserMenuRecord>> usersRecordsMenuRecords = userPermissionsRecords.collect(groupingBy(r -> r.into(USERS),
                filtering(r -> r.get(USER_MENU.MENU_ID) != null,
                        mapping(r -> r.into(USER_MENU),
                                toList()))));
        List<UserDetailedResponseDto> userDetailedResponseDtoList = new ArrayList<>(); //result
        usersRecordsMenuRecords.forEach((usersRecord, menuRecords) -> {
            UserBasicResponseDto userBasicResponseDto = toDto(usersRecord);
            List<MenuRoleDto> menuRoleDtos = toDto(menuRecords);
            UserDetailedResponseDto userDetailedResponseDto = UserDetailedResponseDto.builder()
                    .userBasicResponseDto(userBasicResponseDto)
                    .menuRoleDtos(menuRoleDtos)
                    .build();
            userDetailedResponseDtoList.add(userDetailedResponseDto);
        });
        return userDetailedResponseDtoList;
    }

    public abstract List<MenuRoleDto> toDto(List<UserMenuRecord> userMenuRecords);

    public abstract MenuRoleDto toDto(UserMenuRecord userMenuRecord);

    public abstract UserBasicResponseDto toDto(UsersRecord usersRecord);

    public abstract UserMenuRecord toRecord(MenuRoleDto menuRoleDto);

    public abstract List<UserMenuRecord> toUserMenuRecords(List<MenuRoleDto> menuRoleDtos);

    public abstract UsersRecord toDto(UserBasicInfoUpdateDto userBasicInfoUpdateDto);

    public abstract UserMenuRecord toRecord(MenuRoleUpdateRequestDto menuRoleUpdateRequestDto);

    public abstract List<UserMenuRecord> toRecord(List<MenuRoleUpdateRequestDto> menuRoleUpdateRequestDtos);
}
