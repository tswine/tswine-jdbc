package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.common.vo.KV;
import cn.tswine.jdbc.generator.config.rules.NameStrategy;
import lombok.Data;

import java.util.List;

/**
 * 策略配置
 *
 * @Author: silly
 * @Date: 2019/8/8 20:18
 * @Version 1.0
 * @Desc
 */
@Data
public class StrategyConfig {

    /**********************实体对象相关配置************************/
    /**
     * 是否生成实体
     */
    private boolean entityIsGenerator = true;
    /**
     * 实体包名
     */
    private String entityPackageName = "entity";

    /**
     * 实体类名命名策略
     */
    private NameStrategy entityClassNameStrategy = NameStrategy.BIG_CAMEL_CASE;

    /**
     * 字段命名策略
     */
    private NameStrategy entityFieldNameStrategy = NameStrategy.UNDERLINE_TO_CAMEL;

    /**
     * 是否为生成字段常量
     */
    private boolean entityColumnConstant = true;
    /**
     * 是否为表名生成字段常量
     */
    private boolean entityTableConstant = true;

    /**
     * 头部注解
     */
    private List<KV<String, String>> entityClassAnnotations;
    /**
     * 字段注解：针对所有字段生效
     */
    private List<KV<String, String>> entityFieldAnnotations;
    /**********************实体对象相关配置************************/
}
