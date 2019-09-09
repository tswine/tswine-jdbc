package cn.tswine.jdbc.test.plus.crud;

import cn.tswine.jdbc.test.plus.BaseTest;
import cn.tswine.jdbc.test.plus.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: silly
 * @Date: 2019/9/2 16:35
 * @Version 1.0
 * @Desc
 */
public class InsertTest extends BaseTest {

    @Test
    public void insert1() {
        String sql = "INSERT INTO `tswine_jdbc`.`user` (  `id`,`user_name`,`pass_word`,`is_delete`,`create_time`,`sex`)" +
                "VALUES (?,?,?,?,?,?) ";
        int insert = userDao.insert(sql, new Object[]{String.valueOf(System.currentTimeMillis()), "测试", "密码", 0, new Date(), 1});
        Assert.assertEquals(insert, 1);
    }

    @Test
    public void insert2() {
        User user = new User();
        user.setUserName("tswine");
        user.setPassWord("123456");
        user.setIsDelete(0);
        user.setCreateTime(LocalDateTime.now());
        user.setSex(1);

        int insert = userDao.insert(user);
        Assert.assertEquals(insert, 1);
    }

    @Test
    public void insert3() {
        Map<String, Object> columns = new HashMap<>();
        columns.put("id", String.valueOf(System.currentTimeMillis()));
        columns.put("user_name", "tswine3");
        columns.put("pass_word", "密码");
        columns.put("is_delete", 0);
        columns.put("create_time", new Date());
        columns.put("sex", 1);

        int insert = userDao.insert("user", columns);
        Assert.assertEquals(insert, 1);
    }

    @Test
    public void insertBatch() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName("tswine" + i);
            user.setPassWord("密码" + i);
            user.setIsDelete(0);
            user.setCreateTime(LocalDateTime.now());
            user.setSex(1);

            list.add(user);
        }
        int[] insert = userDao.insert(list);
        Assert.assertEquals(insert.length, 10);

    }
}
