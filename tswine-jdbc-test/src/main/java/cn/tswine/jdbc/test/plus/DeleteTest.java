package cn.tswine.jdbc.test.plus;

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
public class DeleteTest extends BaseTest {

    @Test
    public void delete() {
        String sql = "DELETE FROM sys_menu WHERE id = ?";
        int delete = sysMenuDao.delete(sql, new Object[]{"0195a468ac2c485aa2df9975b62a6860"});
        System.out.println(delete);
    }


    @Test
    public void deleteById() {
        int i = sysMenuDao.deleteById("34f771b7f8964e6aa5a6b49df52a67e4");
        System.out.println(i);
    }

    @Test
    public void deleteBatchIds() {
        List<String> list = new ArrayList<>();
        list.add("359ba94097e94f3bb67be506a59e391d");
        list.add("35db811fc1a149c1b31c594a7ce7e636");
        list.add("38581dc62ae34327a4b6f7e4ee367049");
        int i = sysMenuDao.deleteBatchIds(list);
        System.out.println(i);
    }

    @Test
    public void deleteByMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("sort", 0);
        params.put("name", "测试0");
        int i = sysMenuDao.deleteByMap(params);
        System.out.println(i);
    }

    @Test
    public void deleteByWhere() {
        int i = sysMenuDao.deleteByWhere("sys_menu", "id = ? AND project_id = ?",
                new Object[]{"032f671f5f0e4ce1a0aaa1d0dfab5253", -1});
        System.out.println(i);
    }


}
