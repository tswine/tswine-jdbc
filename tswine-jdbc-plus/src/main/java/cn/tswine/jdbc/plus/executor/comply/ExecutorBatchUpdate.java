package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * 批量更新执行器器
 *
 * @Author: silly
 * @Date: 2019/8/23 20:55
 * @Version 1.0
 * @Desc
 */
public class ExecutorBatchUpdate extends BaseExecutor {
    private final static Logger LOG = LoggerFactory.getLogger(ExecutorBatchUpdate.class);

    public ExecutorBatchUpdate(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void execute(SqlSource sqlSource) {
        String sql = sqlSource.getSql();
        List<Object[]> args = sqlSource.getParameters();
        if (LOG.isDebugEnabled()) {
            LOG.debug("sql:{},size:{}", sql, args.size());
        }
        PreparedStatement ps = null;
        try (Connection conn = transaction.getConnection()) {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.size(); i++) {
                setObject(ps, args.get(i));
                ps.addBatch();
            }
            int[] flag = ps.executeBatch();
            transaction.commit();
            sqlSource.setBatchUpdate(flag);
        } catch (SQLException e) {
            transaction.rollback();
            throw ExceptionUtils.tse(e, "ExecutorBatchUpdate->execute,sql:%s", sql);
        } finally {
            close(null, ps);
            transaction.close();
        }
    }
}
