package cn.tswine.jdbc.plus.executor;

import cn.tswine.jdbc.common.exception.TswineJdbcException;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 执行器
 *
 * @Author: silly
 * @Date: 2019/8/16 10:06
 * @Version 1.0
 * @Desc
 */
public interface Executor {
    /**
     * 执行更新:INSERT,DELETE,UPDATE
     *
     * @param sql  执行的sql
     * @param args 参数
     * @return 受影响的行数
     * @throws SQLException
     */
    int executeUpdate(String sql, Object... args) throws TswineJdbcException;

    /**
     * 执行查询:SELECT
     *
     * @param sql  执行的sql
     * @param args 参数
     * @return 查询到的数据
     */
    List<Map<String, Object>> executeQuery(String sql, Object... args);

    /**
     * 批量执行:INSERT,DELETE,UPDATE
     *
     * @param sql        执行的sql
     * @param args       参数
     * @param batchCount 批量提交的数量
     * @return
     */
    void executeBatch(String sql, List<Object[]> args, int batchCount);

}
