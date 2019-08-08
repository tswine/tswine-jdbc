package cn.tswine.jdbc.common.utils;

import java.util.Arrays;

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
     * 字符串包含
     *
     * @param sourceStr    源字符串
     * @param subStr       子字符串
     * @param isIgnoreCase 是否忽略
     * @return
     */
    public static boolean contains(String sourceStr, String subStr, boolean isIgnoreCase) {
        if (isEmpty(sourceStr) || isEmpty(subStr)) {
            return false;
        }
        if (isIgnoreCase) {
            return sourceStr.contains(subStr);
        } else {
            return sourceStr.toLowerCase().contains(subStr.toLowerCase());
        }
    }


    /**
     *
     *
     * @param name
     * @return 转换后的字符串
     */

    /**
     * 转换字符串首字母
     *
     * @param str        待转换的字符串
     * @param capitalize 转换类型  true:大写   false:小写
     * @return
     */
    public static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        } else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }
        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars, 0, chars.length);
    }


    /**
     * 下划线驼峰命令转换
     *
     * @param str 需要转换的字符串
     * @return
     */
    public static String underlineToCamelCase(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        //将所有字母转为小写
        String tmpName = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        String[] camels = tmpName.split("_");
        Arrays.stream(camels).filter(camel -> !StringUtils.isEmpty(camel)).forEach(camel -> {
            if (sb.length() == 0) {
                //第一个片段，首字母小写
                sb.append(camel);
            } else {
                //首字母大写
                sb.append(StringUtils.changeFirstCharacterCase(camel, true));
            }
        });
        return sb.toString();
    }

    /**
     * 大驼峰命令转换
     *
     * @return
     */
    public static String bigCamelCase(String name) {
        return changeFirstCharacterCase(underlineToCamelCase(name), true);
    }
}
