package cn.tswine.jdbc.plus.conditions.update;

import java.io.Serializable;

/**
 * @Author: silly
 * @Date: 2019/9/18 20:37
 * @Version 1.0
 * @Desc
 */
public interface Update<Children> extends Serializable {
    /**
     * 设置排除字段
     *
     * @param excludeColumns 排除列
     * @return
     */
    Children exclude(String... excludeColumns);


}
