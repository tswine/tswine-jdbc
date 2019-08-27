package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.test.plus.entity.SysMenu;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:28
 * @Version 1.0
 * @Desc
 */
public class UpdateTest extends BaseTest {

    String id = "058a7dbb0b804ee48e10554fe94711be";

    @Test
    public void update() {
        String sql = "UPDATE sys_menu SET project_id = ? WHERE id = ? ";
        int update = sysMenuDao.update(sql, new Object[]{2, id});
        System.out.println(update);
    }

    @Test
    public void update1() {
        Map<String, Object> set = new HashMap<>();
        set.put("project_id", 3);
        Map<String, Object> where = new HashMap<>();
        where.put("id", id);
        int update = sysMenuDao.update("sys_menu", set, where);
        System.out.println(update);
    }


    @Test
    public void updateById() {
        SysMenu sysMenu = sysMenuDao.selectById(id);
        sysMenu.setProjectId("333");
        sysMenu.setParentId("11111");
        sysMenu.setName(null);
        sysMenuDao.updateById(sysMenu);
    }


}
