package cn.tswine.jdbc.plus.conditions.connector;

import cn.tswine.jdbc.common.toolkit.StringPool;
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
    EQ(" {0} = ? ", StringPool.BASE),
    /**
     * 不等于
     */
    NQ(" {0} != ? ", StringPool.BASE),
    /**
     * 大于
     */
    GT(" {0} > ? ", StringPool.BASE),
    /**
     * 大于等于
     */
    GE(" {0} >= ? ", StringPool.BASE),
    /**
     * 小于
     */
    LT(" {0} < ? ", StringPool.BASE),
    /**
     * 小于等于
     */
    LE(" {0} <= ? ", StringPool.BASE),
    /**
     * between
     */
    BETWEEN(" {0} BETWEEN ? AND ? ", StringPool.BASE),
    /**
     * not between
     */
    BETWEEN_NOT(" {0} NOT BETWEEN ? AND ? ", StringPool.BASE),

    /**
     * like
     */
    LIKE("{0} LIKE '%{1}%' ", StringPool.LIKE),

    /**
     * 左like
     */
    LIKE_LEFT("{0} LIKE '%{1}' ", StringPool.LIKE),

    /**
     * 右like
     */
    LIKE_RIGHT("{0} LIKE '{1}%' ", StringPool.LIKE),

    /**
     * not like
     */
    LIKE_NOT("{0} NOT LIKE '%{1}%' ", StringPool.LIKE),

    /**
     * not 左like
     */
    LIKE_LEFT_NOT("{0} NOT LIKE '%{1}' ", StringPool.LIKE),

    /**
     * not 右like
     */
    LIKE_RIGHT_NOT("{0} NOT LIKE '{1}%' ", StringPool.LIKE),

    /**
     * 为空
     */
    IS_NULL(" {0} IS NULL ", StringPool.NULL),

    /**
     * 不能为空
     */
    IS_NOT_NULL(" {0} IS NOT NULL ", StringPool.NULL),

    /**
     * IN
     */
    IN(" {0} IN ( {1} ) ", StringPool.IN),

    /**
     * NOT IN
     */
    IN_NOT(" {0} NOT IN ( {1} ) ", StringPool.IN);


    String sql;
    String type;


    WhereType(String sql, String type) {
        this.sql = sql;
        this.type = type;
    }


    @Override
    public String getSqlSegment() {
        return sql;
    }

    public String getType() {
        return type;
    }
}