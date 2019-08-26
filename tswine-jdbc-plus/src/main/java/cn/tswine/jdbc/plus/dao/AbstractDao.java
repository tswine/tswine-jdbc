package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.CollectionUtils;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.conditions.Wrapper;
import cn.tswine.jdbc.plus.converts.IResultConvert;
import cn.tswine.jdbc.plus.converts.ResultConvertEntity;
import cn.tswine.jdbc.plus.converts.ResultConvertList;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.Select;
import cn.tswine.jdbc.plus.injector.methods.SelectBatchIds;
import cn.tswine.jdbc.plus.injector.methods.SelectById;
import cn.tswine.jdbc.plus.injector.methods.SelectByWhere;
import cn.tswine.jdbc.plus.metadata.IPage;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.io.Serializable;
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
public abstract class AbstractDao<T> implements Dao<T> {

    /**
     * 泛型类型
     */
    protected Class<T> tClass;


    public AbstractDao() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type clazz = pType.getActualTypeArguments()[0];
            if (clazz instanceof Class) {
                this.tClass = (Class<T>) clazz;
            }
        }
    }

    public abstract IDBLabel getDbLabel();

    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int deleteById(Serializable... ids) {
        return 0;
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return 0;
    }

    @Override
    public int delete(Wrapper<T> wrapper) {
        return 0;
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return 0;
    }

    @Override
    public int updateById(T entity) {
        return 0;
    }

    @Override
    public int update(T entity, Wrapper<T> updateWrapper) {
        return 0;
    }


    /**
     * 自定义查询
     *
     * @param sqlSource
     * @return
     */
    protected List<T> select(SqlSource sqlSource) {
        IMethod method = new Select(getDbLabel(), sqlSource);
        SqlSource<T> execute = execute(method, new ResultConvertList());
        return execute.getResultList();
    }

    @Override
    public List<T> selectByWhere(String whereSql, List<Object> params) {
        params.add(whereSql);
        SqlSource<T> sqlSource = execute(SelectByWhere.class, params,
                new ResultConvertList());
        return sqlSource.getResultList();
    }

    @Override
    public T selectByIds(Serializable... ids) {
        ArrayList<Object> params = ArrayUtils.asList(ids);
        SqlSource<T> sqlSource = execute(SelectById.class, params,
                new ResultConvertEntity());
        return sqlSource.getResult();
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        SqlSource<T> sqlSource = execute(SelectBatchIds.class, CollectionUtils.asList(idList),
                new ResultConvertList());
        return sqlSource.getResultList();
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        Set<String> keys = columnMap.keySet();
        String whereSql = SqlUtils.getWhere(keys);
        return selectByWhere(whereSql, CollectionUtils.asList(columnMap.values()));
    }

    @Override
    public T selectOne(Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public Integer selectCount(Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public List<T> selectList(Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public IPage<T> selectPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectMapsPage(IPage<T> page, Wrapper<T> queryWrapper) {
        return null;
    }


    /**
     * 执行
     *
     * @param clazzMethod   执行方法类名
     * @param params        执行参数
     * @param resultConvert 结果转换器
     * @return
     */
    private SqlSource execute(Class<? extends IMethod> clazzMethod, List<Object> params, IResultConvert resultConvert) {
        IMethod method = ReflectionUtils.newInstance(clazzMethod,
                new Class<?>[]{IDBLabel.class, Class.class, List.class},
                new Object[]{getDbLabel(), tClass, params});
        return execute(method, resultConvert);
    }

    /**
     * 执行：重载
     *
     * @param method        执行方法对象
     * @param resultConvert 结果转换器
     * @return
     */
    private SqlSource execute(IMethod method, IResultConvert resultConvert) {
        SqlSource<T> sqlSource = method.execute();
        resultConvert.convertTo(SchemaBuilder.buildEntity(tClass, getDbLabel().getDbType()), sqlSource);
        return sqlSource;
    }


}
