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
