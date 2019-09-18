package cn.tswine.jdbc.test.plus.crud;

import cn.tswine.jdbc.plus.conditions.update.UpdateWrapper;
import cn.tswine.jdbc.test.plus.BaseTest;
import cn.tswine.jdbc.test.plus.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/9/18 20:14
 * @Version 1.0
 * @Desc
 */
public class DeleteTest extends BaseTest {

    //    int delete(String sql, Object[] params)
    @Test
    public void test1() {
        String sql = "DELETE FROM user WHERE user_name = ? AND sex = ?";
        int delete = userDao.delete(sql, new Object[]{"tswine117", 0});
        Assert.assertEquals(0, delete);
    }

    //    int deleteByWhere(String tableName, String sqlWhere, Object[] params);
    @Test
    public void test2() {
        String sql = "   WHERE user_name = ? AND sex = ?";
        int delete = userDao.deleteByWhere(User.TABLE_NAME, sql, new Object[]{"tswine117", 0});
        Assert.assertEquals(0, delete);
    }

    //    int deleteById(Serializable... ids);
    @Test
    public void test3() {
        int delete = userDao.deleteById("037479b2ec5f42af9aa264ce98836870");
        Assert.assertEquals(0, delete);
    }

    //    int deleteBatchIds(Collection<? extends Serializable> idList);
    @Test
    public void test4() {
        int delete = userDao.deleteBatchIds(Arrays.asList(
                "09d9d8efb397462abbb7c642eee2d9dd", "11946bc3fa304bf4a7879212a115fc1d"));
        Assert.assertEquals(0, delete);

    }

    //    int deleteByMap(Map<String, Object> columnMap);
    @Test
    public void test5() {
        Map<String, Object> params = new HashMap<>();
        params.put(User.FIELD_ID, "xxxx");
        params.put(User.FIELD_USER_NAME, "xxxx");
        params.put(User.FIELD_PASS_WORD, "xxxx");
        int delete = userDao.deleteByMap(params);
        Assert.assertEquals(0, delete);

    }

    //    int delete(Wrapper wrapper);
    @Test
    public void test6() {
        UpdateWrapper wrapper = new UpdateWrapper();
        wrapper.eq(User.FIELD_ID, "19ca9e349ebf442299b16f83fde56553")
                .or().eq(User.FIELD_USER_NAME, "tswine68");
        int delete = userDao.delete(wrapper);
        Assert.assertEquals(0, delete);
    }
}
