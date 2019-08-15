package cn.tswine.jdbc.plus;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.plus.config.GlobalConfig;
import cn.tswine.jdbc.plus.core.metadata.DaoInfo;


/**
 * 工厂类
 *
 * @Author: silly
 * @Date: 2019/8/14 19:53
 * @Version 1.0
 * @Desc
 */
public class JdbcPlusFactory {
    private DaoInfo daoInfo = null;
    private GlobalConfig globalConfig;

    public JdbcPlusFactory(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        daoInfo = new DaoInfo(globalConfig.getDataSourceStore());
    }

    /**
     * 获取Dao对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getDao(Class<T> clazz) throws TswineJdbcException {
        return daoInfo.create(clazz);
    }

}
