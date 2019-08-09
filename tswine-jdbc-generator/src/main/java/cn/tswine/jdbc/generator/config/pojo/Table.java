package cn.tswine.jdbc.generator.config.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 表信息
 *
 * @Author: silly
 * @Date: 2019/8/7 15:59
 * @Version 1.0
 * @Desc
 */
@Data
public class Table {

    /**
     * 导入的包名
     */
    private final Set<String> importPackages = new HashSet<>();

    /**
     * 表名
     */
    private String tableName;
    /**
     * 名称
     */
    private String name;
    /**
     * 注解名
     */
    private String comment;

    /**
     * 列字段
     */
    private List<TableField> tableFields;


    public Table setTableFields(List<TableField> tableFields) {
        //收集需要导入的包
        if (tableFields != null && tableFields.size() > 0) {
            tableFields.forEach(k -> {
                if (null != k.getFieldType()) {
                    String packageName = k.getFieldType().getPackageName();
                    if ((null != packageName) && (!importPackages.contains(packageName))) {
                        importPackages.add(packageName);
                    }
                }
            });
        }
        this.tableFields = tableFields;
        return this;
    }
}
