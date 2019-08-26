package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.executor.comply.ExecutorQuery;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.sql.SqlSource;

/**
 * 自定义查询条件
 *
 * @Author: silly
 * @Date: 2019/8/22 21:30
 * @Version 1.0
 * @Desc
 */
public class Select extends AbstractMethod<ExecutorQuery> {


    private SqlSource sqlSource;

    public Select(IDBLabel dbLabel, SqlSource sqlSource) {
        super(dbLabel, sqlSource.getClassEntity(), sqlSource.getParameters());
        this.sqlSource = sqlSource;
    }

    @Override
    public SqlSource injectSqlSource() {
        return sqlSource;
    }

}
