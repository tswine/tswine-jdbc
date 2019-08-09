package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.pojo.EntityInfo;
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
     * 创建目录
     *
     * @return
     */
    public AbstractTemplateEngine mkdirs() {
        String outputDir = getConfigBuilder().getGlobalConfig().getOutputDir();
        List<String> outputDirs = new ArrayList<>();
        outputDirs.add(outputDir);
        outputDirs.add(outputDir + File.separator + getConfigBuilder().getStrategyConfig().getEntityPackageName());
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
            //批量生成文件
            //entity
            List<EntityInfo> entityInfoList = configBuilder.getEntityInfoList();
            for (EntityInfo entityInfo : entityInfoList) {
                Map<String, Object> paramsMap = getParamsMap(configBuilder);
                String entityName = entityInfo.getName();
                String outputFile = configBuilder.getGlobalConfig().getOutputDir()
                        + File.separator + configBuilder.getStrategyConfig().getEntityPackageName()
                        + File.separator + entityName + ".java";
                System.out.println(outputFile);
                paramsMap.put("entity", entityInfo);
                writer("/templates/entity.java.btl", outputFile, paramsMap);

            }
        } catch (Exception e) {
            throw new TswineJdbcException("AbstractTemplateEngine->batchGenerator", e);
        }
        return this;
    }

    public Map<String, Object> getParamsMap(ConfigBuilder configBuilder) {
        Map<String, Object> paramMap = new HashMap<>();
        //设置作者
        paramMap.put("author", configBuilder.getGlobalConfig().getAuthor());
        paramMap.put("parentPackage", configBuilder.getGlobalConfig().getParentPackage());
        paramMap.put("entityPackage", configBuilder.getStrategyConfig().getEntityPackageName());
        //设置日期
        paramMap.put("swagger", configBuilder.getGlobalConfig().isSwagger());
        paramMap.put("lombok", configBuilder.getGlobalConfig().isLombok());

        return paramMap;
    }

}
