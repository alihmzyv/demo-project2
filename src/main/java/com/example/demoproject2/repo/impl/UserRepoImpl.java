package com.example.demoproject2.repo.impl;

import com.example.demoproject2.generated.jooq.tables.records.UserMenuRecord;
import com.example.demoproject2.generated.jooq.tables.records.UsersRecord;
import com.example.demoproject2.repo.UserRepo;
import com.example.demoproject2.util.JooqUtil;
import com.example.demoproject2.util.PageUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.jooq.Record;
import org.jooq.*;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.example.demoproject2.generated.jooq.Keys.USER_ID_MENU_ID_UNIQUE;
import static com.example.demoproject2.generated.jooq.Tables.USERS;
import static com.example.demoproject2.generated.jooq.Tables.USER_MENU;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Repository
public class UserRepoImpl implements UserRepo {
    DSLContext dslContext;

    @Override
    public long insertUser(UsersRecord usersRecord, List<UserMenuRecord> userMenuRecords) {
        final Long[] insertedUserId = new Long[1];
        dslContext.transaction(ctx -> {
            UsersRecord usersRecordInserted = ctx.dsl().insertInto(USERS)
                    .set(usersRecord)
                    .returning()
                    .fetchOne();
            insertedUserId[0] = usersRecordInserted.getId();
            Long userId = usersRecordInserted.getId();
            userMenuRecords.forEach(userMenuRecord -> userMenuRecord.setUserId(userId));
            ctx.dsl().batchInsert(userMenuRecords).execute();
        });
        return insertedUserId[0];
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return dslContext.fetchCount(USERS, USERS.USERNAME.eq(username)) != 0;
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return dslContext.fetchCount(USERS, USERS.EMAIL.eq(email)) != 0;
    }

    @Override
    public Result<Record> findUserById(Long userId) {
        return dslContext.select()
                .from(USERS)
                .leftJoin(USER_MENU)
                .on(USER_MENU.USER_ID.eq(USERS.ID))
                .where(USERS.ID.eq(userId))
                .fetch();
    }

    @Override
    public Result<Record> findAllUsers(Pageable pageable) {
        Collection<SortField<?>> orderByFields = PageUtil.getOrderByFields(USERS, pageable.getSort());
        return dslContext.select()
                .from(USERS)
                .leftJoin(USER_MENU)
                .on(USER_MENU.USER_ID.eq(USERS.ID))
                .orderBy(orderByFields)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public void updateUser(UsersRecord usersRecord) {
        Map<String, Object> notNullFields = JooqUtil.findNotNullFields(usersRecord);
        dslContext.update(USERS)
                .set(notNullFields)
                .where(USERS.ID.eq(usersRecord.getId()))
                .execute();
    }

    @Override
    public int deleteUserById(Long userId) {
        return dslContext.deleteFrom(USERS)
                .where(USERS.ID.eq(userId))
                .execute();
    }

    @Override
    public void updateUser(List<UserMenuRecord> userMenuRecords) {
        List<InsertOnConflictConditionStep<UserMenuRecord>> queries = userMenuRecords.stream()
                .map(userMenuRecord -> dslContext.insertInto(USER_MENU)
                        .set(userMenuRecord)
                        .onConflictOnConstraint(USER_ID_MENU_ID_UNIQUE)
                        .doUpdate()
                        .set(USER_MENU.ROLE_ID, userMenuRecord.getRoleId())
                        .where(USER_MENU.USER_ID.eq(userMenuRecord.getUserId())
                                .and(USER_MENU.MENU_ID.eq(userMenuRecord.getMenuId()))))
                .toList();
        dslContext.batch(queries)
                .execute();
    }
}
