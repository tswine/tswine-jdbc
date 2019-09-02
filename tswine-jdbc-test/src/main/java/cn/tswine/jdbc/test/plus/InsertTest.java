package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.test.plus.entity.SysMenu;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:28
 * @Version 1.0
 * @Desc
 */
public class InsertTest extends BaseTest {

    @Test
    public void insert1() {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(System.currentTimeMillis() + "");
        sysMenu.setName("测试");
        sysMenu.setParentId("/admin/test");
        sysMenu.setIcon("test");
        sysMenu.setIsValid(0);
        sysMenu.setRemarks("备注");
        sysMenu.setIsDelete(1);
        sysMenu.setDeleteTime(LocalDateTime.now());
        sysMenu.setLevel(99);
        sysMenu.setSort(100);
        sysMenu.setType("test");
        sysMenu.setProjectId("-1");


        int insert = sysMenuDao.insert(sysMenu);
        System.out.println(insert);
    }

    public void insert2() {
//        sysMenuDao.insert
    }

    @Test
    public void insertBatch() {
        List<SysMenu> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SysMenu sysMenu = new SysMenu();
            sysMenu.setName("测试" + i);
            sysMenu.setParentId("/admin/test" + i);
            sysMenu.setIcon("test" + i);
            sysMenu.setIsValid(0);
            sysMenu.setRemarks("备注");
            sysMenu.setIsDelete(1);
            sysMenu.setDeleteTime(LocalDateTime.now());
            sysMenu.setLevel(i);
            sysMenu.setSort(i);
            sysMenu.setType("test");
            sysMenu.setProjectId("-1");

            list.add(sysMenu);
        }

        int[] insert = sysMenuDao.insert(list);
        for (int i : insert) {
            System.out.println(i);
        }
    }


}
