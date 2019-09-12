package cn.tswine.jdbc.plus.conditions.connector;

import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.plus.conditions.ISqlSegment;

/**
 * 逻辑类型
 *
 * @Author: silly
 * @Date: 2019/9/9 22:07
 * @Version 1.0
 * @Desc
 */
public enum LogicType implements ISqlSegment {
    /**
     * 左括号
     */
    BRACKET_LEFT(StringPool.LEFT_BRACKET),
    /**
     * 右括号
     */
    BRACKET_RIGHT(StringPool.RIGHT_BRACKET),
    /**
     * or
     */
    OR(StringPool.OR),
    /**
     * and
     */
    AND(StringPool.AND);

    String sql;

    LogicType(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSqlSegment() {
        return sql;
    }
}
