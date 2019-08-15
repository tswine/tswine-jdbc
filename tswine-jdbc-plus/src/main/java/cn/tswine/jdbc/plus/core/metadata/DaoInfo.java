package cn.tswine.jdbc.plus.core.metadata;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.plus.core.dao.AbstractDao;
import cn.tswine.jdbc.plus.core.rules.IDBLabel;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dao对象帮助类
 *
 * @Author: silly
 * @Date: 2019/8/14 20:35
 * @Version 1.0
 * @Desc
 */
public class DaoInfo {
    Map<IDBLabel, DataSource> dataSourceStore;

    /**
     * 储存反射类dao对象信息
     */
    private final Map<Class<?>, AbstractDao> DAO_INFO_CACHE = new ConcurrentHashMap<>();

    public DaoInfo(Map<IDBLabel, DataSource> dataSourceStore) {
        this.dataSourceStore = dataSourceStore;
    }

    /**
     * 获取到对象
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws TswineJdbcException
     */
    public <T> T create(Class<T> clazz) throws TswineJdbcException {
        AbstractDao abstractDao = DAO_INFO_CACHE.get(clazz);
        try {
            if (abstractDao == null) {
                synchronized (DaoInfo.class) {
                    abstractDao = DAO_INFO_CACHE.get(clazz);
                    if (abstractDao == null) {
                        abstractDao = (AbstractDao) clazz.newInstance();
                        abstractDao.setDataSource(dataSourceStore.get(abstractDao.getDbLabel()));
                        DAO_INFO_CACHE.put(clazz, abstractDao);
                    } else {
                        return (T) abstractDao;
                    }
                }
            }
            return (T) abstractDao;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new TswineJdbcException(e.getMessage(), e);
        }
    }

}
