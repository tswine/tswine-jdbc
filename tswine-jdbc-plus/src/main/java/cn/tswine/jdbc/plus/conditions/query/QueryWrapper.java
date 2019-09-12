package cn.tswine.jdbc.plus.conditions.query;


import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.plus.conditions.AbstractWrapper;
import cn.tswine.jdbc.plus.conditions.OrderBy;

import java.util.ArrayList;

/**
 * 查询条件封装
 *
 * @param <T> 实体对象
 */
public class QueryWrapper<T> extends AbstractWrapper<T, QueryWrapper<T>> implements Query<QueryWrapper<T>> {

    /**
     * 需要查询的列
     */
    String[] columns = null;

    @Override
    public QueryWrapper<T> select(String... columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public QueryWrapper<T> distinct(boolean distinct) {
        //TODO 实现逻辑
        return this;
    }

    /**
     * order by user_name password desc
     *
     * @param isAsc   是否ASC排序
     * @param columns 排序字段
     * @return
     */
    @Override
    public QueryWrapper<T> orderBy(boolean isAsc, String... columns) {
        if (columns != null || columns.length > 0) {
            OrderBy.Mode mode = OrderBy.Mode.DESC;
            if (isAsc) {
                mode = OrderBy.Mode.ASC;
            }
            for (String r : columns) {
                OrderBy<String> orderBy = new OrderBy<>(r, mode);
                expression.addOrderBy(orderBy);
            }
        }

        return this;
    }

    @Override
    public QueryWrapper<T> orderBy(OrderBy[] columns) {
        if (columns != null && columns.length > 0) {
            for (OrderBy column : columns) {
                expression.addOrderBy(column);
            }
        }
        return this;
    }

    /**
     * 获取查询的列数据
     *
     * @return
     */
    public String[] getColumns() {
        if (ArrayUtils.isEmpty(columns)) {
            //从实体对象获取
            //TODO 获取到的对象为空
            return super.entitySchema.getColumns(null);
        } else {
            return columns;
        }

    }

    @Override
    public String getSqlSegment() {
        String orderBySql = orderBySql();
        if (StringUtils.isNotEmpty(orderBySql)) {
            return String.format("%s %s", super.getSqlSegment(), orderBySql);
        } else {
            return orderBySql;
        }
    }


    private String orderBySql() {
        StringBuilder sb = new StringBuilder();
        //拼接orderBY
        ArrayList<OrderBy> orderByCondition = expression.getOrderBy();
        if (orderByCondition != null && orderByCondition.size() > 0) {
            int size = orderByCondition.size() - 1;
            sb.append(StringPool.SPACE);
            sb.append(SQLSentenceType.ORDER_BY.getValue());
            sb.append(StringPool.SPACE);
            for (int i = 0; i < orderByCondition.size(); i++) {
                OrderBy orderBy = orderByCondition.get(i);
                sb.append(orderBy.getSqlSegment());
                if (i == size) {
                    break;
                }
                sb.append(StringPool.SPACE);
                sb.append(StringPool.COMMA);
                sb.append(StringPool.SPACE);
            }
        }
        return sb.toString().trim();
    }
}
