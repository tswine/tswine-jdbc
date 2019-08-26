package cn.tswine.jdbc.common.rules;

/**
 * object 类型转换器
 *
 * @Author: silly
 * @Date: 2019/8/26 14:51
 * @Version 1.0
 * @Desc
 */
public interface ObjectConvert<T> {
    T convert(Object object);
}
