package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.comply.ExecutorUpdate;
import cn.tswine.jdbc.plus.injector.AbstractMethod;

/**
 * 更新
 *
 * @Author: silly
 * @Date: 2019/8/26 20:11
 * @Version 1.0
 * @Desc
 */
public class Update extends AbstractMethod<ExecutorUpdate> {

    public Update(IDBLabel dbLabel, String sql, Object[] params) {
        super(dbLabel, sql, params);
    }
}
