package com.example.demoproject2.consts;

import org.jooq.Field;

import static com.example.demoproject2.generated.jooq.Tables.CASHIER;
import static org.jooq.impl.DSL.count;

public class Fields {
    public static final Field<Integer> NUM_OF_CASHIERS = count().as("num_of_cashiers");
    public static final Field<Integer> NUM_OF_ACT_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 1)).as("num_of_act_cashiers");
    public static final Field<Integer> NUM_OF_INACT_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 2)).as("num_of_inact_cashiers");
    public static final Field<Integer> NUM_OF_DEL_CASHIERS = count().filterWhere(CASHIER.STATUS.eq((short) 3)).as("num_of_del_cashiers");
}
