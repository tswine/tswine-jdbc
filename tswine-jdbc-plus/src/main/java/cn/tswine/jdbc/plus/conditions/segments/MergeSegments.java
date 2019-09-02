package cn.tswine.jdbc.plus.conditions.segments;

import cn.tswine.jdbc.plus.conditions.OrderBy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 所有查询条件sql合集
 *
 * @Author: silly
 * @Date: 2019/8/16 16:19
 * @Version 1.0
 * @Desc
 */
public class MergeSegments {

    /**
     * 普通查询条件
     */
    private final Map<String, Object[]> normal = new LinkedHashMap<>();

    /**
     * orderBy
     */
    private final ArrayList<OrderBy> orderBy = new ArrayList<>();


    /**
     * 添加
     *
     * @param sql    sql片段
     * @param params sql参数集
     */
    public void add(String sql, Object[] params) {
        normal.put(sql, params);
    }

    /**
     * 排序
     *
     * @param sql
     */
    public void addOrderBy(OrderBy sql) {
        orderBy.add(sql);
    }

    public Map<String, Object[]> getNormal() {
        return normal;
    }

    public ArrayList<OrderBy> getOrderBy() {
        return orderBy;
    }

}
