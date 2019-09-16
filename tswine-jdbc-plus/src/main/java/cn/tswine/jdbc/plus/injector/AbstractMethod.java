package cn.tswine.jdbc.plus.injector;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.plus.executor.Executor;
import cn.tswine.jdbc.plus.metadata.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: silly
 * @Date: 2019/8/13 22:56
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractMethod<E extends Executor> implements IMethod {

    protected IDBLabel dbLabel;
    /**
     * 执行器类型
     */
    protected Class<E> executorClass;

    protected SqlSource sqlSource;


    public AbstractMethod() {
        setExecutorClass();
    }


    public AbstractMethod(IDBLabel dbLabel, SqlSource sqlSource) {
        this();
        this.dbLabel = dbLabel;
        this.sqlSource = sqlSource;
    }

    public AbstractMethod(IDBLabel dbLabel, String sql, Object[] params) {
        this(dbLabel, new SqlSource(sql, params));
    }

    private void setExecutorClass() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type clazz = pType.getActualTypeArguments()[0];
            if (clazz instanceof Class) {
                this.executorClass = (Class<E>) clazz;
            }
        }
    }

    @Override
    public SqlSource execute() {
        Transaction transaction = JdbcTransactionFactory.getInstance().newTransaction(dbLabel);
        Executor executor = ReflectionUtils.newInstance(executorClass,
                new Class<?>[]{Transaction.class}, new Object[]{transaction});
        executor.execute(sqlSource);
        return sqlSource;
    }
}
