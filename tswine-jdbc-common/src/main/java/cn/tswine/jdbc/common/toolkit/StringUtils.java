package cn.tswine.jdbc.common.toolkit;

import java.util.Arrays;

/**
 * @Author: silly
 * @Date: 2019/8/7 15:11
 * @Version 1.0
 * @Desc
 */
public class StringUtils {

    /**
     * 空字符
     */
    public static final String EMPTY = "";

    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';
    /**
     * 占位符
     */
    public static final String PLACE_HOLDER = "{%s}";

    /**
     * 重载join
     *
     * @param array 数组
     * @return 采用逗号连接后的字符串
     */
    public static String join(Object[] array) {
        return join(array, ", ");
    }

    /**
     * 数组连接成字符串
     *
     * @param array  数组
     * @param joiner 连接符
     * @return 连接后的字符串
     */
    public static String join(Object[] array, String joiner) {
        if (array == null) {
            return "null";
        }
        int iMax = array.length - 1;
        if (iMax == -1) {
            return "[]";
        }
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(array[i]);
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(joiner);
        }
    }

    /**
     * 格式化字符串
     *
     * @param target 目标字符串
     * @param params 参数
     * @return 格式化后的字符串
     */
    public static String format(String target, Object... params) {
        if (target.contains("%s") && ArrayUtils.isNotEmpty(params)) {
            return String.format(target, params);
        }
        return target;
    }


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
     * 判断字符串是否不为空
     *
     * @param cs 需要判断字符串
     * @return 判断结果
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
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
     * 字符串驼峰转下划线格式
     *
     * @param str 需要转换的字符串
     * @return 转换好的字符串  TabUser ==> tab_user
     */
    public static String camelToUnderline(String str) {
        if (isEmpty(str)) {
            return EMPTY;
        }
        int len = str.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append(UNDERLINE);
            }
            sb.append(Character.toLowerCase(c));
        }
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


    /**
     * <p>
     * 拼接字符串第二个字符串第一个字母大写
     * </p>
     *
     * @param concatStr
     * @param str
     * @return
     */
    public static String concatCapitalize(String concatStr, final String str) {
        if (isEmpty(concatStr)) {
            concatStr = "";
        }
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        final char firstChar = str.charAt(0);
        if (Character.isTitleCase(firstChar)) {
            return str;
        }

        StringBuilder sb = new StringBuilder(strLen);
        sb.append(concatStr);
        sb.append(Character.toTitleCase(firstChar));
        sb.append(str.substring(1));
        return sb.toString();
    }
}
