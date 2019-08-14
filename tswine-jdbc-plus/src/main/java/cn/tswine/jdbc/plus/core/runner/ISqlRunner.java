package cn.tswine.jdbc.plus.core.runner;

import cn.tswine.jdbc.plus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

/**
 * sql执行器
 *
 * @Author: silly
 * @Date: 2019/8/13 22:48
 * @Version 1.0
 * @Desc
 */
public interface ISqlRunner {
    boolean insert(String sql, Object... args);

    boolean delete(String sql, Object... args);

    boolean update(String sql, Object... args);

    List<Map<String, Object>> selectList(String sql, Object... args);

    List<Object> selectObjs(String sql, Object... args);

    Object selectObj(String sql, Object... args);

    int selectCount(String sql, Object... args);

    Map<String, Object> selectOne(String sql, Object... args);

    IPage<Map<String, Object>> selectPage(IPage<?> page, String sql, Object... args);
}
