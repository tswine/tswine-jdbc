package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.enums.SqlMethod;
import cn.tswine.jdbc.common.enums.generator.UUIDGenerator;
import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.*;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.UpdateBatch;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * 抽象dao操作
 *
 * @Author: silly
 * @Date: 2019/8/13 22:36
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractDao<T> extends BaseDao implements ExpandDao<T> {

    protected Class<T> classEntity;

    protected EntitySchema entitySchema;

    protected IDBLabel dbLabel;

    /**
     * 不允许该类通过new创建
     */
    protected AbstractDao() {
        this.dbLabel = getDbLabel();
        this.setClassEntity();
        this.entitySchema = SchemaBuilder.buildEntity(classEntity, getDbLabel().getDbType());
    }

    @Override
    public int insert(String tableName, Map<String, Object> columnValues) {
        //将列值分开
        List<String> columnsList = new ArrayList<>();
        List<Object> valuesList = new ArrayList<>();
        columnValues.forEach((k, v) -> {
            columnsList.add(k);
            valuesList.add(v);
        });
        String sql = SqlUtils.getInsertSql(getDbLabel().getDbType(), entitySchema.getTableName(), columnsList);
        return insert(sql, valuesList.toArray());
    }

    @Override
    public int insert(T entity) {
        Assert.isNotNull(entity, "entity is empty");
        //获取所有字段
        Map<String, Field> fields = entitySchema.getFields();
        //处理主键
        Map<String, Object> methodValue = disposeIds(ReflectionUtils.getAllMethodValue(entity, fields), entitySchema.getIdsAnno());
        if (methodValue == null) {
            throw ExceptionUtils.tse("reflection not get entity values");
        }
        return insert(entitySchema.getTableName(), methodValue);
    }

    @Override
    public int[] insert(List<T> listEntity) {
        Assert.notEmpty(listEntity, "List entity is empty");
        List<Map<String, Object>> listValue = new ArrayList<>();
        //遍历每个对象，获取每个对象的值
        for (T t : listEntity) {
            //获取所有字段
            Map<String, Field> fields = entitySchema.getFields();
            //处理主键
            Map<String, Object> methodValue = disposeIds(
                    ReflectionUtils.getAllMethodValue(t, fields), entitySchema.getIdsAnno());
            listValue.add(methodValue);
        }
        List<Object[]> params = new ArrayList<>();
        //以第一个对象为标准，依次获取值参数
        Map<String, Object> firstT = listValue.get(0);
        Set<String> columns = firstT.keySet();
        int columnSize = columns.size();
        for (Map<String, Object> mapValue : listValue) {
            Object[] param = new Object[columnSize];
            Iterator<String> iterator = columns.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                param[i] = mapValue.get(iterator.next());
                i++;
            }
            params.add(param);
        }
        String sql = SqlUtils.getInsertSql(dbLabel.getDbType(), entitySchema.getTableName(), columns);
        IMethod method = new UpdateBatch(getDbLabel(), sql, params);
        SqlSource sqlSource = method.execute();
        return sqlSource.getBatchUpdate();
    }

    @Override
    public List<T> selectByWhere(String whereSql, Object... params) {
        //SELECT %s FROM %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.SELECT;
        String sql = String.format(sqlMethod.getSql(), getColumns(), entitySchema.getTableName(), whereSql);
        List<Map<String, Object>> maps = select(sql, params);
        return mapToObject(maps);
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "columnMap not empty");
        String sqlWhere = SqlUtils.getWhere(columnMap.keySet());
        return selectByWhere(sqlWhere, columnMap.values().toArray());
    }

    @Override
    public T selectById(Serializable... ids) {
        Assert.notEmpty(ids, "ids not empty");
        String sqlWhere = SqlUtils.getWhere(entitySchema.getIds());
        List<T> list = selectByWhere(sqlWhere, ids);
        return list != null ? list.get(0) : null;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        List<String> ids = entitySchema.getIds();
        if (ids.size() != 1) {
            throw ExceptionUtils.tse("The primary key only supports one");
        }
        String sqlIn = SqlUtils.getIn(ids.get(0), idList.size());
        return selectByWhere(sqlIn, idList.toArray());
    }

    @Override
    public int deleteById(Serializable... ids) {
        Assert.notEmpty(ids, "ids not empty");
        if (ids.length != entitySchema.getIds().size()) {
            throw ExceptionUtils.tse("ids length is not equal key size");
        }
        String sqlWhere = SqlUtils.getWhere(entitySchema.getIds());
        return deleteByWhere(entitySchema.getTableName(), sqlWhere, ids);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        Assert.notEmpty(idList, "idList not empty");
        List<String> ids = entitySchema.getIds();
        if (ids.size() != 1) {
            throw ExceptionUtils.tse("The primary key only supports one");
        }
        String sqlIn = SqlUtils.getIn(entitySchema.getIds().get(0), idList.size());
        return deleteByWhere(entitySchema.getTableName(), sqlIn, idList.toArray());
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "columnMap not empty");
        Set<String> columns = columnMap.keySet();
        String sqlWhere = SqlUtils.getWhere(columns);
        return deleteByWhere(entitySchema.getTableName(), sqlWhere, columnMap.values().toArray());
    }

    @Override
    public int updateById(T entity) {
        //获取所有字段
        Map<String, Field> fields = entitySchema.getFields();
        //获取所有字段值
        Map<String, Object> methodValue = ReflectionUtils.getAllMethodValue(entity, fields);
        if (methodValue == null) {
            throw ExceptionUtils.tse("reflection not get entity values");
        }
        Map<String, Object> whereIds = new HashMap<>();
        //排除主键数据
        List<String> ids = entitySchema.getIds();
        ids.forEach(k -> {
            Object o = whereIds.get(ids);
            whereIds.put(k, o);
            methodValue.remove(k);
        });
        return update(entitySchema.getTableName(), methodValue, whereIds);
    }

    @Override
    public int update(String tableName, Map<String, Object> update, Map<String, Object> where) {
        Assert.notEmpty(tableName, "tableName not empty");
        Assert.notEmpty(update, "update not empty");
        Assert.notEmpty(where, "where not empty");
        List<Object> params = new ArrayList<>();
        String setSql = SqlUtils.getSet(update.keySet());
        String whereSql = SqlUtils.getWhere(where.keySet());
        params.addAll(update.values());
        params.addAll(where.values());
        String sql = SqlUtils.getUpdateSql(tableName, setSql, whereSql);
        return update(sql, params.toArray());
    }

    @Override
    public int deleteByWhere(String tableName, String where, Object[] params) {
        Assert.isNotNull(tableName, "tableName not null");
        Assert.isNotNull(where, "where not null");
        Assert.notEmpty(params, "params not null");
        String sql = SqlUtils.getDeleteSql(entitySchema.getTableName(), where);
        return delete(sql, params);

    }


    /**
     * 暴露数据库操作对象
     *
     * @return
     */
    protected Transaction transaction() {
        return JdbcTransactionFactory.getInstance().newTransaction(dbLabel);
    }

    /**
     * 实体对象类型
     */
    private void setClassEntity() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type clazz = pType.getActualTypeArguments()[0];
            if (clazz instanceof Class) {
                this.classEntity = (Class<T>) clazz;
            }
        }
    }

    /**
     * 获取列名
     *
     * @return
     */
    protected String getColumns() {
        return StringUtils.join(entitySchema.getColumns().toArray(), ",");
    }

    /**
     * 集合Map转换为集合Object对象
     *
     * @param maps
     * @return
     */
    protected List<T> mapToObject(List<Map<String, Object>> maps) {
        if (maps == null) {
            return null;
        }
        List<T> list = new ArrayList<>();
        for (Map<String, Object> entity : maps) {
            Object obj = entitySchema.asEntity(entity);
            if (obj != null) {
                list.add((T) obj);
            }
        }
        return list;
    }

    /**
     * 处理主键
     *
     * @param values
     * @param ids
     */
    private Map<String, Object> disposeIds(Map<String, Object> values, Map<String, TableId> ids) {
        if (MapUtils.isEmpty(ids)) {
            return values;
        }
        ids.forEach((k, v) -> {
            switch (v.type()) {
                //uuid
                case UUID:
                    String newValue = new UUIDGenerator().get();
                    values.put(k, newValue);
                    break;
                //自增
                case AUTO:
                    values.remove(k);
                case ID_WORKER:
                    //TODO 实现全局唯一逻辑

            }
        });
        return values;
    }

}
