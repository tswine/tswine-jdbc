package cn.tswine.jdbc.plus.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/23 20:17
 * @Version 1.0
 * @Desc
 */
public class SqlSource {
    /**
     * 执行的sql语句
     */
    private final String sql;
    /**
     * sql参数
     */
    private final ArrayList<Object> parameters;
    /**
     * 查询结果
     */
    private List<Map<String, Object>> resultSelect;
    /**
     * 更新结果
     */
    private int resultUpdate;

    public SqlSource(String sql, ArrayList<Object> parameter) {
        this.sql = sql;
        this.parameters = parameter;
    }

    public SqlSource setResultSelect(List<Map<String, Object>> resultSelect) {
        this.resultSelect = resultSelect;
        return this;
    }

    public int getResultUpdate() {
        return resultUpdate;
    }

    public SqlSource setResultUpdate(int resultUpdate) {
        this.resultUpdate = resultUpdate;
        return this;
    }

    public List<Map<String, Object>> getResultSelect() {
        return resultSelect;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public String getSql() {
        return sql;
    }
}
