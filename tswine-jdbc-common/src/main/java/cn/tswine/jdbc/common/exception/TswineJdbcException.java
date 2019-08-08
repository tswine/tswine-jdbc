package cn.tswine.jdbc.common.exception;

/**
 * 异常信息
 *
 * @Author: silly
 * @Date: 2019/8/7 13:27
 * @Version 1.0
 * @Desc
 */
public class TswineJdbcException extends RuntimeException {

    public TswineJdbcException(String message) {
        super(message);
    }

    public TswineJdbcException(Throwable throwable) {
        super(throwable);
    }

    public TswineJdbcException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
