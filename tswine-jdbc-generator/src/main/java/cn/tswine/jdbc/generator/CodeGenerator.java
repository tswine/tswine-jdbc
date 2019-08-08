package cn.tswine.jdbc.generator;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import cn.tswine.jdbc.generator.engine.AbstractTemplateEngine;
import cn.tswine.jdbc.generator.engine.BeetlTemplateEngine;
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
     * 模板引擎
     */
    AbstractTemplateEngine templateEngine;


    StrategyConfig strategyConfig;

    /**
     * 执行生成
     */
    public void execute() {
        if (dataSourceConfig == null) {
            throw new TswineJdbcException("数据源配置不能为空:DataSourceConfig");
        }
        //校验数据
        if (globalConfig == null) {
            globalConfig = new GlobalConfig();
        }
        ConfigBuilder configBuilder = new ConfigBuilder(dataSourceConfig, globalConfig, strategyConfig);
        templateEngine = new BeetlTemplateEngine();
        templateEngine.init(configBuilder).mkdirs().batchGenerator();
    }

}
