package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.generator.config.rules.DateConvertType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局配置
 *
 * @Author: silly
 * @Date: 2019/8/7 12:49
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {

    /**
     * 生成文件的存放路径
     */
    private String outputDir;

    /**
     * 时间类型转换策略
     */
    private DateConvertType dateConvertType = DateConvertType.TIME_DATE;

    /**
     * 是否覆盖已存在的文件
     */
    private boolean overrideExistFile = true;

    /**
     * 开发人员
     */
    private String author;

    /**
     * 是否采用swagger2注解
     */
    private boolean swagger2 = false;
    /**
     * 是否采用lombok注解
     */
    private boolean lombok = false;

    /**
     * 父包名
     */
    private String parentPackage = "default";

    /**
     * 包含的表
     */
    private String[] includeTables = null;

    /**
     * 排除的表
     */
    private String[] excludeTables = null;
    /**
     * 排除的字段
     */
    private String[] excludeFields = null;

    /**
     * 是否跳过视图
     */
    private boolean skipView = true;
}
