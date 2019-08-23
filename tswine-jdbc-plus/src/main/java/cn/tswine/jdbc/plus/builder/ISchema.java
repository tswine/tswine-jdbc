package cn.tswine.jdbc.plus.builder;

/**
 * @Author: silly
 * @Date: 2019/8/22 20:12
 * @Version 1.0
 * @Desc
 */
public interface ISchema {
    /**
     * 初始化参数
     * @param clazz
     * @param params
     */
    void build(Class<?> clazz, Object[] params);
}
