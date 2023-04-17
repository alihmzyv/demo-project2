package com.example.demoproject2.util;

import lombok.extern.slf4j.Slf4j;
import org.jooq.SortField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableRecord;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
public class PageUtil {

    private static <R extends TableRecord<R>> TableField<R, ?> getTableField(Table<R> table, String sortFieldName) {
        TableField<R, ?> sortField;
        try {
            log.info("Fields of {}", table.getName());
            Field[] fields = table.getClass().getFields();
            Arrays.stream(fields)
                    .forEach(field -> log.info("Field: {}", field));
            java.lang.reflect.Field field = table.getClass().getField(sortFieldName.toUpperCase());
            sortField = (TableField<R, ?>) field.get(table);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            String errorMessage = String.format("Could not find table field: %s", sortFieldName);
            throw new InvalidDataAccessApiUsageException(errorMessage, e);
        }
        return sortField;
    }

    private static <R extends TableRecord<R>> SortField<?> convertTableFieldToSortField(TableField<R, ?> tableField, Sort.Direction sortDirection) {
        if (sortDirection.isAscending()) return tableField.asc();
        else return tableField.desc();
    }

    public static  <R extends TableRecord<R>> Collection<SortField<?>> getOrderByFields(Table<R> table, Sort sortSpecification) {
        Collection<SortField<?>> querySortFields = new ArrayList<>();

        if (sortSpecification == null) {
            return querySortFields;
        }

        for (Sort.Order specifiedField : sortSpecification) {
            String sortFieldName = specifiedField.getProperty();
            Sort.Direction sortDirection = specifiedField.getDirection();
            TableField<R, ?> tableField = getTableField(table, sortFieldName);
            SortField<?> querySortField = convertTableFieldToSortField(tableField, sortDirection);
            querySortFields.add(querySortField);
        }

        return querySortFields;
    }
}
