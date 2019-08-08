package cn.tswine.jdbc.test.generator;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.generator.CodeGenerator;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import cn.tswine.jdbc.generator.config.pojo.Table;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
        return strategyConfig;
    }


    @Test
    public void execute() {
        codeGenerator.setStrategyConfig(strategyConfig());
        ConfigBuilder configBuilder = codeGenerator.execute();
        List<Table> tableList = configBuilder.getTableList();
        printTable(tableList);
    }


}
