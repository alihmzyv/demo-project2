package com.example.demoproject2.repo;

import org.jooq.Field;

import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static org.jooq.impl.DSL.count;

public class Fields {
    public static final Field<Integer> NUM_OF_CASHİERS = count();
    public static final Field<Integer> NUM_OF_ACT_CASHİERS = count().filterWhere(CASHIER.STATUS.eq((short) 1));
    public static final Field<Integer> NUM_OF_İNACT_CASHİERS = count().filterWhere(CASHIER.STATUS.eq((short) 2));
    public static final Field<Integer> NUM_OF_DEL_CASHİERS = count().filterWhere(CASHIER.STATUS.eq((short) 3));
}
