package cn.tswine.jdbc.plus.enums;

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
    INSERT("insert", "插入一条数据", ""),
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
    SELECT_BY_ID("selectById", "根据ID查找一条数据:多主键", "SELECT %s FROM %s WHERE %s"),
    selectBatchIds("selectBatchIds", "插入一条数据", ""),
    selectByMap("selectByMap", "插入一条数据", ""),
    selectOne("selectOne", "插入一条数据", ""),
    selectCount("selectCount", "插入一条数据", ""),
    selectList("selectList", "插入一条数据", ""),
    selectMaps("selectMaps", "插入一条数据", ""),
    selectObjs("selectObjs", "插入一条数据", ""),
    selectPage("selectPage", "插入一条数据", ""),
    selectMapsPage("selectMapsPage", "插入一条数据", "");


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
