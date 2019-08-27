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

    /**
     * 插入
     */
    INSERT("insert", "插入一条数据", "INSERT INTO %s ( %s ) VALUES ( %s )"),
    /**
     * 删除
     */
    deleteById("deleteById", "插入一条数据", ""),
    deleteByMap("deleteByMap", "插入一条数据", ""),
    delete("", "插入一条数据", ""),
    deleteBatchIds("deleteBatchIds", "插入一条数据", ""),

    /**
     * 更新
     */
    updateById("updateById", "插入一条数据", ""),
    update("update", "插入一条数据", ""),
    /**
     * 查找
     */
    SELECT("select", "根据where条件通用查询", "SELECT %s FROM %s WHERE %s");

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
