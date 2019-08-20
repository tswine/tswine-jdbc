package cn.tswine.jdbc.plus.conditions;

import java.io.Serializable;

/**
 * 查询条件封装
 *
 * @Author: silly
 * @Date: 2019/8/16 16:11
 * @Version 1.0
 * @Desc
 */
public interface Compare<Children, R> extends Serializable {
    
    /**
     * 等于 =
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children eq(R column, Object val);

}
