package cn.tswine.jdbc.plus.builder.schema;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
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
     * 数据库类型
     */
    DbType dbType;

    /**
     * 所有字段
     */
    final Map<String, Field> fields = new HashMap<>();

    /**
     * 主键字段
     */
    LinkedHashMap<String, TableId> ids = new LinkedHashMap<>();

    // TODO 逻辑删除

    public EntitySchema() {

    }


    @Override
    public void build(Class<?> clazz, Object[] params) {
        Assert.isNotNull(clazz, "Class<?> clazz");
        Assert.isNotNull(params, "params");
        this.clazz = clazz;
        this.dbType = (DbType) params[0];
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
                method.invoke(obj, val);
            } catch (Exception e) {
                throw ExceptionUtils.tse(e);
            }

        }
        return obj;
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

    /**
     * 获取表名
     *
     * @return
     */
    public String getTableName() {
        return this.tableName;
    }


    /**
     * 获取数据库表字段
     *
     * @param excludeColumns 排除的列名
     * @return
     */
    public List<String> getColumns(String... excludeColumns) {
        Map<String, Field> newFileds = MapUtils.copy(fields);
        if (excludeColumns != null) {
            for (String column : excludeColumns) {
                if (newFileds.containsKey(column)) {
                    newFileds.remove(newFileds);
                }
            }
        }
        return fieldToColumnList(newFileds.keySet());
    }

    /**
     * 获取所有的id
     *
     * @return
     */
    public List<String> getIds() {
        if (ids == null || ids.size() <= 0) {
            throw ExceptionUtils.tse(String.format("clazz:%s no id", clazz.getName()));
        }
        return fieldToColumnList(ids.keySet());
    }

    /**
     * 字段转换为列集合
     *
     * @param fields
     * @return
     */
    private List<String> fieldToColumnList(Set<String> fields) {
        ArrayList<String> columns = new ArrayList<>();
        String placeholder = dbType.getPlaceholder();
        fields.forEach(k -> {
            if (StringUtils.isNotEmpty(placeholder)) {
                k = String.format(placeholder, k);
            }
            columns.add(k);
        });
        return columns;
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
}
