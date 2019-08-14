package cn.tswine.jdbc.plus.core.metadata;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.plus.core.dao.AbstractDao;

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
    //锁对象
    private Object lock = new Object();

    /**
     * 储存反射类dao对象信息
     */
    private final Map<Class<?>, AbstractDao> DAO_INFO_CACHE = new ConcurrentHashMap<>();

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
                synchronized (lock) {
                    abstractDao = DAO_INFO_CACHE.get(clazz);
                    if (abstractDao == null) {
                        abstractDao = (AbstractDao) clazz.newInstance();
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
