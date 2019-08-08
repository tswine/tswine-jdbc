package cn.tswine.jdbc.generator.config.rules;

/**
 * 实体类字段属性信息接口
 *
 * @Author: silly
 * @Date: 2019/8/7 12:38
 * @Version 1.0
 * @Desc
 */
public interface IColumnType {

    /**
     * 获取字段类型
     *
     * @return
     */
    String getType();


    /**
     * 获取字段类型对应的包名
     *
     * @return
     */
    String getPackageName();
}
