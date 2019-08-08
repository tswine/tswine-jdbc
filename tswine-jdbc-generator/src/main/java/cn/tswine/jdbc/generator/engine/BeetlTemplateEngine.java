package cn.tswine.jdbc.generator.engine;

import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Beetl模板引擎
 *
 * @Author: silly
 * @Date: 2019/8/7 15:43
 * @Version 1.0
 * @Desc 文档地址：http://ibeetl.com/guide/#beetl
 */
public class BeetlTemplateEngine extends AbstractTemplateEngine {

    GroupTemplate groupTemplate;

    @Override
    public AbstractTemplateEngine init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            groupTemplate = new GroupTemplate(new ClasspathResourceLoader("/"), cfg);
        } catch (IOException e) {
            throw new TswineJdbcException("BeetlTemplateEngine->init", e);
        }
        return this;
    }

    @Override
    public void writer(String templatePath, String outputFile, Map<String, Object> params) {
        Template template = groupTemplate.getTemplate(templatePath);
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            //绑定数据
            template.binding(params);
            // 渲染结果输出到OutputStream里
            template.renderTo(fos);
        } catch (IOException e) {
            throw new TswineJdbcException("BeetlTemplateEngine->writer", e);
        }

    }


}
