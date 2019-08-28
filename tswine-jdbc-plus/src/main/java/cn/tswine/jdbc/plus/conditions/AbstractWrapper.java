package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.plus.conditions.interfaces.Compare;
import cn.tswine.jdbc.plus.conditions.interfaces.Func;
import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;

import java.util.Collection;

/**
 * @Author: silly
 * @Date: 2019/8/16 16:10
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T>
        implements Compare<Children, R>, Func<Children, R> {
    protected final Children typedThis = (Children) this;
    /**
     * 数据库表映射实体类
     */
    protected T entity;
    protected MergeSegments expression;

    /**
     * 实体类型
     */
    protected Class<T> entityClass;

    @Override
    public T getEntity() {
        return entity;
    }

    public Children setEntity(T entity) {
        this.entity = entity;
        this.initEntityClass();
        return typedThis;
    }

    protected void initEntityClass() {
        if (this.entityClass == null && this.entity != null) {
            this.entityClass = (Class<T>) entity.getClass();
        }
    }

    public AbstractWrapper() {
        expression = new MergeSegments();
    }

    @Override
    public Children eq(R column, Object value) {
        return addCondition(column, , value);
    }

    @Override
    public Children nq(R column, Object value) {
        return null;
    }

    @Override
    public Children lt(R column, Object value) {
        return null;
    }

    @Override
    public Children le(R column, Object value) {
        return null;
    }

    @Override
    public Children gt(R column, Object value) {
        return null;
    }

    @Override
    public Children ge(R column, Object value) {
        return null;
    }

    @Override
    public Children like(R column, Object value) {
        return null;
    }

    @Override
    public Children likeLeft(R column, Object value) {
        return null;
    }

    @Override
    public Children likeRight(R column, Object value) {
        return null;
    }

    @Override
    public Children notLike(R column, Object value) {
        return null;
    }

    @Override
    public Children notLikeLeft(R column, Object value) {
        return null;
    }

    @Override
    public Children notLikeRight(R column, Object value) {
        return null;
    }

    @Override
    public Children isNull(R column) {
        return null;
    }

    @Override
    public Children isNotNull(R column) {
        return null;
    }

    @Override
    public Children in(R column, Collection<?> coll) {
        return null;
    }

    @Override
    public Children notIn(R column, Collection<?> coll) {
        return null;
    }
}
