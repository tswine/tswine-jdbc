package cn.tswine.jdbc.plus.conditions.connector;

import cn.tswine.jdbc.plus.conditions.ISqlSegment;

/**
 * where条件类型
 *
 * @Author: silly
 * @Date: 2019/9/9 21:21
 * @Version 1.0
 * @Desc
 */
public enum WhereType implements ISqlSegment {
    /**
     * 等于
     */
    EQ(" {0} = ? "),
    /**
     * 不等于
     */
    NQ(" {0} != ? "),
    /**
     * 大于
     */
    GT(" {0} > ? "),
    /**
     * 大于等于
     */
    GE(" {0} >= ? "),
    /**
     * 小于
     */
    LT(" {0} < ? "),
    /**
     * 小于等于
     */
    LE(" {0} <= ? "),
    /**
     * like
     */
    LIKE(" LIKE '%{0}%' "),
    /**
     * 左like
     */
    LIKE_LEFT(" LIKE '%{0}' "),
    /**
     * 右like
     */
    LIKE_RIGHT(" LIKE '{0}%' "),
    /**
     * not like
     */
    LIKE_NOT(" NOT LIKE '%{0}%' "),
    /**
     * not 左like
     */
    LIKE_LEFT_NOT(" NOT LIKE '%{0}' "),
    /**
     * not 右like
     */
    LIKE_RIGHT_NOT(" NOT LIKE '{0}%' "),
    /**
     * 为空
     */
    IS_NULL(" {0} IS NULL "),
    /**
     * 不能为空
     */
    IS_NOT_NULL(" {0} IS NOT NULL "),
    /**
     * IN
     */
    IN(" {0} IN {0} "),
    /**
     * NOT IN
     */
    IN_NOT(" {0} NOT IN {0} "),

    /**
     * between
     */
    BETWEEN(" {0} BETWEEN ? AND ? ");

    String sql;


    WhereType(String sql) {
        this.sql = sql;
    }


    @Override
    public String getSqlSegment() {
        return sql;
    }}
