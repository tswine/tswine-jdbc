package cn.tswine.jdbc.plus.conditions.interfaces;

import java.io.Serializable;

/**
 * 查询条件
 *
 * @Author: silly
 * @Date: 2019/8/28 13:17
 * @Version 1.0
 * @Desc
 */
public interface Compare<Children, R> extends Serializable {

    /**
     * 等于 =
     *
     * @param column 字段
     * @param value  值
     * @return column = ？
     */
    Children eq(R column, Object value);

    /**
     * 不等于 !=
     *
     * @param column 字段
     * @param value  值
     * @return column ！= ？
     */
    Children nq(R column, Object value);

    /**
     * 小于
     *
     * @param column
     * @param value
     * @return column < ?
     */
    Children lt(R column, Object value);

    /**
     * 小于等于
     *
     * @param column
     * @param value
     * @return column <= ?
     */
    Children le(R column, Object value);

    /**
     * 大于
     *
     * @param column
     * @param value
     * @return column > ?
     */
    Children gt(R column, Object value);

    /**
     * 大于等于
     *
     * @param column
     * @param value
     * @return column >= ?
     */
    Children ge(R column, Object value);

    /**
     * like
     *
     * @param column
     * @param value
     * @return username like '%value%'
     */
    Children like(R column, Object value);

    /**
     * like left
     *
     * @param column
     * @param value
     * @return username like '%value'
     */
    Children likeLeft(R column, Object value);

    /**
     * like right
     *
     * @param column
     * @param value
     * @return username like  'value%'
     */
    Children likeRight(R column, Object value);

    /**
     * not like
     *
     * @param column
     * @param value
     * @return username not like '%value%'
     */
    Children notLike(R column, Object value);

    /**
     * not like left
     *
     * @param column
     * @param value
     * @return username not like '%value'
     */
    Children notLikeLeft(R column, Object value);

    /**
     * not like right
     *
     * @param column
     * @param value
     * @return username not like  'value%'
     */
    Children notLikeRight(R column, Object value);


}
