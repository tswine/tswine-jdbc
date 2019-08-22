package cn.tswine.jdbc.plus.metadata;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.plus.dao.AbstractDao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: silly
 * @Date: 2019/8/22 21:58
 * @Version 1.0
 * @Desc
 */
public class DaoMetaData {
    /**
     * 储存反射类dao对象信息
     */
    private final Map<Class<?>, AbstractDao> DAO_INFO_CACHE = new ConcurrentHashMap<>();

    /**
     * 私有构造函数,不允许被new创建
     */
    private DaoMetaData() {
    }

    /**
     * 获取到对象
     *
     * @param clazz
     * @param <T>
     * @return
     * @throws TswineJdbcException
     */
    public <T> T create(Class<? extends AbstractDao> clazz) throws TswineJdbcException {
        AbstractDao abstractDao = DAO_INFO_CACHE.get(clazz);
        if (abstractDao == null) {
            synchronized (DaoMetaData.class) {
                abstractDao = DAO_INFO_CACHE.get(clazz);
                if (abstractDao == null) {
                    abstractDao = ReflectionUtils.newInstance(clazz);
                    DAO_INFO_CACHE.put(clazz, abstractDao);
                } else {
                    return (T) abstractDao;
                }
            }
        }
        return (T) abstractDao;
    }

}
