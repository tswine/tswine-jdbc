package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;
import cn.tswine.jdbc.common.enums.SQLSentenceType;

/**
 * @Author: silly
 * @Date: 2019/8/16 16:10
 * @Version 1.0
 * @Desc
 */
public class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T>
        implements Compare<Children, R> , StringPool {

    MergeSegments segments;

    public AbstractWrapper() {
        segments = new MergeSegments();
    }


    @Override
    public T getEntity() {
        return null;
    }

    @Override
    public Children eq(R column, Object val) {
        return null;
//        return addCondition(column, SQLSentenceType.EQ, val);
    }

    @Override
    public String getSqlSegment() {
        return null;
    }

    protected Children addCondition(R column, SQLSentenceType sqlKeyword, Object val) {
//        segments.add(new Object[]{val}, sqlKeyword, () -> String.format());
//        return doIt(condition, () -> columnToString(column), sqlKeyword, () -> formatSql("{0}", val));
        //TODO
        return null;
    }


    protected final String formatSql(String sqlStr, Object... params) {
        if (ArrayUtils.isNotEmpty(params)) {
            sqlStr = String.format(sqlStr, params);
        }
        return sqlStr;
    }
}
