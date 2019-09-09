package cn.tswine.jdbc.plus;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.toolkit.ClassUtils;
import cn.tswine.jdbc.plus.config.PlusConfig;
import cn.tswine.jdbc.plus.dao.AbstractDao;
import cn.tswine.jdbc.plus.metadata.DaoMetaData;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;


/**
 * 工厂类
 *
 * @Author: silly
 * @Date: 2019/8/14 19:53
 * @Version 1.0
 * @Desc
 */
public class JdbcPlusFactory {
    private DaoMetaData daoMetaData;
    private PlusConfig globalConfig;

    public JdbcPlusFactory(PlusConfig globalConfig) {
        this.globalConfig = globalConfig;
        init();
    }

    public void init() {
        daoMetaData = ClassUtils.newInstance(DaoMetaData.class);
        //装载数据源仓库
        JdbcTransactionFactory.getInstance().load(globalConfig.getDataSourceStore());
    }

    /**
     * 获取Dao对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getDao(Class<? extends AbstractDao> clazz) throws TswineJdbcException {
        return daoMetaData.create(clazz);
    }

}
