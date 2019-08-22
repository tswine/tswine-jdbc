package cn.tswine.jdbc.plus.builder.schema;

import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.plus.builder.ISchema;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/20 22:18
 * @Version 1.0
 * @Desc
 */
public class EntitySchema implements ISchema {

    public EntitySchema() {

    }

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
    final Map<String, Field> fields = new HashMap<>();

    /**
     * 主键字段
     */
    final Map<String, TableId> ids = new HashMap<>();

    // TODO 逻辑删除

    public EntitySchema(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void build(Class<?> clazz) {
        Assert.isNotNull(clazz, "Class<?> clazz");
        this.clazz = clazz;
        tablName().fields();
    }

    /**
     * 将map数据转载为实体对象
     *
     * @param clazz
     * @param data
     * @param <T>
     * @return
     */
    public <T> T load(Class<T> clazz, Map<String, Object> data) {
        //TODO  实现装载逻辑
        return null;
    }

    /**
     * 设置字段
     *
     * @return
     */
    private EntitySchema fields() {
        List<Field> fieldList = ReflectionUtils.getFieldList(clazz);
        if (fieldList != null && fieldList.size() > 0) {
            for (Field field : fieldList) {
                String columnName;
                TableField anno = field.getAnnotation(TableField.class);
                if (anno != null) {
                    boolean exist = anno.exist();
                    if (!exist) {
                        continue;
                    }
                    if (StringUtils.isEmpty(anno.value())) {
                        columnName = StringUtils.camelToUnderline(field.getName());
                    } else {
                        columnName = anno.value();
                    }
                    //判断是否为主键
                    if (anno instanceof TableField) {
                        ids.put(columnName, (TableId) anno);
                    }
                } else {
                    columnName = StringUtils.camelToUnderline(field.getName());
                }
                fields.put(columnName, field);
            }
        }
        return this;
    }

    /**
     * 获取class上TableName注解值
     */
    private EntitySchema tablName() {
        Class<TableName> tableNameClass = TableName.class;
        TableName annotation = clazz.getAnnotation(tableNameClass);
        if (annotation != null) {
            this.tableName = annotation.value();
        } else {
            //反驼峰命名
            this.tableName = StringUtils.camelToUnderline(clazz.getSimpleName());
        }
        return this;
    }

}
