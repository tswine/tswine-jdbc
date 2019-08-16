package cn.tswine.jdbc.common.toolkit;

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


}
