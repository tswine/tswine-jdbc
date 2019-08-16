package cn.tswine.jdbc.common.toolkit;

import cn.tswine.jdbc.common.exception.TswineJdbcException;

/**
 * 自定义异常辅助工具类
 *
 * @Author: silly
 * @Date: 2019/8/16 12:49
 * @Version 1.0
 * @Desc
 */
public class ExceptionUtils {

    /**
     * 返回新的自定义异常
     *
     * @param t
     * @param msg
     * @param params
     * @return
     */
    public static TswineJdbcException tse(Throwable t, String msg, Object... params) {
        return new TswineJdbcException(StringUtils.format(msg, params), t);
    }

    /**
     * 返回新的自定义异常
     *
     * @param msg
     * @param params
     * @return
     */
    public static TswineJdbcException tse(String msg, Object... params) {
        return new TswineJdbcException(StringUtils.format(msg, params));
    }

    /**
     * 返回新的自定义异常
     *
     * @return
     */
    public static TswineJdbcException tse(Throwable t) {
        return new TswineJdbcException(t);
    }
}
