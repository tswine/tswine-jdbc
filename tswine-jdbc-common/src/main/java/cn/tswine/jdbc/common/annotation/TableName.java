package cn.tswine.jdbc.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表注解
 *
 * @Author: silly
 * @Date: 2019/8/10 13:21
 * @Version 1.0
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableName {
    /**
     * 表名
     *
     * @return
     */
    String value();
}
