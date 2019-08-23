package cn.tswine.jdbc.plus.sql;

import java.util.ArrayList;

/**
 * @Author: silly
 * @Date: 2019/8/23 20:17
 * @Version 1.0
 * @Desc
 */
public class SqlSource {
    private final String sql;
    private final ArrayList<Object> parameters;

    //TODO 优化根据传入的解析器解析出对应的数据
    private Object result;

    public SqlSource(String sql, ArrayList<Object> parameter) {
        this.sql = sql;
        this.parameters = parameter;
    }

    public SqlSource setResult(Object result) {
        this.result = result;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public String getSql() {
        return sql;
    }
}
