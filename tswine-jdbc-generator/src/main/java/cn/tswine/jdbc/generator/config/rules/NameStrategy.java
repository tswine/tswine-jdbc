package cn.tswine.jdbc.generator.config.rules;

import cn.tswine.jdbc.common.utils.StringUtils;

import java.util.Arrays;

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
    UNDERLINE_TO_CAMEL;


    /**
     * 下划线驼峰命令转换
     *
     * @param name
     * @return
     */
    public static String underlineToCamel(String name) {
        if (StringUtils.isEmpty(name)) {
            return "";
        }
        //将所有字母转为小写
        String tmpName = name.toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] camels = tmpName.split("_");
        Arrays.stream(camels).filter(camel -> !StringUtils.isEmpty(camel)).forEach(camel -> {
            if (sb.length() == 0) {
                //第一个片段，首字母小写
                sb.append(camel);
            } else {
                //首字母大写
                sb.append(StringUtils.capitalFirst(camel));
            }
        });
        return sb.toString();
    }
}
