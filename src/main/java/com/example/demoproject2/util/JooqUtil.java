package com.example.demoproject2.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.Record;
import org.jooq.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Component
public class JooqUtil {
    DSLContext dslContext;

    public <R extends Record> Result<R>  emptyResult(Table<R> table) {
        return dslContext.newResult(table);
    }
    public <R extends Record, Z> Optional<Field<Z>> findField(Table<R> table, String fieldName, Class<Z> fieldType) {
        return Optional.ofNullable(table.field(fieldName.toLowerCase(), fieldType));
    }
}
