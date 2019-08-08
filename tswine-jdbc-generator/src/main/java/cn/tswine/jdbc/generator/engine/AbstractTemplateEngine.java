package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.generator.builder.ConfigBuilder;

import java.util.Map;

/**
 * 模板引擎抽象类
 *
 * @Author: silly
 * @Date: 2019/8/7 15:41
 * @Version 1.0
 * @Desc
 */
public abstract class AbstractTemplateEngine {

    private ConfigBuilder configBuilder;

    /**
     * 模板引擎初始化
     */
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        this.configBuilder = configBuilder;
        return this;
    }

    /**
     * 将模板渲染后写出到指定文件
     *
     * @param templatePath 模板路径
     * @param outputFile   输出文件路径
     * @param params       模板参数
     */
    public abstract void writer(String templatePath, String outputFile, Map<String, Object> params);

}
