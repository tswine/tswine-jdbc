package cn.tswine.jdbc.plus.injector.methods;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.enums.SqlMethod;
import cn.tswine.jdbc.plus.executor.comply.ExecutorQuery;
import cn.tswine.jdbc.plus.injector.AbstractMethod;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.util.List;

/**
 * 通过where条件查询
 *
 * @Author: silly
 * @Date: 2019/8/22 21:30
 * @Version 1.0
 * @Desc
 */
public class SelectByWhere extends AbstractMethod<ExecutorQuery> {

    public SelectByWhere(IDBLabel dbLabel, Class<?> eClazz, List<Object> params) {
        super(dbLabel, eClazz, params);
    }

    @Override
    public SqlSource injectSqlSource() {
        //SELECT %s FROM %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_WHERE;
        //获取where条件语句
        String whereSql = String.valueOf(params.get(params.size()-1));
        String sql = String.format(sqlMethod.getSql(), getColumns(), entitySchema.getTableName(), whereSql);
        SqlSource sqlSource = new SqlSource(sql, params.subList(0, params.size() - 1));
        return sqlSource;
    }

}
