package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;

/**
 * 条件构造抽象类
 *
 * @Author: silly
 * @Date: 2019/8/13 22:33
 * @Version 1.0
 * @Desc
 */
public abstract class Wrapper<T> implements ISqlSegment {

    /**
     * 实体对象（子类实现）
     *
     * @return 泛型 T
     */
    public abstract T getEntity();

    /**
     * 获取MergeSegments
     *
     * @return
     */
    public abstract MergeSegments getExpression();
}
