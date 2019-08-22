package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.plus.JdbcPlusFactory;
import cn.tswine.jdbc.plus.config.GlobalConfig;
import cn.tswine.jdbc.test.plus.dao.SysMenuDao;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:28
 * @Version 1.0
 * @Desc
 */
public class FactoryTest {

    JdbcPlusFactory factory;

    @Before
    public void init() {
        GlobalConfig globalConfig = new GlobalConfig();
        factory = new JdbcPlusFactory(globalConfig);
    }

    @Test
    public void getDao() throws InterruptedException {
        SysMenuDao dao = factory.getDao(SysMenuDao.class);

    }

    @Test
    public void select() {
        SysMenuDao dao = factory.getDao(SysMenuDao.class);
        dao.selectById("1");

    }
}
