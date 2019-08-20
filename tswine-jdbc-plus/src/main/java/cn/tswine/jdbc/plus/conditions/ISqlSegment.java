package cn.tswine.jdbc.plus.conditions;

import java.io.Serializable;

/**
 * @Author: silly
 * @Date: 2019/8/16 15:11
 * @Version 1.0
 * @Desc
 */
@FunctionalInterface
public interface ISqlSegment extends Serializable {

    /**
     * sql片段
     *
     * @return
     */
    String getSqlSegment();
}
