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


}
