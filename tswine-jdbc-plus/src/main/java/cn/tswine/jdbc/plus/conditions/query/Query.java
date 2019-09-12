package cn.tswine.jdbc.plus.conditions.query;

import cn.tswine.jdbc.plus.conditions.OrderBy;

import java.io.Serializable;

/**
 * 查询器
 *
 * @Author: silly
 * @Date: 2019/8/16 15:41
 * @Version 1.0
 * @Desc
 */
public interface Query<Children> extends Serializable {


    /**
     * 设置查询的字段
     *
     * @param columns 查询字段数组
     * @return
     */
    Children select(String... columns);

    /**
     * 是否去除重复值
     *
     * @param distinct
     * @return
     */
    Children distinct(boolean distinct);

    /**
     * 排序：Order By 字段
     *
     * @param isAsc   是否ASC排序
     * @param columns 排序字段
     * @return
     */
    Children orderBy(boolean isAsc, String... columns);

    /**
     * 排序
     *
     * @param columns
     * @return
     */
    Children orderBy(OrderBy[] columns);

    /**
     * 排序: Order By 字段... ASC
     *
     * @param columns
     * @return
     */
    default Children orderByAsc(String... columns) {
        return orderBy(true, columns);
    }

    /**
     * 排序: Order By 字段... DESC
     *
     * @param columns
     * @return
     */
    default Children orderByDesc(String... columns) {
        return orderBy(false, columns);
    }


}
