package cn.tswine.jdbc.common.toolkit;

import java.util.HashMap;
import java.util.Map;

/**
 * map工具类
 *
 * @Author: silly
 * @Date: 2019/8/23 17:24
 * @Version 1.0
 * @Desc
 */
public class MapUtils {

    public static boolean isEmpty(Map<?, ?> map) {
        if (map == null || map.size() <= 0) {
            return true;
        }
        return false;
    }


    /**
     * 不能为空
     *
     * @param map
     * @param error
     */
    public static void isNotEmpty(Map<?, ?> map, String error) {
        if (map == null || map.size() <= 0) {
            throw ExceptionUtils.tse(error);
        }
    }

    /**
     * 深度拷贝
     *
     * @param data
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> copy(Map<K, V> data) {
        Map<K, V> newMap = new HashMap<>();
        newMap.putAll(data);
        return newMap;
    }
}
