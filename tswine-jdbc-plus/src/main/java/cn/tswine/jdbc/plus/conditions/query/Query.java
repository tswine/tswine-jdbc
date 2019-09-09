package cn.tswine.jdbc.plus.conditions.query;

import java.io.Serializable;

/**
 * 查询器
 *
 * @Author: silly
 * @Date: 2019/8/16 15:41
 * @Version 1.0
 * @Desc
 */
public interface Query<Children, T, R> extends Serializable {
    /**
     * 设置查询的字段
     *
     * @param columns 查询字段数组
     * @return
     */
    Children select(R... columns);

    /**
     * 是否去除重复值
     *
     * @param distinct
     * @return
     */
    Children distinct(boolean distinct);

    /**
     * 获取查询列
     *
     * @return
     */
    R[] getSelectColumns();
}
