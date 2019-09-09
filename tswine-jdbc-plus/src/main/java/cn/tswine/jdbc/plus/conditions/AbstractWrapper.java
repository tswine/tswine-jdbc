package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.plus.conditions.connector.WhereConnector;
import cn.tswine.jdbc.plus.conditions.connector.WhereType;
import cn.tswine.jdbc.plus.conditions.interfaces.Compare;
import cn.tswine.jdbc.plus.conditions.interfaces.Func;
import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;

import java.util.ArrayList;
import java.util.List;

import static cn.tswine.jdbc.common.enums.SQLSentenceType.ORDER_BY;


/**
 * @Author: silly
 * @Date: 2019/8/16 16:10
 * @Version 1.0
 * @Desc
 */
public class AbstractWrapper<T, Children extends AbstractWrapper<T, Children>> extends Wrapper<T>
        implements Compare<Children>, Func<Children> {
    protected final Children _this = (Children) this;
    /**
     * 数据库表映射实体类
     */
    protected T entity;
    protected MergeSegments expression;

    private final List<Object> params = new ArrayList<>();
    /**
     * 实体类型
     */
    protected Class<T> entityClass;

    @Override
    public T getEntity() {
        return entity;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    public Children setEntity(T entity) {
        this.entity = entity;
        this.initEntityClass();
        return _this;
    }

    protected void initEntityClass() {
        if (this.entityClass == null && this.entity != null) {
            this.entityClass = (Class<T>) entity.getClass();
        }
    }

    public AbstractWrapper() {
        expression = new MergeSegments();
    }

    /**
     * =
     *
     * @param column 字段
     * @param param  参数
     * @return
     */
    @Override
    public Children eq(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.EQ, new Object[]{param});
    }

    /**
     * !=
     *
     * @param column 字段
     * @param param  参数
     * @return
     */
    @Override
    public Children nq(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.NQ, new Object[]{param});
    }

    /**
     * <
     *
     * @param column
     * @param param
     * @return
     */
    @Override
    public Children lt(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LT, new Object[]{param});
    }

    /**
     * <=
     *
     * @param column
     * @param param
     * @return
     */
    @Override
    public Children le(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LE, new Object[]{param});
    }

    /**
     * >
     *
     * @param column
     * @param param
     * @return
     */
    @Override
    public Children gt(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.GT, new Object[]{param});
    }

    /**
     * >=
     *
     * @param column
     * @param param
     * @return
     */
    @Override
    public Children ge(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.GE, new Object[]{param});
    }

    /**
     * like '%value%'
     *
     * @param column user_name like "%value%"
     * @param param
     * @return
     */
    @Override
    public Children like(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE, new Object[]{param});
    }

    @Override
    public Children likeLeft(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_LEFT, new Object[]{param});
    }

    @Override
    public Children likeRight(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_RIGHT, new Object[]{param});
    }

    @Override
    public Children notLike(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_NOT, new Object[]{param});
    }

    @Override
    public Children notLikeLeft(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_LEFT_NOT, new Object[]{param});
    }

    @Override
    public Children notLikeRight(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_RIGHT_NOT, new Object[]{param});
    }

    @Override
    public Children isNull(String column) {
        return addWhereConnector(new String[]{column}, WhereType.IS_NULL, null);
    }

    @Override
    public Children isNotNull(String column) {
        return addWhereConnector(new String[]{column}, WhereType.IS_NOT_NULL, null);
    }

    /**
     * user_name in (?,?,?,?)
     *
     * @param column
     * @param params
     * @return
     */
    @Override
    public Children in(String column, Object[] params) {
        return addWhereConnector(new String[]{column}, WhereType.IN, params);
    }


    /**
     * user_name not in (?,?,?,?)
     *
     * @param column
     * @param params
     * @return
     */
    @Override
    public Children notIn(String column, Object[] params) {
        return addWhereConnector(new String[]{column}, WhereType.IN_NOT, params);
    }


    /**
     * order by user_name password desc
     *
     * @param isAsc   是否ASC排序
     * @param columns 排序字段
     * @return
     */
    @Override
    public Children orderBy(boolean isAsc, String... columns) {
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

        return _this;
    }

    @Override
    public Children orderBy(OrderBy[] columns) {
        if (columns != null && columns.length > 0) {
            for (OrderBy column : columns) {
                expression.addOrderBy(column);
            }
        }
        return _this;
    }


    /**
     * 添加条件连接器
     *
     * @param columns 列名
     * @param type    类型
     * @param params  参数
     * @return
     */
    private Children addWhereConnector(String[] columns, WhereType type, Object[] params) {
        WhereConnector connector = new WhereConnector(type, columns, params);
        expression.add(connector);
        return _this;
    }

    @Override
    public String getSqlSegment() {
        StringBuilder sb = new StringBuilder();
        //TODO 拼接where条件

        //拼接orderBY
        ArrayList<OrderBy> orderByCondition = expression.getOrderBy();
        if (orderByCondition != null && orderByCondition.size() > 0) {
            int size = orderByCondition.size() - 1;
            sb.append(StringPool.SPACE);
            sb.append(ORDER_BY.getValue());
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
        return sb.toString();
    }

    public Object[] getParams() {
        return params.toArray();
    }
}
