package cn.tswine.jdbc.plus.conditions.interfaces;

import cn.tswine.jdbc.plus.conditions.OrderBy;

import java.io.Serializable;

/**
 * 查询条件封装
 *
 * @Author: silly
 * @Date: 2019/8/28 14:51
 * @Version 1.0
 * @Desc
 */
public interface Func<Children> extends Serializable {

    /**
     * 是空值
     *
     * @param column
     * @return
     */
    Children isNull(String column);

    /**
     * 不是空值
     *
     * @param column
     * @return
     */
    Children isNotNull(String column);

    /**
     * in
     *
     * @param column
     * @param params
     * @return
     */
    Children in(String column, Object[] params);

    /**
     * not in
     *
     * @param column
     * @param params
     * @return
     */
    Children notIn(String column, Object[] params);


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
