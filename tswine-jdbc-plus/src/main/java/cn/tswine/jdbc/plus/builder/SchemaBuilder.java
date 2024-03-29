package cn.tswine.jdbc.plus.builder;

import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:05
 * @Version 1.0
 * @Desc
 */
public class SchemaBuilder {

    /**
     * 实体解析缓存
     */
    private static final Map<Class<?>, ISchema> ENTITY_SCHEMA_CACHE = new ConcurrentHashMap<>();

    public synchronized static EntitySchema buildEntity(Class<?> clazz) {
        ISchema schema = ENTITY_SCHEMA_CACHE.get(clazz);
        if (schema == null) {
            schema = ReflectionUtils.newInstance(EntitySchema.class);
            schema.build(clazz);
            ENTITY_SCHEMA_CACHE.put(clazz, schema);
        }
        return (EntitySchema) schema;
    }


}
