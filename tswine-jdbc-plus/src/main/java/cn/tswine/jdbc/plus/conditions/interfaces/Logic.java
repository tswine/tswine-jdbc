package cn.tswine.jdbc.plus.conditions.interfaces;

import java.io.Serializable;

/**
 * 逻辑接口
 *
 * @Author: silly
 * @Date: 2019/9/11 20:39
 * @Version 1.0
 * @Desc
 */
public interface Logic<Children> extends Serializable {
    /**
     * AND连接符
     * @return
     */
    Children and();

    /**
     * OR连接符
     * @return
     */
    Children or();
}
