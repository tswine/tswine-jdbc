package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.CollectionUtils;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.conditions.interfaces.Compare;
import cn.tswine.jdbc.plus.conditions.interfaces.Func;
import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;

import java.util.*;

import static cn.tswine.jdbc.common.enums.SQLSentenceType.*;


/**
 * @Author: silly
 * @Date: 2019/8/16 16:10
 * @Version 1.0
 * @Desc
 */
public class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T>
        implements Compare<Children, R>, Func<Children, R> {
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
     * @param value  值
     * @return
     */
    @Override
    public Children eq(R column, Object value) {
        return addCondition(column, EQ, new Object[]{value});
    }

    /**
     * !=
     *
     * @param column 字段
     * @param value  值
     * @return
     */
    @Override
    public Children nq(R column, Object value) {
        return addCondition(column, NQ, new Object[]{value});
    }

    /**
     * <
     *
     * @param column
     * @param value
     * @return
     */
    @Override
    public Children lt(R column, Object value) {
        return addCondition(column, LT, new Object[]{value});
    }

    /**
     * <=
     *
     * @param column
     * @param value
     * @return
     */
    @Override
    public Children le(R column, Object value) {
        return addCondition(column, LE, new Object[]{value});
    }

    /**
     * >
     *
     * @param column
     * @param value
     * @return
     */
    @Override
    public Children gt(R column, Object value) {
        return addCondition(column, GT, new Object[]{value});
    }

    /**
     * >=
     *
     * @param column
     * @param value
     * @return
     */
    @Override
    public Children ge(R column, Object value) {
        return addCondition(column, GE, new Object[]{value});
    }

    /**
     * like '%value%'
     *
     * @param column user_name like "%value%"
     * @param value
     * @return
     */
    @Override
    public Children like(R column, Object value) {
        expression.add(column + " like '%" + value + "%'", null);
        return _this;
    }

    @Override
    public Children likeLeft(R column, Object value) {
        expression.add(column + " like '%" + value + "'", null);
        return _this;
    }

    @Override
    public Children likeRight(R column, Object value) {
        expression.add(column + " like '" + value + "%'", null);
        return _this;
    }

    @Override
    public Children notLike(R column, Object value) {
        expression.add(column + " not like '%" + value + "%'", null);
        return _this;
    }

    @Override
    public Children notLikeLeft(R column, Object value) {
        expression.add(column + " not like '%" + value + "'", null);
        return _this;
    }

    @Override
    public Children notLikeRight(R column, Object value) {
        expression.add(column + " not like '" + value + "%'", null);
        return _this;
    }

    @Override
    public Children isNull(R column) {
        expression.add(String.format("%s %s", column, IS_NULL), null);
        return _this;
    }

    @Override
    public Children isNotNull(R column) {
        expression.add(String.format("%s %s", column, IS_NOT_NULL), null);
        return _this;
    }

    /**
     * user_name in (?,?,?,?)
     *
     * @param column
     * @param coll
     * @return
     */
    @Override
    public Children in(R column, Collection<?> coll) {
        return addIn(false, column, coll);
    }

    /**
     * user_name not in (?,?,?,?)
     *
     * @param column
     * @param coll
     * @return
     */
    @Override
    public Children notIn(R column, Collection<?> coll) {
        return addIn(true, column, coll);
    }

    /**
     * order by user_name password desc
     *
     * @param isAsc   是否ASC排序
     * @param columns 排序字段
     * @return
     */
    @Override
    public Children orderBy(boolean isAsc, R... columns) {
        if (columns != null || columns.length > 0) {
            OrderBy.Mode mode = OrderBy.Mode.DESC;
            if (isAsc) {
                mode = OrderBy.Mode.ASC;
            }
            for (R r : columns) {
                OrderBy<R> orderBy = new OrderBy<>(r, mode);
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
     * 添加in条件
     *
     * @param isNot
     * @param column
     * @param coll
     * @return
     */
    private Children addIn(boolean isNot, R column, Collection<?> coll) {
        String exprSql;
        if (isNot) {
            exprSql = "%s not in ( %s )";
        } else {
            exprSql = "%s  in ( %s )";
        }
        String questionMark = SqlUtils.getQuestionMark(coll.size());
        expression.add(String.format(exprSql, column, questionMark), coll.toArray());
        return _this;
    }

    /**
     * 添加条件
     *
     * @param column
     * @param keyword
     * @param value
     * @return
     */
    private Children addCondition(R column, SQLSentenceType keyword, Object[] value) {
        String exprSql = "%s %s ?";
        expression.add(String.format(exprSql, column, keyword.getValue()), value);
        return _this;
    }

    @Override
    public String getSqlSegment() {
        StringBuilder sb = new StringBuilder();
        //拼接where条件
        Map<String, Object[]> whereCondition = expression.getNormal();
        if (whereCondition != null && whereCondition.size() >= 0) {
            int size = whereCondition.size() - 1;
            int i = 0;
            Iterator<Map.Entry<String, Object[]>> iterator = whereCondition.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object[]> entry = iterator.next();
                sb.append(entry.getKey());
                if (ArrayUtils.isNotEmpty(entry.getValue())) {
                    CollectionUtils.add(params, entry.getValue());
                }
                if (i == size) {
                    break;
                }
                i++;
                sb.append(StringPool.SPACE);
                sb.append(AND.getValue());
                sb.append(StringPool.SPACE);
            }
        }
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
