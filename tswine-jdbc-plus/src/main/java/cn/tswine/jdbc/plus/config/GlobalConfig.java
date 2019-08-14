package cn.tswine.jdbc.plus.config;

import cn.tswine.jdbc.plus.core.rules.IDBLabel;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置
 *
 * @Author: silly
 * @Date: 2019/8/14 19:54
 * @Version 1.0
 * @Desc
 */
public class GlobalConfig {
    //数据源
    private final Map<IDBLabel, DataSource> dataSource = new HashMap<>();

    /**
     * 添加数据源
     *
     * @param label 数据库标签
     * @param ds    数据源
     */
    public void addDataSource(IDBLabel label, DataSource ds) {
        this.dataSource.put(label, ds);
    }

}
