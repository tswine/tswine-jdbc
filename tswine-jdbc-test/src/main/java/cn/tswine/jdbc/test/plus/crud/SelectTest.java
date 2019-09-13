package cn.tswine.jdbc.test.plus.crud;

import cn.tswine.jdbc.common.toolkit.CollectionUtils;
import cn.tswine.jdbc.plus.conditions.query.QueryWrapper;
import cn.tswine.jdbc.test.plus.BaseTest;
import cn.tswine.jdbc.test.plus.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/9/2 16:36
 * @Version 1.0
 * @Desc
 */
public class SelectTest extends BaseTest {

    //    List<Map<String, Object>> select(String sql, Object[] params);
    @Test
    public void select1() {
        String sql = "SELECT user_name FROM `user` WHERE create_time >=?  AND sex = ? GROUP BY user_name ";
        List<Map<String, Object>> select = userDao.select(sql, new Object[]{"2019-09-02", 1});
        Assert.assertNotNull(select);
        System.out.println(select);
    }


    //    List<T> selectList(String sql, Object[] params);
    @Test
    public void select2() {
        String sql = "SELECT user_name FROM `user` WHERE create_time >=?  AND sex = ? GROUP BY user_name ";
        List<User> list = userDao.selectList(sql, new Object[]{"2019-09-02", 1});
        Assert.assertNotNull(list);
        println(list);
    }

    //    List<Map<String, Object>> select(String tableName, String[] columns, String where, Object... params);
    @Test
    public void select3() {

    }

    //    List<T> selectList(String tableName, String[] columns, String where, Object... params);
    @Test
    public void select4() {

    }


    //    T selectById(Serializable... ids);
    @Test
    public void select5() {
        User user = userDao.selectById("21e84e67a6a243d8ba04209d1dccca29");
        Assert.assertNotNull(user);
        System.out.println(user);
    }

    //    List<T> selectBatchIds(Collection<? extends Serializable> idList);
    @Test
    public void select6() {
        List<String> ids = new ArrayList<>();
        ids.add("00fe3989a258429c9692ad450b5eac55");
        ids.add("21e84e67a6a243d8ba04209d1dccca29");
        ids.add("fe4fde91818f47698d4aa8ad361cbf4c");
        List<User> list = userDao.selectBatchIds(ids);
        Assert.assertEquals(list.size(), 3);
        println(list);
    }

    //    List<T> select(Wrapper<T> wrapper);
    @Test
    public void select7() {

    }

    //    T selectOne(Wrapper<T> wrapper);
    @Test
    public void select8() {

    }

    //    Integer selectCount(Wrapper<T> wrapper);
    @Test
    public void select9() {

    }


    /**
     * 测试wrapper-基本连接符AND
     */
    @Test
    public void wrapperBase() {
        //"user_name = ? AND sex = ? AND create_time >= ?";
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(User.FIELD_USER_NAME, "tswine").eq(User.FIELD_SEX, 1).ge(User.FIELD_CREATE_TIME, "2019-09-02");
        List<User> select = userDao.select(wrapper);
        println(select);
    }

    /**
     * 测试wrapper-in
     */
    @Test
    public void wrapperIn() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.in(User.FIELD_USER_NAME, new Object[]{"tswine", "tswine1", "tswine2", "tswine3"});
        List<User> select = userDao.select(wrapper);
        println(select);
    }

    /**
     * wrapper:like
     */
    @Test
    public void wrapperLike() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.notLikeRight(User.FIELD_USER_NAME, "测试");
        List select = userDao.select(wrapper);
        println(select);
    }

    @Test
    public void wrapperBetween() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.notBetween(User.FIELD_USER_NAME, "tswine1", "tswine3");
        List select = userDao.select(wrapper);
        println(select);
    }

    @Test
    public void wrapperNull() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.isNotNull(User.FIELD_USER_NAME);
        List select = userDao.select(wrapper);
        println(select);
    }

    @Test
    public void wrapperOrderBy() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc(User.FIELD_USER_NAME);
        List select = userDao.select(wrapper);
        println(select);
    }

    private void println(List<User> list) {
        if (CollectionUtils.isEmpty(list)) {
            System.out.println("数据为空");
        } else {
            System.out.println(list.size());
            list.forEach(k -> {
                System.out.println(k);
            });
        }

    }


}
