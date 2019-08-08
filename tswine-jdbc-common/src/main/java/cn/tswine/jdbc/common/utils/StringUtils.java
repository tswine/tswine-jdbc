package cn.tswine.jdbc.common.utils;

/**
 * @Author: silly
 * @Date: 2019/8/7 15:11
 * @Version 1.0
 * @Desc
 */
public class StringUtils {


    /**
     * 判断字符串是否为空
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isEmpty(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    /**
     * 首字母大写
     *
     * @param name 待转换的字符串
     * @return 转换后的字符串
     */
    public static String capitalFirst(String name) {
        if (!StringUtils.isEmpty(name)) {
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return "";
    }
}
