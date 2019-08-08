package com.test;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.generator.CodeGenerator;
import cn.tswine.jdbc.generator.builder.ConfigBuilder;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.pojo.Table;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/8/7 14:56
 * @Version 1.0
 * @Desc
 */
public class Demo {

    public static void main(String[] args) {
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL);
        dsConfig.setUrl("jdbc:mysql://192.168.47.100:3306/tswine_boot?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
        dsConfig.setUsername("tswine_boot");
        dsConfig.setPassword("123456");
        dsConfig.setDriverName("com.mysql.jdbc.Driver");

        CodeGenerator codeGenerator = new CodeGenerator();
        ConfigBuilder configBuilder = codeGenerator.execute();
        List<Table> tableList = configBuilder.getTableList();
        Arrays.stream(tableList.toArray()).forEach(t -> {
            System.out.println(t.toString());
        });
    }
}
