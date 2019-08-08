package cn.tswine.jdbc.generator.config.rules;

/**
 * 数据库实践类型与实体类时间类型转换对应策略
 *
 * @Author: silly
 * @Date: 2019/8/7 12:50
 * @Version 1.0
 * @Desc
 */
public enum DateConvertType {
    /**
     * 只使用 java.util.date 代替
     */
    UTIL_DATE,
    /**
     * 使用 java.sql 包下的
     */
    SQL_DATE,
    /**
     * 使用 java.time 包下的
     * <p>java8 新的时间类型</p>
     */
    TIME_DATE

}
