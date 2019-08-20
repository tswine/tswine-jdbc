package cn.tswine.jdbc.plus.conditions.segments;

import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.plus.conditions.ISqlSegment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 所有查询条件sql合集
 *
 * @Author: silly
 * @Date: 2019/8/16 16:19
 * @Version 1.0
 * @Desc
 */
public class MergeSegments implements ISqlSegment {

    /**
     * 普通查询条件
     */
    private final NormalSegmentList normal = new NormalSegmentList();
    private final ArrayList<Object> normalParams = new ArrayList<>();

    /**
     * 添加
     *
     * @param params     params 条件参数
     * @param sqlSegment sql语句
     */
    public void add(Object[] params, ISqlSegment... sqlSegment) {
        List<ISqlSegment> list = Arrays.asList(sqlSegment);
        ISqlSegment firstSqlSegment = list.get(0);
        //TODO 其他类型判断
        normal.addAll(list);
        if (ArrayUtils.isNotEmpty(params)) {
            normalParams.addAll(Arrays.asList(params));
        }
    }

    @Override
    public String getSqlSegment() {
        //TODO 实现逻辑
        return null;
    }
}
