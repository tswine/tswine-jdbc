package cn.tswine.jdbc.plus.transaction.jdbc;

import cn.tswine.jdbc.plus.enums.TransactionIsolationLevel;
import cn.tswine.jdbc.plus.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author: silly
 * @Date: 2019/8/16 10:20
 * @Version 1.0
 * @Desc
 */
public class JdbcTransaction implements Transaction {

    protected DataSource dataSource;
    protected TransactionIsolationLevel level;
    protected boolean autoCommit;
    protected Connection connection;

    public JdbcTransaction(DataSource dataSource, TransactionIsolationLevel desiredLevel, boolean autoCommit) {
        this.dataSource = dataSource;
        this.level = desiredLevel;
        this.autoCommit = autoCommit;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null) {
            openConnection();
        }
        return connection;
    }

    @Override
    public void commit() throws SQLException {
        if (connection != null && !connection.getAutoCommit()) {
            connection.commit();
        }
    }

    @Override
    public void rollback() {
        try {
            if (connection != null && !connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException e) {
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 打开数据库连接
     */
    protected void openConnection() throws SQLException {
        connection = dataSource.getConnection();
        if (level != null) {
            connection.setTransactionIsolation(level.getLevel());
        }
        setAutoCommit(autoCommit);
    }

    /**
     * 设置是否自动提交
     *
     * @param autoCommit
     */
    protected void setAutoCommit(boolean autoCommit) throws SQLException {
        if (connection.getAutoCommit() != autoCommit) {
            connection.setAutoCommit(autoCommit);
        }
    }
}
