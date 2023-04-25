package com.example.demoproject2.repo;

import com.example.demoproject2.generated.jooq.tables.records.UserMenuRecord;
import com.example.demoproject2.generated.jooq.tables.records.UsersRecord;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepo {
    long insertUser(UsersRecord usersRecord, List<UserMenuRecord> userMenuRecords);

    boolean userExistsByUsername(String username);

    boolean userExistsByEmail(String email);

    Result<Record> findUserById(Long userId);

    Result<Record> findAllUsers(Pageable pageable);

    void updateUser(UsersRecord usersRecord);

    int deleteUserById(Long userId);

    void updateUser(List<UserMenuRecord> userMenuRecords);
}
