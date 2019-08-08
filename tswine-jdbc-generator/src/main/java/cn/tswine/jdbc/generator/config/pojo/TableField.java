package cn.tswine.jdbc.generator.config.pojo;

import cn.tswine.jdbc.generator.config.rules.IColumnType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 表字段
 *
 * @Author: silly
 * @Date: 2019/8/7 16:02
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class TableField {
    /**
     * 是否为主键
     */
    private boolean isKey;
    /**
     * 列名
     */
    private String columnName;
    /**
     * 列注解
     */
    private String columnComment;
    /**
     * 列类型
     */
    private String columnType;
    private IColumnType fieldType;
}
