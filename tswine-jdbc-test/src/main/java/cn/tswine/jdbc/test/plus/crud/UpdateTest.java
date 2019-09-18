package cn.tswine.jdbc.test.plus.crud;

import cn.tswine.jdbc.plus.conditions.update.UpdateWrapper;
import cn.tswine.jdbc.test.plus.BaseTest;
import cn.tswine.jdbc.test.plus.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/9/18 21:58
 * @Version 1.0
 * @Desc
 */
public class UpdateTest extends BaseTest {
    String id = "04fcef0986254f389438a663e662e4bf";

    //    int update(String sql, Object[] params);
    @Test
    public void test1() {
        String sql = "UPDATE user SET user_name = ?,pass_word = ? WHERE id = ? AND sex = ?";
        int update = userDao.update(sql, new Object[]{"test1", "password1", "04fcef0986254f389438a663e662e4bf", 0});
        Assert.assertEquals(1, update);
    }

    //    int updateByWhere(String tableName, Map<String, Object> update, String whereSql,Object[] params);
    @Test
    public void test2() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_name", "test22");
        params.put("pass_word", "password22");
        String whereSql = "WHERE id = ? ";
        int update = userDao.updateByWhere("user", params, whereSql, new Object[]{"04fcef0986254f389438a663e662e4bf"});
        Assert.assertEquals(1, update);
    }

    //    int updateById(T entity);
    @Test
    public void test3() {
        User user = userDao.selectById(id);
        user.setSex(3);
        user.setUserName("username3");
        user.setPassWord("password3");
        user.setSort(3);
        int update = userDao.updateById(user);
        Assert.assertEquals(1, update);
    }

    //    int updateById(T entity, String[] excludeColumns);
    @Test
    public void test4() {
        User user = userDao.selectById(id);
        user.setSex(4);
        user.setUserName("username4");
        user.setPassWord("password4");
        user.setSort(4);
        int update = userDao.updateById(user, new String[]{"sex", "sort"});
        Assert.assertEquals(1, update);
    }

    //    int update(String tableName, Map<String, Object> update, Map<String, Object> where);
    @Test
    public void test5() {
        Map<String, Object> set = new HashMap<>();
        set.put("user_name", "test22");
        set.put("pass_word", "password22");
        Map<String, Object> where = new HashMap<>();
        where.put("id", "04fcef0986254f389438a663e662e4bf");
        int update = userDao.update("user", set, where);
        Assert.assertEquals(1, update);
    }

    //    int update(T entity, Wrapper updateWrapper);
    @Test
    public void test6() {
        User user = userDao.selectById(id);
        user.setSex(6);
        user.setUserName("username66");
        user.setPassWord("password66");
        user.setSort(66);

        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.exclude("id", "sort").eq("id", user.getId()).eq("sex", 6);
        int update = userDao.update(user, updateWrapper);
        Assert.assertEquals(1, update);
    }
}
