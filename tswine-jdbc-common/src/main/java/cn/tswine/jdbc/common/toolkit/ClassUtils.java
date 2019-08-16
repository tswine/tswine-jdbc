package cn.tswine.jdbc.common.toolkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassUtils
 *
 * @Author: silly
 * @Date: 2019/8/16 12:46
 * @Version 1.0
 * @Desc
 */
public class ClassUtils {
    /**
     * 根据clazz实例化对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        Assert.isNotNull(clazz, "class不能为空");
        try {
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw ExceptionUtils.tse(e, "实例化对象出错，请给%s对象添加无参的构造函数", clazz.getName());
        }
    }
}
