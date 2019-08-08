package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.generator.config.rules.NameStrategy;


/**
 * 实体配置
 *
 * @Author: silly
 * @Date: 2019/8/7 15:00
 * @Version 1.0
 * @Desc
 */
public class EntityConfig  {

    /**
     * 实体包名
     */
    private String entity = "entity";

    /**
     * 实体命名策略
     */
    private NameStrategy entityNameStrategy = NameStrategy.UNDERLINE_TO_CAMEL;

    /**
     * 字段命名策略
     */
    private NameStrategy fieldNameStrategy = NameStrategy.UNDERLINE_TO_CAMEL;

    /**
     * 是否为生成字段常量
     */
    private boolean isColumnConstant = false;

    /**
     * 是否采用lombok注解
     */
    private boolean isLombok = false;


}
