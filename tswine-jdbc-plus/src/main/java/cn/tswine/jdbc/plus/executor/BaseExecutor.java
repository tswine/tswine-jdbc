package cn.tswine.jdbc.plus.executor;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/16 12:07
 * @Version 1.0
 * @Desc
 */
public abstract class BaseExecutor implements Executor {

    protected Transaction transaction;


    public BaseExecutor(Transaction transaction) {
        this.transaction = transaction;
    }

    public abstract void execute(SqlSource sqlSource);

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    /**
     * 执行更新:INSERT,DELETE,UPDATE
     *
     * @param sql  执行的sql
     * @param args 参数
     * @return 受影响的行数
     * @throws SQLException
     */
    public int executeUpdate(String sql, Object... args) throws TswineJdbcException {
        int count;
        PreparedStatement ps = null;
        try (Connection conn = transaction.getConnection()) {
            ps = conn.prepareStatement(sql);
            setObject(ps, args);
            count = ps.executeUpdate();
            transaction.commit();
        } catch (SQLException e) {
            throw ExceptionUtils.tse(e, "BaseExecutor->executeUpdate,sql:%s,params:%s", sql, StringUtils.join(args));
        } finally {
            close(null, ps);
            transaction.close();
        }
        return count;
    }

    /**
     * 执行查询:SELECT
     *
     * @param sql  执行的sql
     * @param args 参数
     * @return 查询到的数据
     */
    public List<Map<String, Object>> executeQuery(String sql, Object[] args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = transaction.getConnection();
            ps = conn.prepareStatement(sql);
            setObject(ps, args);
            rs = ps.executeQuery();
            while (rs.next()) {
                results.add(rsToMap(rs));
            }
        } catch (SQLException e) {
            throw ExceptionUtils.tse(e, "executeQuery,sql:%s,params:%s", sql, StringUtils.join(args));
        } finally {
            close(rs, ps);
            transaction.close();
        }
        return results;
    }

    /**
     * 批量执行:INSERT,DELETE,UPDATE
     *
     * @param sql        执行的sql
     * @param args       参数
     * @param batchCount 批量提交的数量
     * @return
     */
    public void executeBatch(String sql, List<Object[]> args, int batchCount) {
        PreparedStatement ps = null;
        try (Connection conn = transaction.getConnection()) {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.size(); i++) {
                setObject(ps, args.get(i));
                ps.addBatch();
                if (i % batchCount == 0) {
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw ExceptionUtils.tse(e, "BaseExecutor->executeBatch,sql:%s", sql);
        } finally {
            close(null, ps);
            transaction.close();
        }
    }

    /**
     * ResultSet  转成map
     *
     * @param rs
     * @return
     */
    public Map<String, Object> rsToMap(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        Map<String, Object> rsData = new HashMap<>(columnCount);
        for (int i = 1; i <= columnCount; i++) {
            String columnName = metaData.getColumnName(i);
            rsData.put(columnName, rs.getObject(i));
        }
        return rsData;
    }

    /**
     * PreparedStatement 设置参数
     *
     * @param ps
     * @param args
     */
    protected void setObject(PreparedStatement ps, Object... args) throws SQLException {
        if (ArrayUtils.isNotEmpty(args)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject((i + 1), args[i]);
            }
        }
    }

    /**
     * 关闭资源
     *
     * @param rs 结果集
     * @param ps 参数构造器
     */
    protected void close(ResultSet rs, PreparedStatement ps) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
        if (null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }
}
