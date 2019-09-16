package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.metadata.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;

/**
 * 分页查询执行器
 *
 * @Author: silly
 * @Date: 2019/9/16 20:21
 * @Version 1.0
 * @Desc
 */
public class ExecutorPage extends BaseExecutor {
    public ExecutorPage(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void execute(SqlSource sqlSource) {

    }
}
