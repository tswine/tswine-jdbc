package cn.tswine.jdbc.common.vo;

import lombok.Data;

/**
 * 键值对
 *
 * @Author: silly
 * @Date: 2019/8/8 20:34
 * @Version 1.0
 * @Desc
 */
@Data
public class KV<K, V> {

    /**
     * 键
     */
    K key;
    /**
     * 值
     */
    V value;

    public KV() {

    }

    public KV(K key, V value) {
        this.key = key;
        this.value = value;
    }
}