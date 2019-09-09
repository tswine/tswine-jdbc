package cn.tswine.jdbc.plus.conditions.query;


import cn.tswine.jdbc.plus.conditions.AbstractWrapper;

/**
 * 查询条件封装
 *
 * @param <T> 实体对象
 */
public class QueryWrapper<T> extends AbstractWrapper<T, QueryWrapper<T>> implements Query<QueryWrapper<T>, T, String> {

    String[] selectColumn;

    boolean distinct = false;

    @Override
    public QueryWrapper<T> select(String... columns) {
        this.selectColumn = columns;
        return this;
    }

    @Override
    public QueryWrapper<T> distinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    @Override
    public String[] getSelectColumns() {
        return selectColumn;
    }

}
