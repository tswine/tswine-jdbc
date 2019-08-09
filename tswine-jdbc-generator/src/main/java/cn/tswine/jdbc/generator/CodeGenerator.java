package cn.tswine.jdbc.generator;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import cn.tswine.jdbc.generator.config.TemplateConfig;
import cn.tswine.jdbc.generator.engine.AbstractTemplateEngine;
import cn.tswine.jdbc.generator.engine.BeetlTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.log4j.Logger;


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
    protected static final Logger logger = Logger.getLogger(CodeGenerator.class);


    /**
     * 数据库配置
     */
    private DataSourceConfig dataSourceConfig;
    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;

    /**
     * 模板引擎
     */
    private AbstractTemplateEngine templateEngine;
    /**
     * 模板配置
     */
    private TemplateConfig templateConfig;

    /**
     * 策略配置
     */
    private StrategyConfig strategyConfig;


    /**
     * 执行生成
     */
    public void execute() {
        logger.info("开始生成代码");
        if (dataSourceConfig == null) {
            throw new TswineJdbcException("数据源配置不能为空:DataSourceConfig");
        }
        if (globalConfig == null) {
            globalConfig = new GlobalConfig();
        }
        if (strategyConfig == null) {
            strategyConfig = new StrategyConfig();
        }
        if (templateConfig == null) {
            templateConfig = new TemplateConfig();
        }
        ConfigBuilder configBuilder = new ConfigBuilder(dataSourceConfig, globalConfig, strategyConfig, templateConfig);
        templateEngine = new BeetlTemplateEngine();
        templateEngine.init(configBuilder).mkdirs().batchGenerator();
        logger.info("代码生成完毕");
    }

}
