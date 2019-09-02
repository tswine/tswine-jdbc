package cn.tswine.jdbc.test.plus;

import cn.tswine.jdbc.plus.conditions.query.QueryWrapper;
import cn.tswine.jdbc.test.plus.entity.SysMenu;
import org.junit.Test;

import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/9/2 10:52
 * @Version 1.0
 * @Desc
 */
public class QueryWrapperTest extends BaseTest {


    //SELECT id,project_id,NAME,parent_id,icon,sort,remarks
    //FROM sys_menu WHERE parent_id = '/admin/test1' AND icon = 'home'   AND NAME LIKE '%测试1%'
    //ORDER BY sort ASC ,id DESC
    @Test
    public void query() {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
//        wrapper.select(SysMenu.FIELD_ID, SysMenu.FIELD_PARENT_ID, SysMenu.FIELD_NAME,
//                SysMenu.FIELD_PARENT_ID, SysMenu.FIELD_ICON, SysMenu.FIELD_REMARKS);
        wrapper.eq("parent_id", "/admin/test1").eq("icon", "home")
                .notLike("name", "测试1")
                .orderByAsc("sort").orderByDesc("id");
        List<SysMenu> sysMenus = sysMenuDao.select(wrapper);
        for (SysMenu sysMenu : sysMenus) {
            System.out.println(sysMenu.toString());
        }
    }
}
