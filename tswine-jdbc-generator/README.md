添加maven依赖
--
```xml
  <dependency>
    <groupId>cn.tswine.jdbc</groupId>
    <artifactId>tswine-jdbc-generator</artifactId>
    <version>${version}</version>
  </dependency>
```

配置参数
--
####全局配置 GlobalConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|outputDir|  String  | 生成文件存放路径| |
|dateConvertType |  DateConvertType|时间类型转换策略| DateConvertType.TIME_DATE|
|overrideExistFile | boolean |  是否覆盖已存在的文件 | true |
|author |String|开发人员||
|swagger2|boolean|swagger2注解|false|
|lombok|boolean|lombok注解|false|
|parentPackage|String|父包名|default|
|includeTables|String[]|包含的表|null|
|excludeTables|String[]|排除的表|null|
|excludeFields|String[]|排除的字段|null|
|skipView|boolean|是否跳过视图|true|

####数据源配置 DataSourceConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|dbQuery|IDbQuery |数据库查询语句 |null|
|schemaName|String|模式名|null|
|dbType|DbType|数据库类型|null|
|typeConvert|ITypeConvert|字段类型转换器|null|
|url|String|驱动连接的URL|null|
|driverName|String|驱动名称|null|
|username|String|用户名|null|
|password|String|密码|null|

####模板配置 TemplateConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|entity|  String  |实体模板路径|/templates/entity.java |
|mapper|  String  |mappeer模板路径 |/templates/mapper.xml |
|mapperInterface|  String|mapper接口模板路径 |/templates/mapper.interface.java |

####生成策略配置 StrategyConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|entityConfig|EntityConfig|实体配置| |
|mapperConfig|MapperConfig|mapper配置| |

##### StrategyConfig.EntityConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|generator|boolean|是否生成文件|true|
|packageName|String|实体包名|entity|
|classNameStrategy|NameStrategy|实体类名命名策略| NameStrategy.BIG_CAMEL_CASE|
|fieldNameStrategy|NameStrategy|字段命名策略|NameStrategy.UNDERLINE_TO_CAMEL|
|columnConstant|boolean|是否生成字段常量|true|
|tableConstant|boolean|是否表名生成常量|true|
|annotationClass| KV<String, String>|类注解|null|
|annotationField| KV<String, String>|字段注解|null|
|annotationFieldKey| KV<String, String>|主键字段注解|null|
|removePrefix|String[]|去除前缀|null|
|addPrefix|String|添加前缀|null|
|addSuffix|String|添加后缀|null|

##### StrategyConfig.EntityConfig
| 参数|类型 |描述 |默认值|  
| :---:|:---:|:---:|:----:|
|generator|boolean|是否生成文件|true|
|packageName|String|mapper接口包名|mapper|
|xmlPath|String|xml存放的路径|xml|
|interfaceSuffix|String|接口后缀|Mapper|
|annotationClass|KV<String, String>|类注解|null|


代码样例
--
```java
public class CodeGeneratorMySQLTest  {

    DataSourceConfig dsConfig = null;

    CodeGenerator codeGenerator = null;

    @Before
    public void init() {
        //数据源配置
        dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL);
        dsConfig.setUrl("jdbc:mysql://192.168.47.100:3306/tswine_boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        dsConfig.setUsername("tswine_boot");
        dsConfig.setPassword("123456");
        dsConfig.setDriverName("com.mysql.jdbc.Driver");

        codeGenerator = new CodeGenerator();
        codeGenerator.setDataSourceConfig(dsConfig);
    }

    private StrategyConfig strategyConfig() {
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.configEntity()
                .setTableConstant(true)
                .setColumnConstant(true)
                .setPackageName("model")
//                .setRemovePrefix(new String[]{"tdms", "sys"})
//                .setAddPrefix("Test")
//                .setAddSuffix("Suffix")
                .setAnnotationClass(new KV<>("@TableName(value = \"" + ConstValue.GenerqatorPlaceholder.TABLE_NAME + "\")", TableName.class.getName()))
                .setAnnotationField(new KV<>("@TableField(value = \"" + ConstValue.GenerqatorPlaceholder.TABLE_FIELD + "\")", TableField.class.getName()))
                .setAnnotationFieldKey(new KV<>("@TableId(value = \"" + ConstValue.GenerqatorPlaceholder.TABLE_FIELD + "\")", TableId.class.getName()));
        return strategyConfig;
    }

    private GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("silly")
                .setOutputDir("E:\\workspace\\tswine\\tswine-jdbc\\tswine-jdbc-test\\src\\main\\java\\cn\\tswine\\jdbc\\test\\generator")
                .setParentPackage("cn.tswine.jdbc.test.generator")
                .setLombok(true)
                .setSwagger2(true)
                .setOverrideExistFile(true);
//        globalConfig.setExcludeFields(new String[]{"delete_time","create_time"});
//        globalConfig.setIncludeTables(new String[]{"sys_menu"});
//        globalConfig.setExcludeTables(new String[]{"sys_menu"});
        return globalConfig;
    }

    @Test
    public void execute() {
        codeGenerator.setStrategyConfig(strategyConfig());
        codeGenerator.setGlobalConfig(globalConfig());
        codeGenerator.execute();
    }
}
```


文件样例
--
- 表结构
```sql
CREATE TABLE `tdms_define_host` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `host_name` varchar(32) NOT NULL COMMENT '主机名',
  `alias` varchar(32) NOT NULL COMMENT '别名',
  `ip_address` varchar(32) NOT NULL COMMENT '监控的主机IP',
  `group_id` varchar(32) DEFAULT NULL COMMENT '所属组ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`,`host_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主机定义'
```
- entity文件
```java
package cn.tswine.jdbc.test.generator.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 主机定义
 * </p>
 *
 * @Author silly
 * @Date 2019-08-13
 * @Desc
 */
@ApiModel(value = "TdmsDefineHost对象", description = "主机定义")
@Data
@Accessors(chain = true)
@TableName(value = "tdms_define_host")
public class TdmsDefineHost {

    public static final String TABLE_NAME = "tdms_define_host";

    public static final String FIELD_ID = "id";
    public static final String FIELD_HOST_NAME = "host_name";
    public static final String FIELD_ALIAS = "alias";
    public static final String FIELD_IP_ADDRESS = "ip_address";
    public static final String FIELD_GROUP_ID = "group_id";
    public static final String FIELD_CREATE_TIME = "create_time";

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id")
    private String id;
    /**
     * 主机名
     */
    @ApiModelProperty(value = "主机名")
    @TableId(value = "host_name")
    private String hostName;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField(value = "alias")
    private String alias;
    /**
     * 监控的主机IP
     */
    @ApiModelProperty(value = "监控的主机IP")
    @TableField(value = "ip_address")
    private String ipAddress;
    /**
     * 所属组ID
     */
    @ApiModelProperty(value = "所属组ID")
    @TableField(value = "group_id")
    private String groupId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

}
```

- mapper接口文件
```java
package cn.tswine.jdbc.test.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.tswine.jdbc.test.generator.model.TdmsDefineHost;

/**
 * <p>
 * 主机定义 mapper接口
 * </p>
 *
 * @Author silly
 * @Date 2019-08-13
 * @Desc
 */
@Mapper
public interface TdmsDefineHostMapper {

    /**
     * 插入
     *
     * @param tdmsDefineHost 主机定义
     * @return
     */
    int insert(@Param("tdmsDefineHost") TdmsDefineHost tdmsDefineHost);

    /**
     * 通过主键查找
     *
     * @param id id 主键
     * @param hostName hostName 主机名
     * @return
     */
    TdmsDefineHost selectByPrimaryKey(@Param("id") String id,@Param("hostName") String hostName);

    /**
     * 通过主键删除
     *
     * @param id id 主键
     * @param hostName hostName 主机名
     * @return
     */
    int deleteByPrimaryKey(@Param("id") String id,@Param("hostName") String hostName);

    /**
     * 通过主键更新
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(TdmsDefineHost tdmsDefineHost);


}
```

- mapper文件
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="cn.tswine.jdbc.test.generator.mapper.TdmsDefineHostMapper">
    <resultMap id="BaseResultMap" type="cn.tswine.jdbc.test.generator.model.TdmsDefineHost">
        <id column="id" property="id"/>
        <id column="host_name" property="hostName"/>
        <result column="alias" property="alias"/>
        <result column="ip_address" property="ipAddress"/>
        <result column="group_id" property="groupId"/>
        <result column="create_time" property="createTime"/>
    </resultMap>
    <sql id="columns">`id`,`host_name`,`alias`,`ip_address`,`group_id`,`create_time`</sql>

     <insert id="insert" parameterType="cn.tswine.jdbc.test.generator.model.TdmsDefineHost">
        insert into tdms_define_host
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="tdmsDefineHost.id != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
            <if test="tdmsDefineHost.hostName != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
            <if test="tdmsDefineHost.alias != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
            <if test="tdmsDefineHost.ipAddress != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
            <if test="tdmsDefineHost.groupId != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
            <if test="tdmsDefineHost.createTime != null">
                strutil.replace(placeholder,"%s",field.columnName),
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="tdmsDefineHost.id != null">
                #{tdmsDefineHost.id},
            </if>
            <if test="tdmsDefineHost.hostName != null">
                #{tdmsDefineHost.hostName},
            </if>
            <if test="tdmsDefineHost.alias != null">
                #{tdmsDefineHost.alias},
            </if>
            <if test="tdmsDefineHost.ipAddress != null">
                #{tdmsDefineHost.ipAddress},
            </if>
            <if test="tdmsDefineHost.groupId != null">
                #{tdmsDefineHost.groupId},
            </if>
            <if test="tdmsDefineHost.createTime != null">
                #{tdmsDefineHost.createTime},
            </if>
        </trim>
     </insert>

     <select id="selectByPrimaryKey" parameterType="map" resultMap="BaseResultMap">
        select
        <include refid="columns" />
        from tdms_define_host
        where  id = #{id} and host_name = #{hostName} 
     </select>

     <delete id="deleteByPrimaryKey" parameterType="map">
        delete from tdms_define_host
        where  id = #{id} and host_name = #{hostName} 
     </delete>

     <update id="updateByPrimaryKey" parameterType="cn.tswine.jdbc.test.generator.model.TdmsDefineHost">
        update tdms_define_host
        <set>
            <if test="id != null">
                id = #{id},
            </if>
            <if test="hostName != null">
                host_name = #{hostName},
            </if>
            <if test="alias != null">
                alias = #{alias},
            </if>
            <if test="ipAddress != null">
                ip_address = #{ipAddress},
            </if>
            <if test="groupId != null">
                group_id = #{groupId},
            </if>
            <if test="createTime != null">
                create_time = #{createTime},
            </if>
        </set>
        where  id = #{id} and host_name = #{hostName} 
     </update>
</mapper>
```






