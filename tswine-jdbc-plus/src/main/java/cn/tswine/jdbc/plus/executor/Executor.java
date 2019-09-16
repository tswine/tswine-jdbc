package cn.tswine.jdbc.plus.executor;

import cn.tswine.jdbc.plus.injector.sql.SqlSource;

/**
 * 执行器
 *
 * @Author: silly
 * @Date: 2019/8/16 10:06
 * @Version 1.0
 * @Desc
 */
public interface Executor {

    /**
     * 执行
     *
     * @param sqlSource
     * @return
     */
    void execute(SqlSource sqlSource);


}
