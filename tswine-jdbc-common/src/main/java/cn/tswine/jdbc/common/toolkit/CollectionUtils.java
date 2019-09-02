package cn.tswine.jdbc.common.toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 集合工具类
 *
 * @Author: silly
 * @Date: 2019/8/16 12:58
 * @Version 1.0
 * @Desc
 */
public class CollectionUtils {

    /**
     * 校验集合是否为空
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null || coll.isEmpty());
    }

    /**
     * 校验集合是否不为空
     *
     * @param coll 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * 判断Map是否为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 判断Map是否不为空
     *
     * @param map 入参
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }


    /**
     * 转换为list
     *
     * @param coll
     * @return
     */
    public static <T> List<T> asList(Collection<T> coll) {
        List<T> list = new ArrayList(coll);
        return list;
    }

    public static <T> void add(Collection<T> params, T[] value) {
        Assert.isNotNull(params, "params is null");
        for (T obj : value) {
            params.add(obj);
        }
    }
}
