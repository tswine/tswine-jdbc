package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.test.plus.entity.SysMenu;
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
public class SelectTest extends BaseTest {

    @Test
    public void selectByIds() {
        SysMenu sysMenu = sysMenuDao.selectById("1e69489fd9ce31559ba69e3978357127");
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
