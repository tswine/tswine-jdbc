package cn.tswine.jdbc.common.enums;

import cn.tswine.jdbc.common.toolkit.StringPool;

/**
 * sql保留关键字枚举
 *
 * @Author: silly
 * @Date: 2019/8/13 22:39
 * @Version 1.0
 * @Desc
 */
public enum SQLSentenceType {
    SELECT("SELECT"),
    INSERT("INSERT"),
    DELETE("DELETE"),
    UPDATE("UPDATE"),

    EQ(StringPool.EQ),
    NQ(StringPool.NQ),
    GT(StringPool.GT),
    GE(StringPool.GE),
    LT(StringPool.LT),
    LE(StringPool.LE),

    WHERE("WHERE"),
    AND("AND"),
    OR("OR"),
    IN("IN"),
    NOT_IN("NOT IN"),
    NOT("NOT"),
    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    EXISTS("EXISTS"),
    BETWEEN("BETWEEN"),
    ASC("ASC"),
    DESC("DESC");


    /**
     * 值
     */
    private final String value;

    SQLSentenceType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
