package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.comply.ExecutorPage;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.metadata.SqlSource;

/**
 * 分页查询
 *
 * @Author: silly
 * @Date: 2019/9/16 22:09
 * @Version 1.0
 * @Desc
 */
public class SelectPage extends AbstractMethod<ExecutorPage> {
    public SelectPage(IDBLabel dbLabel, SqlSource sqlSource) {
        super(dbLabel, sqlSource);
    }
}
