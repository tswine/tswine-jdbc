package cn.tswine.jdbc.common.annotation;

import cn.tswine.jdbc.common.enums.IdType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表主键注解
 *
 * @Author: silly
 * @Date: 2019/8/10 13:23
 * @Version 1.0
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableId {

    /**
     * 值
     *
     * @return
     */
    String value();

    /**
     * 主键类型
     *
     * @return
     */
    IdType type() default IdType.AUTO;

    /**
     * 索引:复合主键
     *
     * @return
     */
    int index() default -1;
}
