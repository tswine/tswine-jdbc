package cn.tswine.jdbc.plus.injector;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.executor.Executor;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

/**
 * @Author: silly
 * @Date: 2019/8/13 22:56
 * @Version 1.0
 * @Desc
 */
public class AbstractMethod {

    IDBLabel dbLabel;

    public AbstractMethod(IDBLabel dbLabel) {
        this.dbLabel = dbLabel;
    }


}
