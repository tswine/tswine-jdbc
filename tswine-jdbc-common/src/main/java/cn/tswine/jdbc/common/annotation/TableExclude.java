package cn.tswine.jdbc.common.annotation;

import cn.tswine.jdbc.common.enums.ExcludeType;

/**
 * 排除字段
 *
 * @Author: silly
 * @Date: 2019/8/20 22:31
 * @Version 1.0
 * @Desc
 */
public @interface TableExclude {
    String value();

    ExcludeType type() default ExcludeType.ALL;
}
