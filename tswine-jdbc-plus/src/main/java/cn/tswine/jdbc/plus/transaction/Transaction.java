package cn.tswine.jdbc.plus.transaction;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: silly
 * @Date: 2019/8/16 10:17
 * @Version 1.0
 * @Desc
 */
public interface Transaction {

    /**
     * 获取数据库连接
     *
     * @return DB connection
     * @throws SQLException
     */
    Connection getConnection() throws SQLException;

    /**
     * 提交
     *
     * @throws SQLException
     */
    void commit() throws SQLException;

    /**
     * 回滚
     *
     * @throws SQLException
     */
    void rollback();

    /**
     * 关闭数据库连接
     *
     * @throws SQLException
     */
    void close();

}
