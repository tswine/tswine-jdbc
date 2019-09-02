package cn.tswine.jdbc.plus.conditions.query;


import cn.tswine.jdbc.plus.conditions.AbstractWrapper;

/**
 * 查询条件封装
 *
 * @param <T> 实体对象
 */
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>> implements Query<QueryWrapper<T>, T, String> {

    String[] selectColumn;

    @Override
    public QueryWrapper<T> select(String... columns) {
        this.selectColumn = columns;
        return this;
    }

    @Override
    public String[] getSelectColumns() {
        return selectColumn;
    }

}
