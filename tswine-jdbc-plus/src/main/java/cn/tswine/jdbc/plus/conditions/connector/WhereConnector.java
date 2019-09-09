package cn.tswine.jdbc.plus.conditions.connector;

import cn.tswine.jdbc.plus.conditions.IConnector;
import cn.tswine.jdbc.plus.conditions.ISqlSegment;
import lombok.Data;

/**
 * 操作连接器
 *
 * @Author: silly
 * @Date: 2019/9/9 21:11
 * @Version 1.0
 * @Desc
 */
@Data
public class WhereConnector implements IConnector {
    /**
     * 参数
     */
    Object[] params;

    /**
     * 列
     */
    String[] columns;

    /**
     * 操作类型
     */
    ISqlSegment operator;

    public WhereConnector() {
    }

    public WhereConnector(ISqlSegment operator) {
        this();
        this.operator = operator;
    }

    public WhereConnector(ISqlSegment operator, String[] columns, Object[] params) {
        this(operator);
        this.params = params;
        this.columns = columns;
    }
}
