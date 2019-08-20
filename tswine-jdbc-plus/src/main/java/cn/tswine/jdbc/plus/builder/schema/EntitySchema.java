package cn.tswine.jdbc.plus.builder.schema;

import cn.tswine.jdbc.common.annotation.TableExclude;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/20 22:18
 * @Version 1.0
 * @Desc
 */
public class EntitySchema {

    /**
     * 类
     */
    Class<?> clazz;
    /**
     * 表名
     */
    String tableName;

    /**
     * 所有字段
     */
    Map<String, Field> fields;

    /**
     * 主键字段
     */
    Map<String, TableId> ids;
    /**
     * 排除字段
     */
    Map<String, TableExclude> excludes;

    // TODO 逻辑删除

    public EntitySchema(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void build() {
        Assert.isNotNull(clazz, "Class<?> clazz");
        List<Field> fieldList = ReflectionUtils.getFieldList(clazz);
        //TODO 实现逻辑
    }

}
