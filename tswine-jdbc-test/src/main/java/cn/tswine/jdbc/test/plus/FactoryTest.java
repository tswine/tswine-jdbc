package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.plus.JdbcPlusFactory;
import cn.tswine.jdbc.plus.config.GlobalConfig;
import cn.tswine.jdbc.test.plus.dao.SysMenuDao;
import cn.tswine.jdbc.test.plus.entity.SysMenu;
import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:28
 * @Version 1.0
 * @Desc
 */
public class FactoryTest {

    JdbcPlusFactory factory;
    SysMenuDao sysMenuDao;


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
        sysMenuDao = factory.getDao(SysMenuDao.class);
    }

    @Test
    public void getDao() {
        SysMenuDao dao = factory.getDao(SysMenuDao.class);

    }

    @Test
    public void selectByIds() {
        SysMenu sysMenu = sysMenuDao.selectByIds("1e69489fd9ce31559ba69e3978357127");
        System.out.println(sysMenu.toString());

    }

    @Test
    public void selectBatchIds() {
        List<String> ids = new ArrayList<>();
        ids.add("1e69489fd9ce31559ba69e3978357127");
        ids.add("c2f64fd2c2fc661dd6f76aa344c49129");
        ids.add("f14de51884ef5a0e36ee86c7f5535c84");

        List<SysMenu> sysMenus = sysMenuDao.selectBatchIds(ids);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu.toString());
        }
    }


    @Test
    public void selectByMap() {
        Map<String, Object> params = new HashMap<>();
        params.put(SysMenu.FIELD_ID, "1e69489fd9ce31559ba69e3978357127");
        params.put(SysMenu.FIELD_NAME, "菜单管理");
        List<SysMenu> sysMenus = sysMenuDao.selectByMap(params);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu.toString());
        }
    }
}
