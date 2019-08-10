package cn.tswine.jdbc.common.annotation;

/**
 * 表字段注解
 *
 * @Author: silly
 * @Date: 2019/8/10 13:22
 * @Version 1.0
 * @Desc
 */
public @interface TableField {
    /**
     * 字段值
     */
    String value() default "";
}
