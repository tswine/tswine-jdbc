package cn.tswine.jdbc.plus.conditions.interfaces;

import cn.tswine.jdbc.plus.conditions.OrderBy;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 查询条件封装
 *
 * @Author: silly
 * @Date: 2019/8/28 14:51
 * @Version 1.0
 * @Desc
 */
public interface Func<Children, R> extends Serializable {

    /**
     * 是空值
     *
     * @param column
     * @return
     */
    Children isNull(R column);

    /**
     * 不是空值
     *
     * @param column
     * @return
     */
    Children isNotNull(R column);

    /**
     * in
     *
     * @param column
     * @param values
     * @return
     */
    default Children in(R column, Object... values) {
        return in(column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{}))
                .collect(Collectors.toList()));
    }

    /**
     * in
     *
     * @param column
     * @param coll
     * @return
     */
    Children in(R column, Collection<?> coll);

    /**
     * not in
     *
     * @param column
     * @param values
     * @return
     */
    default Children notIn(R column, Object... values) {
        return notIn(column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{}))
                .collect(Collectors.toList()));
    }

    /**
     * not in
     *
     * @param column
     * @param coll
     * @return
     */
    Children notIn(R column, Collection<?> coll);


    /**
     * 排序：Order By 字段
     *
     * @param isAsc   是否ASC排序
     * @param columns 排序字段
     * @return
     */
    Children orderBy(boolean isAsc, R... columns);

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
    default Children orderByAsc(R... columns) {
        return orderBy(true, columns);
    }

    /**
     * 排序: Order By 字段... DESC
     *
     * @param columns
     * @return
     */
    default Children orderByDesc(R... columns) {
        return orderBy(false, columns);
    }


}
