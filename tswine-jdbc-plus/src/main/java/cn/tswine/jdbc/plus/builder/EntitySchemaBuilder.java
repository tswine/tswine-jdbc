package cn.tswine.jdbc.plus.builder;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:05
 * @Version 1.0
 * @Desc
 */
public class EntitySchemaBuilder {

    /**
     * 实体解析缓存
     */
    private static final Map<Class<?>, EntitySchema> ENTITY_SCHEMA_CACHE = new ConcurrentHashMap<>();


    public synchronized static EntitySchema build(Class<?> clazz) {
        EntitySchema entitySchema = ENTITY_SCHEMA_CACHE.get(clazz);
        if (entitySchema == null) {
            entitySchema = new EntitySchema(clazz);
            entitySchema.build();
            ENTITY_SCHEMA_CACHE.put(clazz, entitySchema);
        }
        return entitySchema;
    }


}
