package cn.tswine.jdbc.common.enums.generator;

import java.util.UUID;

/**
 * UUID生成器
 *
 * @Author: silly
 * @Date: 2019/8/26 22:12
 * @Version 1.0
 * @Desc
 */
public class UUIDGenerator implements IdTypeGenerator {
    @Override
    public String get() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
