package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.vo.KV;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import cn.tswine.jdbc.generator.config.pojo.TableInfo;
import lombok.Getter;

import java.io.File;
import java.util.*;

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
        outputDirs.add(outputDir + File.separator + getConfigBuilder().getStrategyConfig().configMapper().getPackageName());
        outputDirs.add(outputDir + File.separator + getConfigBuilder().getStrategyConfig().configMapper().getXmlPath());
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
            for (TableInfo tableInfo : configBuilder.getTableList()) {
                Map<String, Object> paramsMap = getParamsMap(configBuilder, tableInfo);
                generatorEntity(tableInfo, paramsMap).generatorMapper(tableInfo, paramsMap);
            }
        } catch (Exception e) {
            throw new TswineJdbcException("AbstractTemplateEngine->batchGenerator", e);
        }
        return this;
    }

    /**
     * 生成mapper
     *
     * @param tableInfo
     * @param paramsMap
     * @return
     */
    private AbstractTemplateEngine generatorMapper(TableInfo tableInfo, Map<String, Object> paramsMap) {
        if (!configBuilder.getStrategyConfig().configMapper().isGenerator()) {
            return this;
        }
        StrategyConfig.MapperConfig mapperConfig = configBuilder.getStrategyConfig().configMapper();
        String entityName = tableInfo.getName();
        String mapperInterfaceName = String.format("%s%s", entityName, mapperConfig.getInterfaceSuffix());
        paramsMap.put("mapperPackage", mapperConfig.getPackageName());
        paramsMap.put("mapperXmlFile", mapperConfig.getXmlPath());
        paramsMap.put("mapperInterfaceName", mapperInterfaceName);
        String xmlOutputFile = configBuilder.getGlobalConfig().getOutputDir()
                + File.separator + mapperConfig.getXmlPath()
                + File.separator + entityName + ".xml";
        if (isExists(xmlOutputFile)) {
            if (!configBuilder.getGlobalConfig().isOverrideExistFile()) {
                throw new TswineJdbcException(String.format("mapper文件已经存在,全局配置不允许覆盖存在文件,%s", xmlOutputFile));
            }
        }
        writer(configBuilder.getTemplateConfig().getMapper() + templateSuffix(), xmlOutputFile, paramsMap);

        String mapperOutputFile = configBuilder.getGlobalConfig().getOutputDir()
                + File.separator + mapperConfig.getPackageName()
                + File.separator + mapperInterfaceName + ".java";
        if (isExists(mapperOutputFile)) {
            if (!configBuilder.getGlobalConfig().isOverrideExistFile()) {
                throw new TswineJdbcException(String.format("mapper接口文件已经存在,全局配置不允许覆盖存在文件,%s", mapperOutputFile));
            }
        }
        writer(configBuilder.getTemplateConfig().getMapperInterface() + templateSuffix(), mapperOutputFile, paramsMap);
        return this;
    }

    /**
     * 生成实体
     */
    private AbstractTemplateEngine generatorEntity(TableInfo tableInfo, Map<String, Object> paramsMap) {
        //生成实体
        if (configBuilder.getStrategyConfig().configEntity().isGenerator()) {
            //实体参数
            paramsMap.put("entityPackage", configBuilder.getStrategyConfig().configEntity().getPackageName());
            paramsMap.put("tableConstant", configBuilder.getStrategyConfig().configEntity().isTableConstant());
            paramsMap.put("columnConstant", configBuilder.getStrategyConfig().configEntity().isColumnConstant());

            String entityName = tableInfo.getName();
            String entityOutputFile = configBuilder.getGlobalConfig().getOutputDir()
                    + File.separator + configBuilder.getStrategyConfig().configEntity().getPackageName()
                    + File.separator + entityName + ".java";
            if (isExists(entityOutputFile)) {
                if (!configBuilder.getGlobalConfig().isOverrideExistFile()) {
                    throw new TswineJdbcException(String.format("实体文件已经存在,全局配置不允许覆盖存在文件,%s", entityOutputFile));
                }
            }
            //收集实体对象额外需要导入的包
            Set<String> entityImportPackages = new HashSet<>();
            KV<String, String> entityAnnotationClass = configBuilder.getStrategyConfig().configEntity().getAnnotationClass();
            if (entityAnnotationClass != null) {
                paramsMap.put("entityAnnotationClass", entityAnnotationClass.getKey());
                if (entityAnnotationClass.getValue() != null) {
                    entityImportPackages.add(entityAnnotationClass.getValue());
                }
            }
            KV<String, String> entityAnnotationField = configBuilder.getStrategyConfig().configEntity().getAnnotationField();
            if (entityAnnotationField != null) {
                paramsMap.put("entityAnnotationField", entityAnnotationField.getKey());
                if (entityAnnotationField.getValue() != null) {
                    entityImportPackages.add(entityAnnotationField.getValue());
                }
            }
            KV<String, String> entityAnnotationFieldKey = configBuilder.getStrategyConfig().configEntity().getAnnotationFieldKey();
            if (entityAnnotationFieldKey != null) {
                paramsMap.put("entityAnnotationFieldKey", entityAnnotationFieldKey.getKey());
                if (entityAnnotationFieldKey.getValue() != null) {
                    entityImportPackages.add(entityAnnotationFieldKey.getValue());
                }
            }
            paramsMap.put("entityImportPackages", entityImportPackages);
            writer(configBuilder.getTemplateConfig().getEntity() + templateSuffix(), entityOutputFile, paramsMap);
        } else {
//            logger.info("配置不需要生成实体文件");
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
    public Map<String, Object> getParamsMap(ConfigBuilder configBuilder, TableInfo table) {
        Map<String, Object> paramsMap = new HashMap<>();
        //设置作者
        paramsMap.put("author", configBuilder.getGlobalConfig().getAuthor());
        paramsMap.put("parentPackage", configBuilder.getGlobalConfig().getParentPackage());
        paramsMap.put("placeholder", configBuilder.getDbQuery().dbType().getPlaceholder());
        paramsMap.put("entityName", table.getName());


        //设置日期
        paramsMap.put("swagger2", configBuilder.getGlobalConfig().isSwagger2());
        paramsMap.put("lombok", configBuilder.getGlobalConfig().isLombok());
        paramsMap.put("table", table);

        return paramsMap;
    }

    /**
     * 文件是否存在
     *
     * @param filePath
     * @return
     */
    public boolean isExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }

}
