package cn.tswine.jdbc.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表字段注解
 *
 * @Author: silly
 * @Date: 2019/8/10 13:22
 * @Version 1.0
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableField {
    /**
     * 字段值
     */
    String value() default "";
}
