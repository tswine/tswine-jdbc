package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.enums.SqlMethod;
import cn.tswine.jdbc.plus.executor.comply.ExecutorQuery;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据ID查找记录
 *
 * @Author: silly
 * @Date: 2019/8/22 21:30
 * @Version 1.0
 * @Desc
 */
public class SelectBatchIds extends AbstractMethod<ExecutorQuery> {

    public SelectBatchIds(IDBLabel dbLabel, Class<?> eClazz, ArrayList<Object> params) {
        super(dbLabel, eClazz, params);
    }


    @Override
    public SqlSource injectSqlSource() {
        List<String> ids = entitySchema.getIds();
        if (ids.size() != 1) {
            throw ExceptionUtils.tse("The primary key only supports one");
        }
        //SELECT %s FROM %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.SELECT_BATCH_IDS;
        String sqlIn = SqlUtils.getIn(ids.get(0), params.size());
        String sql = String.format(sqlMethod.getSql(), getColumns(), entitySchema.getTableName(), sqlIn);
        SqlSource sqlSource = new SqlSource(sql, params);
        return sqlSource;
    }

}
