package cn.tswine.jdbc.plus.conditions.segments;

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
    private final Map<String, Object> normal = new LinkedHashMap<>();


    /**
     * 添加
     *
     * @param sql    sql片段
     * @param params sql参数集
     */
    public void add(String sql, Object[] params) {
        normal.put(sql, params);
    }

}
