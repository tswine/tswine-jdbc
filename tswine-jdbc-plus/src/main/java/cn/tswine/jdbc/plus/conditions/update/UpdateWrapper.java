package cn.tswine.jdbc.plus.conditions.update;

import cn.tswine.jdbc.plus.conditions.AbstractWrapper;

/**
 * @Author: silly
 * @Date: 2019/9/18 20:37
 * @Version 1.0
 * @Desc
 */
public class UpdateWrapper extends AbstractWrapper<UpdateWrapper> implements Update<UpdateWrapper> {
    String[] excludeColumns;

    @Override
    public UpdateWrapper exclude(String... excludeColumns) {
        this.excludeColumns = excludeColumns;
        return this;
    }

    /**
     * 获取查询的列数据
     *
     * @return
     */
    public String[] getExcludeColumns() {
        return excludeColumns;
    }
}
