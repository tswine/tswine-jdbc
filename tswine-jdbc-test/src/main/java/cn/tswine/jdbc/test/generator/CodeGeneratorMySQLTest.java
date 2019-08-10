package cn.tswine.jdbc.test.generator;

import cn.tswine.jdbc.common.ConstValue;
import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import cn.tswine.jdbc.common.vo.KV;
import cn.tswine.jdbc.generator.CodeGenerator;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author: silly
 * @Date: 2019/8/8 16:46
 * @Version 1.0
 * @Desc
 */
public class CodeGeneratorMySQLTest extends BaseCodeGeneratorTest {

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
                .setRemovePrefix(new String[]{"tdms","sys"})
                .setAddPrefix("Test")
                .setAddSuffix("Suffix")
                .setAnnotationClass(new KV<>("@TableName(value=\"" + ConstValue.GenerqatorPlaceholder.TABLE_NAME + "\")", TableName.class.getName()))
                .setAnnotationField(new KV<>("@TableField(value=\"" + ConstValue.GenerqatorPlaceholder.TABLE_FIELD + "\")", TableField.class.getName()))
                .setAnnotationFieldKey(new KV<>("@TableId(value=\"" + ConstValue.GenerqatorPlaceholder.TABLE_FIELD + "\")", TableId.class.getName()));
        return strategyConfig;
    }

    private GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("silly")
                .setOutputDir("E:\\workspace\\tswine\\tswine-jdbc\\tswine-jdbc-test\\src\\main\\java\\cn\\tswine\\jdbc\\test\\generator")
                .setParentPackage("cn.tswine.jdbc.test.generator")
                .setLombok(true)
                .setOverrideExistFile(true);
//        globalConfig.setExcludeFields(new String[]{"delete_time","create_time"});
//        globalConfig.setIncludeTables(new String[]{"sys_menu"});
//        globalConfig.setExcludeTables(new String[]{"sys_menu"});
        //TODO swagger
        return globalConfig;
    }

    @Test
    public void execute() {
        codeGenerator.setStrategyConfig(strategyConfig());
        codeGenerator.setGlobalConfig(globalConfig());
        codeGenerator.execute();
    }


}
