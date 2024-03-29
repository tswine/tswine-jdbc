package cn.tswine.jdbc.plus.conditions.segments;

import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.plus.conditions.ISqlSegment;
import cn.tswine.jdbc.plus.conditions.OrderBy;
import cn.tswine.jdbc.plus.conditions.connector.LogicType;
import cn.tswine.jdbc.plus.conditions.connector.WhereConnector;
import cn.tswine.jdbc.plus.conditions.connector.WhereType;

import java.util.ArrayList;

/**
 * 所有查询条件sql合集
 *
 * @Author: silly
 * @Date: 2019/8/16 16:19
 * @Version 1.0
 * @Desc
 */
public class MergeSegments {


    /**
     * where条件连接器
     */
    private final ArrayList<WhereConnector> whereConnector = new ArrayList<>();

    /**
     * orderBy
     */
    private final ArrayList<OrderBy> orderBy = new ArrayList<>();

    /**
     * 最近一次连接器类型
     */
    private ISqlSegment lastConnector = null;

    /**
     * 添加条件连接器
     *
     * @param connector 连接器
     */
    public void add(WhereConnector connector) {
        Assert.isNotNull(connector, "connector is not null");
        if (lastConnector != null) {
            ISqlSegment operator = connector.getOperator();
            if (operator instanceof WhereType) {
                if (lastConnector instanceof WhereType) {
                    //添加默认逻辑连接器：and
                    addAnd();
                }
            }
        }
        whereConnector.add(connector);
        lastConnector = connector.getOperator();
    }

    /**
     * 添加and
     */
    private void addAnd() {
        WhereConnector connector = new WhereConnector(LogicType.AND);
        whereConnector.add(connector);
        lastConnector = LogicType.AND;
    }

    /**
     * 排序
     *
     * @param sql
     */
    public void addOrderBy(OrderBy sql) {
        orderBy.add(sql);
    }


    public ArrayList<WhereConnector> getWhereConnector() {
        return whereConnector;
    }


    public ArrayList<OrderBy> getOrderBy() {
        return orderBy;
    }

}
