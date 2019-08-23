package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.plus.executor.comply.ExecutorQuery;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.sql.SqlSource;

/**
 * 根据ID查找记录
 *
 * @Author: silly
 * @Date: 2019/8/22 21:30
 * @Version 1.0
 * @Desc
 */
public class SelectById extends AbstractMethod<ExecutorQuery> {

    public SelectById(IDBLabel dbLabel, Class<?> eClazz) {
        super(dbLabel, eClazz);
    }

    @Override
    public SqlSource injectSqlSource() {
        String columns = StringUtils.join(entitySchema.getColumns().toArray(), ",");
        String sql = String.format("SELECT %s FROM %s", columns, entitySchema.getTableName());
        System.out.println(sql);
        SqlSource sqlSource = new SqlSource(sql, null);
        return sqlSource;
    }
}
