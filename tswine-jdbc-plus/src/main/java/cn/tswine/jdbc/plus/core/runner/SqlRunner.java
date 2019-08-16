package cn.tswine.jdbc.plus.core.runner;

import cn.tswine.jdbc.plus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/16 9:51
 * @Version 1.0
 * @Desc
 */
public class SqlRunner implements ISqlRunner{
    @Override
    public boolean insert(String sql, Object... args) {
        return false;
    }

    @Override
    public boolean delete(String sql, Object... args) {
        return false;
    }

    @Override
    public boolean update(String sql, Object... args) {
        return false;
    }

    @Override
    public List<Map<String, Object>> selectList(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Object> selectObjs(String sql, Object... args) {
        return null;
    }

    @Override
    public Object selectObj(String sql, Object... args) {
        return null;
    }

    @Override
    public int selectCount(String sql, Object... args) {
        return 0;
    }

    @Override
    public Map<String, Object> selectOne(String sql, Object... args) {
        return null;
    }

    @Override
    public IPage<Map<String, Object>> selectPage(IPage<?> page, String sql, Object... args) {
        return null;
    }
}
