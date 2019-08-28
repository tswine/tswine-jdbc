package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;

/**
 * 更新执行器
 *
 * @Author: silly
 * @Date: 2019/8/23 20:55
 * @Version 1.0
 * @Desc
 */
public class ExecutorUpdate extends BaseExecutor {

    public ExecutorUpdate(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void execute(SqlSource sqlSource) {
        int update = executeUpdate(sqlSource.getSql(), sqlSource.get0());
        sqlSource.setUpdate(update);
    }
}
