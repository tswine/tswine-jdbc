package cn.tswine.jdbc.plus.config;

import cn.tswine.jdbc.common.rules.IDBLabel;

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
public class PlusConfig {
    //数据源
    private final Map<IDBLabel, DataSource> dataSourceStore = new HashMap<>();

    /**
     * 添加数据源
     *
     * @param label 数据库标签
     * @param ds    数据源
     */
    public void addDataSource(IDBLabel label, DataSource ds) {
        this.dataSourceStore.put(label, ds);
    }

    /**
     * 获取数据源操作
     *
     * @return
     */
    public Map<IDBLabel, DataSource> getDataSourceStore() {
        return this.dataSourceStore;
    }

}
