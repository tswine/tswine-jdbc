package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.enums.generator.UUIDGenerator;
import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.standard.IGeneric;
import cn.tswine.jdbc.common.toolkit.*;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.conditions.Wrapper;
import cn.tswine.jdbc.plus.conditions.query.QueryWrapper;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.UpdateBatch;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 抽象dao操作
 *
 * @Author: silly
 * @Date: 2019/8/13 22:36
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractDao<T> extends BaseDao implements ExpandDao<T>, IGeneric<T> {

    protected EntitySchema entitySchema;

    protected IDBLabel dbLabel;

    /**
     * 不允许该类通过new创建
     */
    protected AbstractDao() {
        this.dbLabel = getDbLabel();
        this.entitySchema = SchemaBuilder.buildEntity(clazz());
    }

    @Override
    public int insert(String tableName, Map<String, Object> columnValues) {
        String sql = SqlUtils.getInsertSql(getDbLabel().getDbType(), entitySchema.getTableName(),
                CollectionUtils.asArray(columnValues.keySet(), String.class));
        return insert(sql, columnValues.values().toArray());
    }

    @Override
    public int insert(T entity) {
        return insert(entity, null);
    }

    @Override
    public int insert(T entity, String[] excludeColumns) {
        Assert.isNotNull(entity, "entity is empty");
        //获取所有字段
        Map<String, Field> fields = entitySchema.getFields();
        //处理主键
        Map<String, Object> methodValue = disposeIds(ReflectionUtils.getAllMethodValue(entity, fields), entitySchema.getIdsAnno());
        if (methodValue == null) {
            throw ExceptionUtils.tse("reflection not get entity values");
        }
        //处理排除列
        if (ArrayUtils.isNotEmpty(excludeColumns)) {
            for (String column : excludeColumns) {
                if (methodValue.containsKey(column)) {
                    methodValue.remove(column);
                }
            }
        }
        return insert(entitySchema.getTableName(), methodValue);
    }

    @Override
    public int[] insert(List<T> listEntity) {
        return insert(listEntity, null);
    }

    @Override
    public int[] insert(List<T> listEntity, String[] excludeColumns) {
        Assert.notEmpty(listEntity, "List entity is empty");
        //获取对象需要排除后的列
        String[] columns = entitySchema.getColumns(excludeColumns);
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
        int columnSize = columns.length;
        for (Map<String, Object> mapValue : listValue) {
            Object[] param = new Object[columnSize];
            for (int i = 0; i < columnSize; i++) {
                param[i] = mapValue.get(columns[i]);
            }
            params.add(param);
        }
        String sql = SqlUtils.getInsertSql(dbLabel.getDbType(), entitySchema.getTableName(), columns);
        IMethod method = new UpdateBatch(getDbLabel(), sql, params);
        SqlSource sqlSource = method.execute();
        return sqlSource.getBatchUpdate();
    }

    /***SELECT***/

    @Override
    public List<T> selectList(String sql, Object... params) {
        List<Map<String, Object>> maps = select(sql, params);
        return mapToObject(maps);
    }

    @Override
    public List<Map<String, Object>> select(String tableName, String[] columns, String whereSql, Object... params) {
        tableName = SqlUtils.columnEscape(tableName, getDbLabel().getDbType().getPlaceholder());
        String columnSql = SqlUtils.getColumnSql(columns, getDbLabel().getDbType().getPlaceholder());
        String sql = SqlUtils.getSelectSql(tableName, columnSql, whereSql);
        return select(sql, params);
    }

    @Override
    public List<T> selectList(String tableName, String[] columns, String whereSql, Object... params) {
        List<Map<String, Object>> maps = select(tableName, columns, whereSql, params);
        return mapToObject(maps);
    }

    @Override
    public T selectById(Serializable... ids) {
        Assert.notEmpty(ids, "ids not empty");
        String[] columns = entitySchema.getColumns(null);
        String whereSql = SqlUtils.getWhere(entitySchema.getIds());
        List<T> list = selectList(entitySchema.getTableName(), columns, whereSql, ids);
        return list != null ? list.get(0) : null;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        String[] ids = entitySchema.getIds();
        Assert.notEmpty(ids, "The primary key only supports one");
        String sqlIn = SqlUtils.getIn(ids[0], idList.size());
        String whereSql = String.format("%s %s", SQLSentenceType.WHERE.getValue(), sqlIn);
        return selectList(entitySchema.getTableName(), entitySchema.getColumns(null), whereSql, idList.toArray());
    }

    @Override
    public List<T> select(Wrapper wrapper) {
        Assert.isNotNull(wrapper, "wrapper is null");
        if (!(wrapper instanceof QueryWrapper)) {
            throw ExceptionUtils.tse("wrapper type must QueryWrapper");
        }
        wrapper.setEntitySchema(entitySchema);
        QueryWrapper queryWrapper = (QueryWrapper) wrapper;
        String whereSql = queryWrapper.getSqlSegment();
        return selectList(entitySchema.getTableName(), queryWrapper.getColumns(), whereSql, queryWrapper.getParams());

    }

    @Override
    public T selectOne(Wrapper wrapper) {
        List<T> select = select(wrapper);
        if (select != null && select.size() > 0) {
            return select.get(0);
        }
        return null;
    }

    @Override
    public int selectCount(Wrapper wrapper) {
        //TODO 未实现业务逻辑
        return 0;
    }

    /***DELETE***/

    @Override
    public int deleteById(Serializable... ids) {
        //TODO
        return 0;
//        Assert.notEmpty(ids, "ids not empty");
//        if (ids.length != entitySchema.getIds().size()) {
//            throw ExceptionUtils.tse("ids length is not equal key size");
//        }
//        String sqlWhere = SqlUtils.getWhere(entitySchema.getIds());
//        return deleteByWhere(entitySchema.getTableName(), sqlWhere, ids);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        //TODO
        return 0;
//        Assert.notEmpty(idList, "idList not empty");
//        List<String> ids = entitySchema.getIds();
//        if (ids.size() != 1) {
//            throw ExceptionUtils.tse("The primary key only supports one");
//        }
//        String sqlIn = SqlUtils.getIn(entitySchema.getIds().get(0), idList.size());
//        return deleteByWhere(entitySchema.getTableName(), sqlIn, idList.toArray());
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        //TODO
        return 0;
//        Assert.notEmpty(columnMap, "columnMap not empty");
//        Set<String> columns = columnMap.keySet();
//        String sqlWhere = SqlUtils.getWhere(columns);
//        return deleteByWhere(entitySchema.getTableName(), sqlWhere, columnMap.values().toArray());
    }

    @Override
    public int delete(Wrapper wrapper) {
        if (wrapper == null) {
            throw ExceptionUtils.tse("wrapper is not null");
        }
        //TODO 逻辑实现
        return 0;
    }

    @Override
    public int updateById(T entity) {
        //TODO
        return 0;
//        //获取所有字段
//        Map<String, Field> fields = entitySchema.getFields();
//        //获取所有字段值
//        Map<String, Object> methodValue = ReflectionUtils.getAllMethodValue(entity, fields);
//        if (methodValue == null) {
//            throw ExceptionUtils.tse("reflection not get entity values");
//        }
//        Map<String, Object> whereIds = new HashMap<>();
//        //排除主键数据
//        List<String> ids = entitySchema.getIds();
//        ids.forEach(k -> {
//            Object o = whereIds.get(ids);
//            whereIds.put(k, o);
//            methodValue.remove(k);
//        });
//        return update(entitySchema.getTableName(), methodValue, whereIds);
    }

    @Override
    public int update(String tableName, Map<String, Object> update, Map<String, Object> where) {
        //TODO
        return 0;
//        Assert.notEmpty(tableName, "tableName not empty");
//        Assert.notEmpty(update, "update not empty");
//        Assert.notEmpty(where, "where not empty");
//        List<Object> params = new ArrayList<>();
//        String setSql = SqlUtils.getSet(update.keySet());
//        String whereSql = SqlUtils.getWhere(where.keySet());
//        params.addAll(update.values());
//        params.addAll(where.values());
//        String sql = SqlUtils.getUpdateSql(tableName, setSql, whereSql);
//        return update(sql, params.toArray());
    }

    @Override
    public int deleteByWhere(String tableName, String where, Object[] params) {
        //TODO
        return 0;
//        Assert.isNotNull(tableName, "tableName not null");
//        Assert.isNotNull(where, "where not null");
//        Assert.notEmpty(params, "params not null");
//        String sql = SqlUtils.getDeleteSql(entitySchema.getTableName(), where);
//        return delete(sql, params);
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
                    //TODO 实现自定义主键生成方法

            }
        });
        return values;
    }
}
