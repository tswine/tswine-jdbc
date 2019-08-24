package cn.tswine.jdbc.common.toolkit.sql;

import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.StringPool;

import java.util.List;

/**
 * sql工具类
 *
 * @Author: silly
 * @Date: 2019/8/24 11:09
 * @Version 1.0
 * @Desc
 */
public class SqlUtils implements StringPool {


    /**
     * 获取where条件 sql
     *
     * @param columns 条件列
     * @return user_name = ? AND password = ?
     */
    public static String getWhere(List<String> columns) {
        Assert.isNotNull(columns, "条件列不能为空");
        int size = columns.size() - 1;
        if (size == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; ; i++) {
            sb.append(columns.get(i));
            sb.append(SPACE);
            sb.append(EQUALS);
            sb.append(SPACE);
            sb.append(QUESTION_MARK);
            sb.append(SPACE);
            if (i == size) {
                return sb.toString();
            }
            sb.append(SPACE);
            sb.append(SQLSentenceType.AND);
            sb.append(SPACE);
        }
    }

}
