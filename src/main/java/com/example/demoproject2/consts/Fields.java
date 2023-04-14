package com.example.demoproject2.consts;

import org.jooq.Field;

import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static org.jooq.impl.DSL.count;

public class Fields {
    public static final Field<Integer> NUM_OF_CASHIERS = count();
    public static final Field<Integer> NUM_OF_ACT_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 1));
    public static final Field<Integer> NUM_OF_INACT_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 2));
    public static final Field<Integer> NUM_OF_DEL_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 3));
}
