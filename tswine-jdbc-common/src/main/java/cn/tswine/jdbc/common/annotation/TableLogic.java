package cn.tswine.jdbc.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 逻辑删除
 *
 * @Author: silly
 * @Date: 2019/8/20 21:35
 * @Version 1.0
 * @Desc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface TableLogic {
    String value();

    int[] delval();
    //TODO 定义逻辑删除值
}
