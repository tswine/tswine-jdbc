package cn.tswine.jdbc.plus.core.dao;

import cn.tswine.jdbc.plus.conditions.Wrapper;
import cn.tswine.jdbc.plus.core.metadata.IPage;
import cn.tswine.jdbc.plus.core.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.executor.Executor;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.io.Serializable;
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
    public T selectById(Serializable... ids) {
        Transaction transaction = JdbcTransactionFactory.getInstance().newTransaction(getDbLabel());
        Executor executor = new BaseExecutor(transaction);

        return null;
    }

    @Override
    public List<T> selectBatchIds(Collection<? extends Serializable> idList) {
        return null;
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
    public List<Object> selectObjs(Wrapper<T> queryWrapper) {
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


}
