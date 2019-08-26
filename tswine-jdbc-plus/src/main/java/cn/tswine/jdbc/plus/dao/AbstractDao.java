package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.CollectionUtils;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.conditions.Wrapper;
import cn.tswine.jdbc.plus.converts.IResultConvert;
import cn.tswine.jdbc.plus.converts.ResultConvertEntity;
import cn.tswine.jdbc.plus.converts.ResultConvertList;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.SelectBatchIds;
import cn.tswine.jdbc.plus.injector.methods.SelectById;
import cn.tswine.jdbc.plus.metadata.IPage;
import cn.tswine.jdbc.plus.sql.SqlSource;

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

    @Override
    public T selectByIds(Serializable... ids) {
        ArrayList<Object> params = ArrayUtils.asList(ids);
        SqlSource<T> sqlSource = execute(SelectById.class, params,
                new ResultConvertEntity());
        return sqlSource.getResult();
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        SqlSource<T> sqlSource = execute(SelectBatchIds.class, CollectionUtils.toList(idList),
                new ResultConvertList());
        return sqlSource.getResultList();
    }

    @Override
    public List<T> selectByMap(Map<String, Object> columnMap) {
        return null;
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


    private SqlSource execute(Class<? extends IMethod> clazzMethod, List<Object> params, IResultConvert resultConvert) {
        IMethod method = ReflectionUtils.newInstance(clazzMethod,
                new Class<?>[]{IDBLabel.class, Class.class, ArrayList.class},
                new Object[]{getDbLabel(), tClass, params});
        SqlSource<T> sqlSource = method.execute();
        resultConvert.convertTo(SchemaBuilder.buildEntity(tClass, getDbLabel().getDbType()), sqlSource);
        return sqlSource;
    }

}
