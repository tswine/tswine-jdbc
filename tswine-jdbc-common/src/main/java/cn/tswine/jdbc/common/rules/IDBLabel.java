package cn.tswine.jdbc.common.rules;

import cn.tswine.jdbc.common.annotation.DbType;

/**
 * 数据库标签
 *
 * @Author: silly
 * @Date: 2019/8/14 19:46
 * @Version 1.0
 * @Desc
 */
public interface IDBLabel {

    /**
     * 获取数据库标签
     *
     * @return
     */
    String getLabel();

    /**
     * 获取数据库类型
     *
     * @return
     */
    DbType getDbType();


}
