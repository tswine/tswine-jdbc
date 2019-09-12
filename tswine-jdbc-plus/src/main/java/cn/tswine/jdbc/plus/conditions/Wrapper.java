package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;

/**
 * 条件构造接口
 *
 * @Author: silly
 * @Date: 2019/8/13 22:33
 * @Version 1.0
 * @Desc
 */
public interface Wrapper extends ISqlSegment {
    /**
     * 设置实体
     *
     * @param entitySchema
     */
    void setEntitySchema(EntitySchema entitySchema);
}
