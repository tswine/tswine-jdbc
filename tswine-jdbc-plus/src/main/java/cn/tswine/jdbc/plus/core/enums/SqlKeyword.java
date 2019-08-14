package cn.tswine.jdbc.plus.core.enums;

/**
 * sql保留关键字枚举
 *
 * @Author: silly
 * @Date: 2019/8/13 22:39
 * @Version 1.0
 * @Desc
 */
public enum SqlKeyword {
    AND("AND"),
    OR("OR"),
    IN("IN"),
    NOT("NOT"),
    LIKE("LIKE"),
    EQ("="),
    NE("<>"),
    GT(">"),
    GE(">="),
    LT("<"),
    LE("<="),
    IS_NULL("IS NULL"),
    IS_NOT_NULL("IS NOT NULL"),
    GROUP_BY("GROUP BY"),
    HAVING("HAVING"),
    ORDER_BY("ORDER BY"),
    EXISTS("EXISTS"),
    BETWEEN("BETWEEN"),
    ASC("ASC"),
    DESC("DESC");

    private final String keyword;

    SqlKeyword(final String keyword) {
        this.keyword = keyword;
    }

}
