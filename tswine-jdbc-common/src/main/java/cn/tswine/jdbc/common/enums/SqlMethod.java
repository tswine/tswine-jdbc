package cn.tswine.jdbc.common.enums;

/**
 * 支持的sql方法
 *
 * @Author: silly
 * @Date: 2019/8/22 21:22
 * @Version 1.0
 * @Desc
 */
public enum SqlMethod {

    INSERT("insert", "插入", "INSERT INTO %s ( %s ) VALUES ( %s )"),
    DELETE("delete", "删除", "DELETE FROM %s WHERE %s"),
    UPDATE("update", "更新", "UPDATE %s SET %s WHERE %s"),
    SELECT("select", "查找", "SELECT %s FROM %s %s");

    /**
     * 方法名
     */
    private final String method;
    /**
     * 描述
     */
    private final String desc;
    /**
     * sql模板语句
     */
    private final String sql;

    SqlMethod(String method, String desc, String sql) {
        this.method = method;
        this.desc = desc;
        this.sql = sql;
    }

    public String getMethod() {
        return method;
    }

    public String getDesc() {
        return desc;
    }

    public String getSql() {
        return sql;
    }

}
