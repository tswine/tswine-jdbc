package cn.tswine.jdbc.plus.injector;

import cn.tswine.jdbc.plus.injector.sql.SqlSource;

/**
 * @Author: silly
 * @Date: 2019/8/23 21:36
 * @Version 1.0
 * @Desc
 */
public interface IMethod {

    SqlSource execute();
}
