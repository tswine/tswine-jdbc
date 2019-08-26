package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.comply.ExecutorUpdate;
import cn.tswine.jdbc.plus.injector.AbstractMethod;

/**
 * @Author: silly
 * @Date: 2019/8/26 20:11
 * @Version 1.0
 * @Desc
 */
public class Insert extends AbstractMethod<ExecutorUpdate> {
    
    public Insert(IDBLabel dbLabel, String sql, Object[] params) {
        super(dbLabel, sql, params);
    }
}
