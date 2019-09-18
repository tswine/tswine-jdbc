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
import cn.tswine.jdbc.plus.conditions.update.UpdateWrapper;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.SelectPage;
import cn.tswine.jdbc.plus.injector.methods.UpdateBatch;
import cn.tswine.jdbc.plus.metadata.IPage;
import cn.tswine.jdbc.plus.metadata.SqlSource;
import cn.tswine.jdbc.plus.metadata.pagination.Page;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

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
    protected String placeholder;

    /**
     * 不允许该类通过new创建
     */
    protected AbstractDao() {
        this.dbLabel = getDbLabel();
        this.entitySchema = SchemaBuilder.buildEntity(clazz());
        this.placeholder = this.dbLabel.getDbType().getPlaceholder();
    }

    /***INSERT***/
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
        String sql = getSelectSql(tableName, columns, whereSql);
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
        String whereSql = SqlUtils.getWhere(entitySchema.getIds(), placeholder);
        List<T> list = selectList(entitySchema.getTableName(), columns, whereSql, ids);
        return list != null ? list.get(0) : null;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        String[] ids = entitySchema.getIds();
        Assert.notEmpty(ids, "The primary key only support one");
        String sqlIn = SqlUtils.getIn(ids[0], idList.size());
        String whereSql = String.format("%s %s", SQLSentenceType.WHERE.getValue(), sqlIn);
        return selectList(entitySchema.getTableName(), entitySchema.getColumns(null), whereSql, idList.toArray());
    }

    private List<T> selectList(String tableName, String columnSql, String whereSql, Object... params) {
        String selectSql = getSelectSql(tableName, columnSql, whereSql);
        return selectList(selectSql, params);
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
        return selectList(entitySchema.getTableName(), queryWrapper.getColumnSql(placeholder),
                whereSql, queryWrapper.getParams());
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, Wrapper wrapper) {
        String selectSql = getSelectSql(wrapper);
        SqlSource sqlSource = new SqlSource(selectSql, wrapper.getParams());
        sqlSource.setPage(page);
        IMethod method = new SelectPage(getDbLabel(), sqlSource);
        sqlSource = method.execute();
        List<Map<String, Object>> results = sqlSource.getResults();
        page.setRecords(mapToObject(results));
        return page;
    }

    @Override
    public T selectOne(Wrapper wrapper) {
        IPage<T> page = new Page<>();
        page.setCurrent(1);
        page.setSize(1);
        page = selectPage(page, wrapper);
        if (page.getRecords() != null && page.getRecords().size() > 0) {
            return page.getRecords().get(0);
        }
        return null;
    }

    @Override
    public int selectCount(Wrapper wrapper) {
        Assert.isNotNull(wrapper, "wrapper is null");
        if (!(wrapper instanceof QueryWrapper)) {
            throw ExceptionUtils.tse("wrapper type must QueryWrapper");
        }
        String tableName = SqlUtils.columnEscape(entitySchema.getTableName(), placeholder);
        String whereSql = wrapper.getSqlSegment();
        String sql = String.format("SELECT count(0) as count FROM %s %s", tableName, whereSql);
        List<Map<String, Object>> list = select(sql, wrapper.getParams());
        if (list != null && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            return Integer.parseInt(String.valueOf(map.get("count")));
        }
        return 0;
    }


    /***DELETE***/
    @Override
    public int deleteByWhere(String tableName, String where, Object[] params) {
        Assert.isNotNull(tableName, "tableName not null");
        Assert.isNotNull(where, "where not null");
        Assert.notEmpty(params, "params not null");
        where = where.trim();
        if (!where.startsWith(SQLSentenceType.WHERE.getValue())) {
            where = String.format("%s %s", SQLSentenceType.WHERE.getValue(), where);
        }
        tableName = SqlUtils.columnEscape(tableName, placeholder);
        String sql = SqlUtils.getDeleteSql(tableName, where);
        return delete(sql, params);
    }

    @Override
    public int deleteById(Serializable... ids) {
        Assert.notEmpty(ids, "ids not empty");
        if (ids.length != entitySchema.getIds().length) {
            throw ExceptionUtils.tse("ids length is not equal key size");
        }
        String sqlWhere = SqlUtils.getWhere(entitySchema.getIds(), placeholder);
        return deleteByWhere(entitySchema.getTableName(), sqlWhere, ids);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        Assert.notEmpty(idList, "idList not empty");
        String[] ids = entitySchema.getIds();
        if (ids.length != 1) {
            throw ExceptionUtils.tse("The primary key only supports one");
        }
        String sqlIn = SqlUtils.getIn(ids[0], idList.size());
        return deleteByWhere(entitySchema.getTableName(), sqlIn, idList.toArray());
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        Assert.notEmpty(columnMap, "columnMap not empty");
        String[] columns = CollectionUtils.asArray(columnMap.keySet(), String.class);
        String whereSql = SqlUtils.getWhere(columns, placeholder);
        return deleteByWhere(entitySchema.getTableName(), whereSql, columnMap.values().toArray());
    }

    @Override
    public int delete(Wrapper wrapper) {
        Assert.isNotNull(wrapper, "wrapper is not null");
        return deleteByWhere(entitySchema.getTableName(), wrapper.getSqlSegment(), wrapper.getParams());
    }

    /***UPDATE***/
    @Override
    public int updateByWhere(String tableName, Map<String, Object> update, String whereSql, Object[] whereParams) {
        Assert.notEmpty(tableName, "tableName not empty");
        Assert.notEmpty(update, "update not empty");
        Assert.notEmpty(whereSql, "whereSql not empty");
        String[] columns = CollectionUtils.asArray(update.keySet(), String.class);
        String setSql = SqlUtils.getSet(SqlUtils.columnEscape(columns, placeholder));
        whereSql = StringUtils.formatStart(whereSql, SQLSentenceType.WHERE.getValue());
        tableName = SqlUtils.columnEscape(tableName, placeholder);
        String sql = SqlUtils.getUpdateSql(tableName, setSql, whereSql);
        //合并参数
        Object[] params = update.values().toArray();
        params = ArrayUtils.add(params, whereParams, Object.class);
        return update(sql, params);
    }

    @Override
    public int update(String tableName, Map<String, Object> update, Map<String, Object> where) {
        Assert.notEmpty(tableName, "tableName not empty");
        Assert.notEmpty(update, "update not empty");
        Assert.notEmpty(where, "where not empty");
        String whereSql = SqlUtils.getWhere(CollectionUtils.asArray(where.keySet(), String.class)
                , placeholder);
        return updateByWhere(tableName, update, whereSql, where.values().toArray());
    }

    @Override
    public int updateById(T entity, String[] excludeColumns) {
        //获取所有字段值
        Map<String, Object> methodValue = ReflectionUtils.getAllMethodValue(entity, entitySchema.getFields(excludeColumns));
        if (methodValue == null) {
            throw ExceptionUtils.tse("reflection not get entity values");
        }
        Map<String, Object> whereIds = new HashMap<>();
        //排除主键数据
        String[] ids = entitySchema.getIds();
        for (String id : ids) {
            Object value = methodValue.get(id);
            if (value != null) {
                whereIds.put(id, value);
                methodValue.remove(id);
            }
        }
        return update(entitySchema.getTableName(), methodValue, whereIds);
    }

    @Override
    public int update(T entity, Wrapper wrapper) {
        Assert.isNotNull(wrapper, "wrapper is not null");
        UpdateWrapper updateWrapper = (UpdateWrapper) wrapper;
        String[] excludeColumns = updateWrapper.getExcludeColumns();
        //获取所有字段值
        Map<String, Object> methodValue = ReflectionUtils.getAllMethodValue(entity, entitySchema.getFields(excludeColumns));
        return updateByWhere(entitySchema.getTableName(), methodValue, wrapper.getSqlSegment(), wrapper.getParams());
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

    private String getSelectSql(Wrapper wrapper) {
        Assert.isNotNull(wrapper, "wrapper is null");
        if (!(wrapper instanceof QueryWrapper)) {
            throw ExceptionUtils.tse("wrapper type must QueryWrapper");
        }
        QueryWrapper queryWrapper = (QueryWrapper) wrapper;
        wrapper.setEntitySchema(entitySchema);
        String whereSql = queryWrapper.getSqlSegment();
        return getSelectSql(entitySchema.getTableName(),
                queryWrapper.getColumnSql(placeholder), whereSql);

    }

    private String getSelectSql(String tableName, String[] columns, String whereSql) {
        tableName = SqlUtils.columnEscape(tableName, placeholder);
        String columnSql = SqlUtils.getColumnSql(columns, placeholder);
        return SqlUtils.getSelectSql(tableName, columnSql, whereSql);
    }

    private String getSelectSql(String tableName, String columnSql, String whereSql) {
        tableName = SqlUtils.columnEscape(tableName, placeholder);
        return SqlUtils.getSelectSql(tableName, columnSql, whereSql);
    }
}
