package cn.tswine.jdbc.plus.injector;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ReflectionUtils;
import cn.tswine.jdbc.plus.builder.SchemaBuilder;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.executor.Executor;
import cn.tswine.jdbc.plus.sql.SqlSource;
import cn.tswine.jdbc.plus.transaction.Transaction;
import cn.tswine.jdbc.plus.transaction.jdbc.JdbcTransactionFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/8/13 22:56
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractMethod<E extends Executor> implements IMethod {

    protected IDBLabel dbLabel;
    protected EntitySchema entitySchema;
    /**
     * 执行器类型
     */
    protected Class<E> executorClass;

    /**
     * 执行的参数
     */
    protected ArrayList<Object> params;

    public AbstractMethod(IDBLabel dbLabel, Class<?> eClazz,ArrayList<Object> params) {
        setExecutorClass();
        this.dbLabel = dbLabel;
        this.entitySchema = SchemaBuilder.buildEntity(eClazz, dbLabel.getDbType());
        this.params =params;
    }

    public abstract SqlSource injectSqlSource();

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
        SqlSource sqlSource = injectSqlSource();
        Transaction transaction = JdbcTransactionFactory.getInstance().newTransaction(dbLabel);
        Executor executor = ReflectionUtils.newInstance(executorClass,
                new Class<?>[]{Transaction.class}, new Object[]{transaction});
        executor.execute(sqlSource);
        return sqlSource;
    }
}
