package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.plus.JdbcPlusFactory;
import cn.tswine.jdbc.plus.config.GlobalConfig;
import cn.tswine.jdbc.test.plus.dao.SysMenuDao;
import cn.tswine.jdbc.test.plus.entity.SysMenu;
import com.alibaba.druid.pool.DruidDataSource;
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
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.47.100:3306/tswine_boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        dataSource.setUsername("tswine_boot");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        globalConfig.addDataSource(DbType.MYSQL, dataSource);
        factory = new JdbcPlusFactory(globalConfig);
    }

    @Test
    public void getDao() {
        SysMenuDao dao = factory.getDao(SysMenuDao.class);

    }

    @Test
    public void select() {
        SysMenuDao dao = factory.getDao(SysMenuDao.class);
        SysMenu sysMenu = dao.selectById("1e69489fd9ce31559ba69e3978357127");
        System.out.println(sysMenu.toString());

    }
}
