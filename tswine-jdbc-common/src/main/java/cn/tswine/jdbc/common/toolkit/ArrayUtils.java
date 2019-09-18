package cn.tswine.jdbc.common.toolkit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 数组工具类
 *
 * @Author: silly
 * @Date: 2019/8/16 12:40
 * @Version 1.0
 * @Desc
 */
public class ArrayUtils {
    private ArrayUtils() {
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return 数组对象为null或者长度为 0 时，返回 false
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 判断数组是否不为空
     *
     * @param array
     * @return 数组对象内含有任意对象时返回 true
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }


    /**
     * 转成list
     *
     * @param array
     * @return
     */
    public static <T> ArrayList<T> asList(T... array) {
        if (array == null) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(array));
    }
    /**
     * 将target数组添加到source中
     *
     * @param source 源
     * @param target 目标
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T[] add(T[] source, T[] target, Class<T> tClass) {
        if (isEmpty(source) || isEmpty(target)) {
            return source;
        }
        T[] newArr = (T[]) Array.newInstance(tClass, source.length + target.length);
        System.arraycopy(source, 0, newArr, 0, source.length);
        for (int i = 0; i < target.length; i++) {
            newArr[source.length + i] = target[i];
        }
        return newArr;
    }


}
