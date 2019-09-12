package cn.tswine.jdbc.plus.conditions;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: silly
 * @Date: 2019/8/28 15:29
 * @Version 1.0
 * @Desc
 */
public class OrderBy<R> implements ISqlSegment {

    /**
     * 列
     */
    @Getter
    @Setter
    R column;

    /**
     * 排序模式
     */
    @Getter
    @Setter
    Mode mode;


    public OrderBy(R column, Mode mode) {
        this.column = column;
        this.mode = mode;
    }

    @Override
    public String getSqlSegment() {
        String exprSql = "%s %s";
        return String.format(exprSql, column, mode);
    }


    /**
     * 排序方式
     */
    public enum Mode {
        /**
         * 从小到大
         */
        ASC,
        /**
         * 从大到小
         */
        DESC;
    }
}
