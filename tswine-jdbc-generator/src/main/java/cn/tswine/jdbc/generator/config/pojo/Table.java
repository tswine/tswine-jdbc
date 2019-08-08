package cn.tswine.jdbc.generator.config.pojo;

import lombok.Data;

import java.util.List;

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
     * 表名
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


}
