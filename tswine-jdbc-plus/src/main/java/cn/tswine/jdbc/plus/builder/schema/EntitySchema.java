package cn.tswine.jdbc.plus.builder.schema;

import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import cn.tswine.jdbc.common.rules.FieldType;
import cn.tswine.jdbc.common.rules.IFieldType;
import cn.tswine.jdbc.common.rules.ObjectConvert;
import cn.tswine.jdbc.common.toolkit.*;
import cn.tswine.jdbc.plus.builder.ISchema;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: silly
 * @Date: 2019/8/20 22:18
 * @Version 1.0
 * @Desc
 */
public class EntitySchema implements ISchema {
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
    LinkedHashMap<String, TableId> ids = new LinkedHashMap<>();

    public EntitySchema() {

    }


    @Override
    public void build(Class<?> clazz, Object[] params) {
        Assert.isNotNull(clazz, "Class<?> clazz");
        this.clazz = clazz;
        tableName().fields();
    }

    /**
     * 将map数据转载为实体对象
     *
     * @param result
     * @return
     */
    public Object asEntity(Map<String, Object> result) {
        //TODO 优化
        Object obj = ReflectionUtils.newInstance(clazz);
        for (Map.Entry<String, Object> entry : result.entrySet()) {
            String column = entry.getKey();
            Object val = entry.getValue();
            if (val == null) {
                //数据为空不处理
                continue;
            }
            Field field = fields.get(column);
            if (field == null) {
                //实体对象不包含此列，不处理
                continue;
            }
            //获取方法名
            String methodName = StringUtils.concatCapitalize("set", field.getName());
            try {
                Method method = clazz.getMethod(methodName, field.getType());
                IFieldType fieldType = FieldType.valueOf(field.getType());
                ObjectConvert convert = fieldType.convert();
                if (convert != null) {
                    val = convert.convert(val);
                }
                method.invoke(obj, val);
            } catch (Exception e) {
                throw ExceptionUtils.tse(e);
            }

        }
        return obj;
    }

    /**
     * 获取表名
     *
     * @return
     */
    public String getTableName() {
        return this.tableName;
    }


    /**
     * 获取实体对象对应的数据库表列字段
     *
     * @param excludeColumns 排除的列名
     * @return
     */
    public String[] getColumns(String... excludeColumns) {
        Map<String, Field> fieldsNew = MapUtils.copy(fields);
        if (ArrayUtils.isNotEmpty(excludeColumns)) {
            for (String column : excludeColumns) {
                if (fieldsNew.containsKey(column)) {
                    fieldsNew.remove(fieldsNew);
                }
            }
        }
        return CollectionUtils.asArray(fieldsNew.keySet(), String.class);
    }

    /**
     * 获取所有的id列
     *
     * @return
     */
    public String[] getIds() {
        if (ids == null || ids.size() <= 0) {
            throw ExceptionUtils.tse(String.format("clazz:%s no id", clazz.getName()));
        }
        return CollectionUtils.asArray(ids.keySet(), String.class);
    }

    /**
     * 获取所有的ids
     *
     * @return
     */
    public LinkedHashMap<String, TableId> getIdsAnno() {
        return ids;
    }


    public Map<String, Field> getFields() {
        return fields;
    }


    /**
     * 按照注解TableId 中的index值排序
     *
     * @param ids
     * @return
     */
    private LinkedHashMap<String, TableId> sortByTableIdIndex(Map<String, TableId> ids) {
        List<Map.Entry<String, TableId>> list = new LinkedList<>(ids.entrySet());
        Collections.sort(list, Comparator.comparingInt(o -> o.getValue().index()));
        LinkedHashMap<String, TableId> result = new LinkedHashMap<>();
        for (Map.Entry<String, TableId> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;

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
                TableId annoId = null;
                String columnName = null;
                //判断是否为字段
                TableField annoField = field.getAnnotation(TableField.class);
                if (annoField != null) {
                    boolean exist = annoField.exist();
                    if (!exist) {
                        continue;
                    }
                    columnName = annoField.value();
                } else {
                    annoId = field.getAnnotation(TableId.class);
                    if (annoId != null) {
                        columnName = annoId.value();
                    }
                }
                if (StringUtils.isEmpty(columnName)) {
                    columnName = StringUtils.camelToUnderline(field.getName());
                }
                fields.put(columnName, field);
                if (annoId != null) {
                    ids.put(columnName, annoId);
                }
            }
        }
        //排序id 索引
        if (ids != null && ids.size() > 0) {
            ids = sortByTableIdIndex(ids);
        }
        return this;
    }

    /**
     * 获取class上TableName注解值
     */
    private EntitySchema tableName() {
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
