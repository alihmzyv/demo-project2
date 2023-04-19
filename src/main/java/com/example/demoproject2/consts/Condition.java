package com.example.demoproject2.consts;

import static com.example.demoproject2.consts.Status.*;
import static com.example.demoproject2.generated.jooq.Tables.AGENT;
import static com.example.demoproject2.generated.jooq.Tables.CASHIER;

public class Condition {
    public static org.jooq.Condition AGENT_IS_DELETED = AGENT.STATUS.eq(DELETED_STATUS_VALUE);
    public static org.jooq.Condition AGENT_IS_NOT_DELETED = AGENT_IS_DELETED.isFalse();
    public static org.jooq.Condition CASHIER_IS_DELETED = CASHIER.STATUS.eq(DELETED_STATUS_VALUE);
    public static org.jooq.Condition AGENT_ACTIVE = AGENT.STATUS.eq(ACTIVE_STATUS_VALUE);
    public static org.jooq.Condition AGENT_INACTIVE = AGENT.STATUS.eq(INACTIVE_STATUS_VALUE);
    public static org.jooq.Condition CASHIER_ACTIVE = CASHIER.STATUS.eq(ACTIVE_STATUS_VALUE);
    public static org.jooq.Condition CASHIER_INACTIVE = CASHIER.STATUS.eq(INACTIVE_STATUS_VALUE);
    public static org.jooq.Condition AGENT_NOT_ACTIVE = AGENT_ACTIVE.isFalse();
    public static org.jooq.Condition AGENT_NOT_INACTIVE = AGENT_INACTIVE.isFalse();
    public static org.jooq.Condition CASHIER_NOT_ACTIVE = CASHIER_ACTIVE.isFalse();
    public static org.jooq.Condition CASHIER_NOT_INACTIVE = CASHIER_NOT_ACTIVE.isFalse();
    public static org.jooq.Condition CASHIER_IS_NOT_DELETED = CASHIER_IS_DELETED.isFalse();
}
