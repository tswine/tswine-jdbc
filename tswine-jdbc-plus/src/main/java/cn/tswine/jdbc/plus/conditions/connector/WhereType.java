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
    EQ(" {} = ? "),
    /**
     * 不等于
     */
    NQ(" {} != ? "),
    /**
     * 大于
     */
    GT(" {} > ? "),
    /**
     * 大于等于
     */
    GE(" {} >= ? "),
    /**
     * 小于
     */
    LT(" {} < ? "),
    /**
     * 小于等于
     */
    LE(" {} <= ? "),
    /**
     * like
     */
    LIKE(" LIKE '%{}%' "),
    /**
     * 左like
     */
    LIKE_LEFT(" LIKE '%{}' "),
    /**
     * 右like
     */
    LIKE_RIGHT(" LIKE '{}%' "),
    /**
     * not like
     */
    LIKE_NOT(" NOT LIKE '%{}%' "),
    /**
     * not 左like
     */
    LIKE_LEFT_NOT(" NOT LIKE '%{}' "),
    /**
     * not 右like
     */
    LIKE_RIGHT_NOT(" NOT LIKE '{}%' "),
    /**
     * 为空
     */
    IS_NULL(" {} IS NULL "),
    /**
     * 不能为空
     */
    IS_NOT_NULL(" {} IS NOT NULL "),
    /**
     * IN
     */
    IN(" {} IN {} "),
    /**
     * NOT IN
     */
    IN_NOT(" {} NOT IN {} "),

    /**
     * between
     */
    BETWEEN(" {} BETWEEN ? AND ? ");

    String sql;


    WhereType(String sql) {
        this.sql = sql;
    }


    @Override
    public String getSqlSegment() {
        return sql;
    }}
