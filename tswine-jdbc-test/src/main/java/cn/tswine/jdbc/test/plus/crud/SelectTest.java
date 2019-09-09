package cn.tswine.jdbc.test.plus.crud;

import cn.tswine.jdbc.test.plus.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/9/2 16:36
 * @Version 1.0
 * @Desc
 */
public class SelectTest extends BaseTest {

    @Test
    public void select1() {
        String sql = "SELECT user_name FROM `user` WHERE create_time >=?  AND sex = ? GROUP BY user_name ";
        List<Map<String, Object>> select = userDao.select(sql, new Object[]{"2019-09-02", 1});
        Assert.assertNotNull(select);
        System.out.println(select);
    }

}
