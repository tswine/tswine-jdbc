package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.common.toolkit.MapUtils;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.sql.SqlMethod;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
public abstract  class AbstractDao<T> extends BaseDao implements ExpandDao<T> {

    protected Class<T> classEntity;

    protected EntitySchema entitySchema;


    /**
     * 不允许该类通过new创建
     */
    protected AbstractDao() {
        this.setClassEntity();
        this.entitySchema = SchemaBuilder.buildEntity(classEntity, getDbLabel().getDbType());
    }

    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int[] insert(List<T> listEntity) {
        return new int[0];
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
        MapUtils.isNotEmpty(columnMap, "columnMap不能为空");
        String sqlWhere = SqlUtils.getWhere(columnMap.keySet());
        return selectByWhere(sqlWhere, columnMap.values().toArray());
    }


    @Override
    public T selectByIds(Serializable... ids) {
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

}
