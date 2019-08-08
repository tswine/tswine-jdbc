package cn.tswine.jdbc.generator;

import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.EntityConfig;
import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.engine.AbstractTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 代码生成
 *
 * @Author: silly
 * @Date: 2019/8/7 10:59
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {

    /**
     * 数据库配置
     */
    DataSourceConfig dataSourceConfig;
    /**
     * 全局配置
     */
    GlobalConfig globalConfig;

    /**
     * 实体配置
     */
    EntityConfig entityConfig;

    /**
     * 模板引擎
     */
    AbstractTemplateEngine templateEngine;


    /**
     * 生成代码
     */
    public ConfigBuilder execute() {
        ConfigBuilder configBuilder = new ConfigBuilder(dataSourceConfig, globalConfig);
        return configBuilder;
    }

}
