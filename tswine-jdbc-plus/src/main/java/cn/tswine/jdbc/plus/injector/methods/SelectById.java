package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.enums.SqlMethod;
import cn.tswine.jdbc.plus.executor.comply.ExecutorQuery;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.util.ArrayList;

/**
 * 根据ID查找记录
 *
 * @Author: silly
 * @Date: 2019/8/22 21:30
 * @Version 1.0
 * @Desc
 */
public class SelectById extends AbstractMethod<ExecutorQuery> {


    public SelectById(IDBLabel dbLabel, Class<?> eClazz, ArrayList<Object> params) {
        super(dbLabel, eClazz, params);
    }

    @Override
    public SqlSource injectSqlSource() {
        //SELECT %s FROM %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        String columns = StringUtils.join(entitySchema.getColumns().toArray(), ",");
        String where = SqlUtils.getWhere(entitySchema.getIds());
        String sql = String.format(sqlMethod.getSql(), columns, entitySchema.getTableName(), where);
        System.out.println(sql);
        SqlSource sqlSource = new SqlSource(sql, params);
        return sqlSource;
    }
}
