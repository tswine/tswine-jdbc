package cn.tswine.jdbc.common.enums;

/**
 * 主键类型
 *
 * @Author: silly
 * @Date: 2019/8/20 21:32
 * @Version 1.0
 * @Desc
 */
public enum IdType {

    AUTO(0, "数据库ID自增"),
    INPUT(1, "用户输入ID"),
    ID_WORKER(2, "全局唯一ID"),
    UUID(3, "全局唯一ID"),
    NONE(4, "该类型为未设置主键类型");

    private final int key;
    private final String desc;

    IdType(int key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
