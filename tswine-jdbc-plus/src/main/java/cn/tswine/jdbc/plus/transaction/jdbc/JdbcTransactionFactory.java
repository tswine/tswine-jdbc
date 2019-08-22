package cn.tswine.jdbc.plus.transaction.jdbc;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.enums.TransactionIsolationLevel;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: silly
 * @Date: 2019/8/16 11:31
 * @Version 1.0
 * @Desc
 */
public class JdbcTransactionFactory implements TransactionFactory {
    private static JdbcTransactionFactory instance;

    private Map<IDBLabel, DataSource> dataSourceStore = new ConcurrentHashMap<>();

    /**
     * 采用单例
     */
    private JdbcTransactionFactory() {
    }

    public static synchronized JdbcTransactionFactory getInstance() {
        if (instance == null) {
            instance = new JdbcTransactionFactory();
        }
        return instance;
    }

    public void load(Map<IDBLabel, DataSource> dataSourceStore) {
        dataSourceStore.forEach((k, v) -> {
            load(k, v);
        });
    }

    public void load(IDBLabel dbLabel, DataSource dataSource) {
        dataSourceStore.put(dbLabel, dataSource);
    }


    @Override
    public Transaction newTransaction(IDBLabel label) {
        return newTransaction(label, TransactionIsolationLevel.READ_COMMITTED, false);
    }

    @Override
    public Transaction newTransaction(IDBLabel dbLabel, TransactionIsolationLevel level, boolean autoCommit) {
        if (dataSourceStore == null) {
            throw new TswineJdbcException("dataSourceStore为空，请先load数据源仓库");
        }
        DataSource dataSource = dataSourceStore.get(dbLabel);
        if (dataSource == null) {
            throw new TswineJdbcException("没有获取到DataSource");
        }
        return new JdbcTransaction(dataSource, level, autoCommit);
    }
}
