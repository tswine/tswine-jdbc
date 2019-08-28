package cn.tswine.jdbc.plus.sql;

import lombok.Getter;
import lombok.Setter;

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
    @Getter
    protected final String sql;
    /**
     * sql参数
     */
    @Getter
    protected final List<Object[]> parameters = new ArrayList<>();

    /**
     * 查询的结果集
     */
    @Getter
    @Setter
    private List<Map<String, Object>> results;

    /**
     * 更新受影响的行数
     */
    @Getter
    @Setter
    private int update;

    /**
     * 批量更新
     */
    @Getter
    @Setter
    private int[] batchUpdate;

    public SqlSource(String sql, Object[] parameter) {
        this.sql = sql;
        this.parameters.add(parameter);
    }

    public SqlSource(String sql, List<Object[]> parameter) {
        this.sql = sql;
        for (Object[] param : parameter) {
            this.parameters.add(param);
        }
    }

    /**
     * 获取第一个参数
     *
     * @return
     */
    public Object[] get0() {
        if (parameters != null && parameters.size() > 0) {
            return parameters.get(0);
        }
        return null;
    }
    

}
