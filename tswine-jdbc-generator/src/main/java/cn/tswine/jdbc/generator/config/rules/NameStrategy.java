package cn.tswine.jdbc.generator.config.rules;

import cn.tswine.jdbc.common.utils.StringUtils;

/**
 * 命名策略
 *
 * @Author: silly
 * @Date: 2019/8/7 15:08
 * @Version 1.0
 * @Desc
 */
public enum NameStrategy {

    /**
     * 不做任何改变，原样输出
     */
    NO_CHANGE,
    /**
     * 下划线转驼峰命名
     */
    UNDERLINE_TO_CAMEL,
    /**
     * 大驼峰:相比UNDERLINE_TO_CAMEL，首字母也大写，用于类命令
     */
    BIG_CAMEL_CASE;

    /**
     * 命名转换
     *
     * @param str          需要转换的字符串
     * @param nameStrategy 命名策略
     * @return
     */
    public static String changeNameStrategy(String str, NameStrategy nameStrategy) {
        switch (nameStrategy) {
            case UNDERLINE_TO_CAMEL:
                str = StringUtils.underlineToCamelCase(str);
                break;
            case BIG_CAMEL_CASE:
                str = StringUtils.bigCamelCase(str);
                break;
            default:
                break;
        }
        return str;
    }


}
