package cn.tswine.jdbc.plus.conditions.segments;

import cn.tswine.jdbc.plus.conditions.ISqlSegment;

import java.util.ArrayList;

/**
 * 查询普通片段
 *
 * @Author: silly
 * @Date: 2019/8/16 16:29
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractISegmentList extends ArrayList<ISqlSegment> implements ISqlSegment {
    
    /**
     * 获取sql片段
     *
     * @return
     */
    @Override
    public abstract String getSqlSegment();

}
