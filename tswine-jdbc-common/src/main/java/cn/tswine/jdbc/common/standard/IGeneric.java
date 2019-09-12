package cn.tswine.jdbc.common.standard;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型接口，用于获取泛型的clazz类型
 *
 * @Author: silly
 * @Date: 2019/9/12 16:28
 * @Version 1.0
 * @Desc
 */
public interface IGeneric<T> {

    /**
     * 实体对象类型
     */
    default Class<T> clazz() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type clazz = pType.getActualTypeArguments()[0];
            if (clazz instanceof Class) {
                return (Class<T>) clazz;
            }
        }
        return null;
    }
}
