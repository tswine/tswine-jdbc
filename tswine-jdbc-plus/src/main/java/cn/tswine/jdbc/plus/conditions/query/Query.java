package cn.tswine.jdbc.plus.conditions.query;

import java.io.Serializable;

/**
 * @Author: silly
 * @Date: 2019/8/16 15:41
 * @Version 1.0
 * @Desc
 */
public interface Query<Children, R> extends Serializable {
    /**
     * 设置查询的字段
     *
     * @param columns 查询字段数组
     * @return
     */
    Children select(R... columns);
}
