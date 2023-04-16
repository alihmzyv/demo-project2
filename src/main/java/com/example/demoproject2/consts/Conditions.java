package com.example.demoproject2.consts;

import org.jooq.Condition;

import static com.example.demoproject2.generated.jooq.Tables.AGENT;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;

public class Conditions {
    public static Condition AGENT_IS_DELETED = AGENT.STATUS.eq((short) 3);
    public static Condition CASHIER_IS_DELETED = CASHIER.STATUS.eq((short) 3);

}
