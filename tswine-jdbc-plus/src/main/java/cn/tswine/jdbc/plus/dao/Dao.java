package cn.tswine.jdbc.plus.dao;

import java.util.List;
import java.util.Map;

/**
 * 顶级Dao
 *
 * @Author: silly
 * @Date: 2019/8/13 22:30
 * @Version 1.0
 * @Desc
 */
public interface Dao {

    /**
     * 插入
     *
     * @param sql    执行的sql语句
     * @param params 执行的参数
     * @return 受影响的行数
     */
    int insert(String sql, Object[] params);


    /**
     * 删除
     *
     * @param sql    执行的sql语句
     * @param params 执行的参数
     * @return 受影响的行数
     */
    int delete(String sql, Object[] params);


    /**
     * 更新
     *
     * @param sql    执行的sql语句
     * @param params 执行的参数
     * @return 受影响的行数
     */
    int update(String sql, Object[] params);


    /**
     * 查找
     *
     * @param sql    执行的sql语句
     * @param params 执行的参数
     * @return 查询到的集合数据
     */
    List<Map<String, Object>> select(String sql, Object[] params);
}
