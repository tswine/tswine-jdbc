package cn.tswine.jdbc.test.generator;

import cn.tswine.jdbc.generator.config.pojo.Table;

import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/8/8 18:38
 * @Version 1.0
 * @Desc
 */
public class BaseCodeGeneratorTest {

    protected void printTable(List<Table> tableList) {
        tableList.forEach(t -> {
            System.out.println(t.toString());
            t.getTableFields().forEach(f -> {
                System.out.println("    " + f.toString());
            });
        });
    }
}
