package cn.tswine.jdbc.generator.config.pojo;

import cn.tswine.jdbc.generator.config.rules.IColumnType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 实体信息
 *
 * @Author: silly
 * @Date: 2019/8/7 16:08
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class EntityInfo {
    /**
     * 导入的包名
     */
    private final Set<String> importPackages = new HashSet<>();
    /**
     * 表名
     */
    private String tableName;
    /**
     * 注解
     */
    private String comment;
    /**
     * 名称
     */
    private String name;
    /**
     * 是否为生成字段常量
     */
    private boolean isColumnConstant;

    /**
     * 是否采用lombok注解
     */
    private boolean isLombok;


    /**
     * 列字段
     */
    private List<TableField> tableFields;

    /**
     * 设置表字段
     *
     * @param tableFields
     * @return
     */
    public EntityInfo setTableFields(List<TableField> tableFields) {
        if (tableFields != null && tableFields.size() > 0) {
            this.tableFields = tableFields;
            //设置字段需要导入的包信息
            for (TableField field : tableFields) {
                IColumnType columnType = field.getFieldType();
                if (null != columnType && null != columnType.getPackageName()) {
                    importPackages.add(columnType.getPackageName());
                    //TODO 自定义注解方式
                }
            }
        }
        return this;
    }


}
