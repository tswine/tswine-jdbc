package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.pojo.EntityInfo;
import lombok.Getter;

import java.io.File;
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
        File dir = new File(outputDir);
        if (!dir.exists()) {
            //递归创建文件目录
            boolean flag = dir.mkdirs();
            if (!flag) {
                throw new TswineJdbcException(String.format("AbstractTemplateEngine->batchGenerator创建目录失败,dir:%s", outputDir));
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

            }
        } catch (Exception e) {
            throw new TswineJdbcException("AbstractTemplateEngine->batchGenerator", e);
        }
        return this;
    }

}
