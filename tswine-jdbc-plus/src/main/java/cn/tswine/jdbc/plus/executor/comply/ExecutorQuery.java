package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/23 20:55
 * @Version 1.0
 * @Desc
 */
public class ExecutorQuery extends BaseExecutor {

    public ExecutorQuery(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void execute(SqlSource sqlSource) {
        Object[] args = null;
        if (sqlSource.getParameters() != null && sqlSource.getParameters().size() > 0) {
            args = sqlSource.getParameters().toArray();
        }
        List<Map<String, Object>> maps = executeQuery(sqlSource.getSql(), args);
        sqlSource.setResultSelect(maps);
    }
}
