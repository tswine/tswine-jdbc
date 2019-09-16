package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.comply.ExecutorBatchUpdate;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.metadata.SqlSource;

import java.util.List;

/**
 * 批量更新
 *
 * @Author: silly
 * @Date: 2019/8/26 20:11
 * @Version 1.0
 * @Desc
 */
public class UpdateBatch extends AbstractMethod<ExecutorBatchUpdate> {

    public UpdateBatch(IDBLabel dbLabel, String sql, List<Object[]> params) {
        super(dbLabel, new SqlSource(sql, params));
    }
}
