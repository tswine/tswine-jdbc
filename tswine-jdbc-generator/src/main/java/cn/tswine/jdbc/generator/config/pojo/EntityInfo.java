package cn.tswine.jdbc.generator.config.pojo;


import cn.tswine.jdbc.common.vo.KV;
import cn.tswine.jdbc.generator.config.StrategyConfig;
import cn.tswine.jdbc.generator.config.rules.NameStrategy;
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
    private boolean columnConstant;
    /**
     * 是否为表名生成字段常量
     */
    private boolean tableConstant;

    /**
     * 是否采用lombok注解
     */
    private boolean lombok;

    /**
     * 列字段
     */
    //TODO 不让lombok生成set方法
    private List<TableField> tableFields;

    /**
     * 头部注解
     */
    private List<KV<String, String>> classAnnotations;
    /**
     * 字段注解：针对所有字段生效
     */
    private List<KV<String, String>> fieldAnnotations;

    /**
     * 构造函数
     *
     * @param table          表信息
     * @param strategyConfig 策略配置
     */
    public EntityInfo(Table table, StrategyConfig strategyConfig) {
        this.columnConstant = strategyConfig.isEntityColumnConstant();
        this.tableConstant = strategyConfig.isEntityTableConstant();
        this.lombok = strategyConfig.isEntityLombok();
        this.comment = table.getComment();
        tableName = table.getName();
        name = NameStrategy.changeNameStrategy(tableName, strategyConfig.getEntityClassNameStrategy());
        importPackages.addAll(table.getImportPackages());
        classAnnotations = strategyConfig.getEntityClassAnnotations();
        fieldAnnotations = strategyConfig.getEntityFieldAnnotations();
        handleTableField(table.getTableFields(), strategyConfig);
    }


    /**
     * 处理表字段
     *
     * @param tableFields    表字段
     * @param strategyConfig 生成策略配置
     */
    private void handleTableField(List<TableField> tableFields, StrategyConfig strategyConfig) {
        NameStrategy entityFieldNameStrategy = strategyConfig.getEntityFieldNameStrategy();
        if (tableFields != null && tableFields.size() > 0) {
            for (TableField tableField : tableFields) {
                tableField.setFieldName(NameStrategy.changeNameStrategy(tableField.getColumnName(), entityFieldNameStrategy));
            }
        }
        this.tableFields = tableFields;
        importAnnotationPackages(classAnnotations);
        importAnnotationPackages(fieldAnnotations);
    }

    /**
     * 导入注解需要导入的包
     *
     * @param kvs 注解
     */
    private void importAnnotationPackages(List<KV<String, String>> kvs) {
        if (kvs != null && kvs.size() > 0) {
            for (KV<String, String> kv : kvs) {
                if (kv.getValue() != null && (!importPackages.contains(kv.getValue()))) {
                    importPackages.add(kv.getValue());
                }
            }
        }
    }
}
