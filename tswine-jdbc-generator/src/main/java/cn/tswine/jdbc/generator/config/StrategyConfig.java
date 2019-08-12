package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.common.vo.KV;
import cn.tswine.jdbc.generator.config.rules.NameStrategy;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 策略配置
 *
 * @Author: silly
 * @Date: 2019/8/8 20:18
 * @Version 1.0
 * @Desc
 */
public class StrategyConfig {

    /**
     * 实体配置
     */
    private EntityConfig entityConfig;

    private MapperConfig mapperConfig;

    /**
     * 配置实体
     *
     * @return
     */
    public EntityConfig configEntity() {
        if (entityConfig == null) {
            this.entityConfig = new EntityConfig();
        }
        return this.entityConfig;
    }

    /**
     * 配置实体
     *
     * @return
     */
    public MapperConfig configMapper() {
        if (mapperConfig == null) {
            this.mapperConfig = new MapperConfig();
        }
        return this.mapperConfig;
    }


    /**
     * 实体相关配置
     */
    @Data
    @Accessors(chain = true)
    public class EntityConfig {

        /**
         * 是否生成实体文件
         */
        private boolean generator = true;
        /**
         * 实体包名
         */
        private String packageName = "entity";

        /**
         * 实体类名命名策略
         */
        private NameStrategy classNameStrategy = NameStrategy.BIG_CAMEL_CASE;

        /**
         * 字段命名策略
         */
        private NameStrategy fieldNameStrategy = NameStrategy.UNDERLINE_TO_CAMEL;

        /**
         * 是否为生成字段常量
         */
        private boolean columnConstant = true;
        /**
         * 是否为表名生成字段常量
         */
        private boolean tableConstant = true;
        /**
         * 类注解
         */
        private KV<String, String> annotationClass = null;
        /**
         * 字段注解
         */
        private KV<String, String> annotationField = null;
        /**
         * 主键字段注解
         */
        private KV<String, String> annotationFieldKey = null;
        /**
         * 去除前缀
         */
        private String[] removePrefix = null;
        /**
         * 添加前缀
         */
        private String addPrefix = null;
        /**
         * 添加后缀
         */
        private String addSuffix = null;
    }


    /**
     * mapper相关配置
     */
    @Data
    @Accessors(chain = true)
    public class MapperConfig {
        /**
         * 是否生成文件
         */
        private boolean generator = true;
        /**
         * mapper接口包名
         */
        private String packageName = "mapper";

        /**
         * xml存放的路径
         */
        private String xmlPath = "xml";
        /**
         * 接口后缀
         */
        private String interfaceSuffix = "Mapper";
        /**
         * 类注解
         */
        private KV<String, String> annotationClass = null;

    }


}
