package cn.tswine.jdbc.common.rules;

/**
 * 字段类型
 *
 * @Author: silly
 * @Date: 2019/8/7 12:38
 * @Version 1.0
 * @Desc
 */
public interface IFieldType {

    /**
     * 字段类型名称
     */
    String name();

    /**
     * 获取字段类型
     *
     * @return
     */
    Class<?> type();


    /**
     * 获取字段类型对应的包名
     *
     * @return
     */
    String packages();

    /**
     * 获取转换器
     *
     * @return
     */
    ObjectConvert convert();
}
