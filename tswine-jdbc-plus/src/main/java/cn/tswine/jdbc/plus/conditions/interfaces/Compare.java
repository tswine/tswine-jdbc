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
public interface Compare<Children> extends Serializable {

    /**
     * 等于 =
     *
     * @param column 字段
     * @param value  值
     * @return column = ？
     */
    Children eq(String column, Object value);

    /**
     * 不等于 !=
     *
     * @param column 字段
     * @param value  值
     * @return column ！= ？
     */
    Children nq(String column, Object value);

    /**
     * 小于
     *
     * @param column
     * @param value
     * @return column < ?
     */
    Children lt(String column, Object value);

    /**
     * 小于等于
     *
     * @param column
     * @param value
     * @return column <= ?
     */
    Children le(String column, Object value);

    /**
     * 大于
     *
     * @param column
     * @param value
     * @return column > ?
     */
    Children gt(String column, Object value);

    /**
     * 大于等于
     *
     * @param column
     * @param value
     * @return column >= ?
     */
    Children ge(String column, Object value);

    /**
     * like
     *
     * @param column
     * @param value
     * @return username like '%value%'
     */
    Children like(String column, Object value);

    /**
     * like left
     *
     * @param column
     * @param value
     * @return username like '%value'
     */
    Children likeLeft(String column, Object value);

    /**
     * like right
     *
     * @param column
     * @param value
     * @return username like  'value%'
     */
    Children likeRight(String column, Object value);

    /**
     * not like
     *
     * @param column
     * @param value
     * @return username not like '%value%'
     */
    Children notLike(String column, Object value);

    /**
     * not like left
     *
     * @param column
     * @param value
     * @return username not like '%value'
     */
    Children notLikeLeft(String column, Object value);

    /**
     * not like right
     *
     * @param column
     * @param value
     * @return username not like  'value%'
     */
    Children notLikeRight(String column, Object value);


    /**
     * between
     *
     * @param column
     * @param value1
     * @param value2
     * @return username between value1 and value2
     */
    Children between(String column, Object value1, Object value2);


    /**
     * not between
     *
     * @param column
     * @param value1
     * @param value2
     * @return username between value1 and value2
     */
    Children notBetween(String column, Object value1, Object value2);


}
