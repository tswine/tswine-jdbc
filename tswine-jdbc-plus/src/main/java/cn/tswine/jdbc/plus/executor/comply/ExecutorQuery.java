package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;

import java.util.List;
import java.util.Map;

/**
 * 查询执行器
 *
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
        List<Map<String, Object>> maps = executeQuery(sqlSource.getSql(), sqlSource.get0());
        sqlSource.setResults(maps);
    }
}
