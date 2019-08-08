package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.generator.config.rules.IColumnType;

/**
 * 数据库字段类型转换接口
 *
 * @Author: silly
 * @Date: 2019/8/7 12:43
 * @Version 1.0
 * @Desc
 */
public interface ITypeConvert {

    /**
     * 执行类型转换
     *
     * @param globalConfig 全局配置
     * @param columnType   列类型
     * @return
     */
    IColumnType execute(GlobalConfig globalConfig, String columnType);

}
