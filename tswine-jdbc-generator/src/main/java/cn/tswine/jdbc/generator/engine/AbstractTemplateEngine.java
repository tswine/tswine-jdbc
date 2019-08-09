package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.pojo.Table;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Getter
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

    /**
     * 模板后缀
     *
     * @return
     */
    public abstract String templateSuffix();

    /**
     * 创建目录
     *
     * @return
     */
    public AbstractTemplateEngine mkdirs() {
        String outputDir = getConfigBuilder().getGlobalConfig().getOutputDir();
        List<String> outputDirs = new ArrayList<>();
        outputDirs.add(outputDir);
        outputDirs.add(outputDir + File.separator + getConfigBuilder().getStrategyConfig().configEntity().getPackageName());
        for (String strDir : outputDirs) {
            File dir = new File(strDir);
            if (!dir.exists()) {
                //递归创建文件目录
                dir.mkdirs();
            }
        }
        return this;
    }

    /**
     * 批量生成文件
     *
     * @return
     */
    public AbstractTemplateEngine batchGenerator() {
        try {
            for (Table table : configBuilder.getTableList()) {
                Map<String, Object> paramsMap = getParamsMap(configBuilder, table);

                //生成实体
                String entityName = table.getName();
                String entityOutputFile = configBuilder.getGlobalConfig().getOutputDir()
                        + File.separator + configBuilder.getStrategyConfig().configEntity().getPackageName()
                        + File.separator + entityName + ".java";
                writer(configBuilder.getTemplateConfig().getEntity() + templateSuffix(), entityOutputFile, paramsMap);

            }
        } catch (Exception e) {
            throw new TswineJdbcException("AbstractTemplateEngine->batchGenerator", e);
        }
        return this;
    }

    /**
     * 设置生成模板需要的参数
     *
     * @param configBuilder
     * @param table
     * @return
     */
    public Map<String, Object> getParamsMap(ConfigBuilder configBuilder, Table table) {
        Map<String, Object> paramMap = new HashMap<>();
        //设置作者
        paramMap.put("author", configBuilder.getGlobalConfig().getAuthor());
        paramMap.put("parentPackage", configBuilder.getGlobalConfig().getParentPackage());

        //设置日期
        paramMap.put("swagger", configBuilder.getGlobalConfig().isSwagger());
        paramMap.put("lombok", configBuilder.getGlobalConfig().isLombok());
        paramMap.put("table", table);

        //实体参数
        paramMap.put("entityPackage", configBuilder.getStrategyConfig().configEntity().getPackageName());
        paramMap.put("tableConstant", configBuilder.getStrategyConfig().configEntity().isTableConstant());
        paramMap.put("columnConstant", configBuilder.getStrategyConfig().configEntity().isColumnConstant());

        return paramMap;
    }

}
