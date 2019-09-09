package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.plus.JdbcPlusFactory;
import cn.tswine.jdbc.plus.config.PlusConfig;
import cn.tswine.jdbc.test.plus.dao.UserDao;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:28
 * @Version 1.0
 * @Desc
 */
public class BaseTest {

    protected JdbcPlusFactory factory;

    protected UserDao userDao;


    @Before
    public void init() {
        PlusConfig globalConfig = new PlusConfig();
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://192.168.47.100:3306/tswine_jdbc?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        dataSource.setUsername("tswine_jdbc");
        dataSource.setPassword("123456");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        globalConfig.addDataSource(DbType.MYSQL, dataSource);
        factory = new JdbcPlusFactory(globalConfig);
        userDao = factory.getDao(UserDao.class);
    }

}
