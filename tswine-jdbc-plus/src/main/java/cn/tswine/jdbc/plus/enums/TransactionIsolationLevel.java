package cn.tswine.jdbc.plus.enums;

import java.sql.Connection;

/**
 * 数据库事务隔离级别
 *
 * @Author: silly
 * @Date: 2019/8/16 10:34
 * @Version 1.0
 * @Desc
 */
public enum TransactionIsolationLevel {
    /**
     * 不支持事务
     */
    NONE(Connection.TRANSACTION_NONE),
    /**
     *
     */
    READ_COMMITTED(Connection.TRANSACTION_READ_COMMITTED),
    /**
     * 脏读
     */
    READ_UNCOMMITTED(Connection.TRANSACTION_READ_UNCOMMITTED),
    /**
     *
     */
    REPEATABLE_READ(Connection.TRANSACTION_REPEATABLE_READ),
    /**
     *
     */
    SERIALIZABLE(Connection.TRANSACTION_SERIALIZABLE);

    private final int level;

    TransactionIsolationLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
