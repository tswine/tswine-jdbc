package cn.tswine.jdbc.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 模板配置
 *
 * @Author: silly
 * @Date: 2019/8/9 21:23
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class TemplateConfig {

    private String entity = "/templates/entity.java";
    private String mapper = "/templates/mapper.xml";
    private String mapperInterface = "/templates/mapper.interface.java";
}
