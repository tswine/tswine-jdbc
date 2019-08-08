package cn.tswine.jdbc.generator.config.query;

import cn.tswine.jdbc.generator.config.IDbQuery;

/**
 * @Author: silly
 * @Date: 2019/8/7 11:28
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractDbQuery implements IDbQuery {

    @Override
    public String[] fieldCustom() {
        return null;
    }
}
