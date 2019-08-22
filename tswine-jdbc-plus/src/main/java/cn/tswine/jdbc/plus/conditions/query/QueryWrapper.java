package cn.tswine.jdbc.plus.conditions.query;


import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.injector.AbstractMethod;

/**
 * 查询条件封装
 *
 * @param <T> 实体对象
 */
public class QueryWrapper<T> extends AbstractMethod {
    public QueryWrapper(IDBLabel dbLabel) {
        super(dbLabel);
    }
}
