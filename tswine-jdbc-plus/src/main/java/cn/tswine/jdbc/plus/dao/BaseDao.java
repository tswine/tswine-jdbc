package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.plus.injector.IMethod;
import cn.tswine.jdbc.plus.injector.methods.Select;
import cn.tswine.jdbc.plus.injector.methods.Update;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/26 18:47
 * @Version 1.0
 * @Desc
 */
public abstract class BaseDao implements Dao {

    public abstract IDBLabel getDbLabel();

    @Override
    public int insert(String sql, Object[] params) {
        return update(sql, params);
    }

    @Override
    public int delete(String sql, Object[] params) {
        //sql审计判断：params不能为空
        if (params == null || params.length > 0) {
            throw ExceptionUtils.tse("sql审计：参数不能为空（不允许执行删除sql，不加条件）");
        }
        return update(sql, params);
    }

    @Override
    public int update(String sql, Object[] params) {
        //sql审计判断：params不能为空
        if (params == null || params.length > 0) {
            throw ExceptionUtils.tse("sql审计：参数不能为空（不允许执行更新sql，不加条件）");
        }
        IMethod method = new Update(getDbLabel(), sql, params);
        SqlSource sqlSource = method.execute();
        return sqlSource.getUpdate();
    }

    @Override
    public List<Map<String, Object>> select(String sql, Object[] params) {
        IMethod method = new Select(getDbLabel(), sql, params);
        SqlSource sqlSource = method.execute();
        return sqlSource.getResults();
    }
}

