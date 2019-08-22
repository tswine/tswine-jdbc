package cn.tswine.jdbc.plus.transaction;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.enums.TransactionIsolationLevel;

/**
 * @Author: silly
 * @Date: 2019/8/16 11:27
 * @Version 1.0
 * @Desc
 */
public interface TransactionFactory {
    /**
     * 创建数据源
     *
     * @param label 数据源便签
     * @return
     */
    Transaction newTransaction(IDBLabel label);

    /**
     * 创建数据源
     *
     * @param label      数据源便签
     * @param level      级别
     * @param autoCommit 是否自动提交
     * @return
     */
    Transaction newTransaction(IDBLabel label, TransactionIsolationLevel level, boolean autoCommit);
}
