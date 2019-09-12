package cn.tswine.jdbc.plus.conditions;

import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.common.toolkit.sql.SqlUtils;
import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.conditions.connector.LogicType;
import cn.tswine.jdbc.plus.conditions.connector.WhereConnector;
import cn.tswine.jdbc.plus.conditions.connector.WhereType;
import cn.tswine.jdbc.plus.conditions.interfaces.Compare;
import cn.tswine.jdbc.plus.conditions.interfaces.Func;
import cn.tswine.jdbc.plus.conditions.interfaces.Logic;
import cn.tswine.jdbc.plus.conditions.segments.MergeSegments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: silly
 * @Date: 2019/8/16 16:10
 * @Version 1.0
 * @Desc
 */
public class AbstractWrapper<Children extends AbstractWrapper<Children>>
        implements Wrapper, Compare<Children>, Func<Children>, Logic<Children> {
    protected final Children _this = (Children) this;
    /**
     * 数据库表映射实体类
     */
    protected EntitySchema entitySchema;
    protected MergeSegments expression;


    private final List<Object> whereParams = new ArrayList<>();

    public AbstractWrapper() {
        expression = new MergeSegments();
    }

    @Override
    public void setEntitySchema(EntitySchema entitySchema) {
        this.entitySchema = entitySchema;
    }


    @Override
    public Children eq(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.EQ, new Object[]{param});
    }

    @Override
    public Children nq(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.NQ, new Object[]{param});
    }

    @Override
    public Children lt(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LT, new Object[]{param});
    }

    @Override
    public Children le(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LE, new Object[]{param});
    }

    @Override
    public Children gt(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.GT, new Object[]{param});
    }

    @Override
    public Children ge(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.GE, new Object[]{param});
    }

    @Override
    public Children like(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE, new Object[]{param});
    }

    @Override
    public Children likeLeft(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_LEFT, new Object[]{param});
    }

    @Override
    public Children likeRight(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_RIGHT, new Object[]{param});
    }

    @Override
    public Children notLike(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_NOT, new Object[]{param});
    }

    @Override
    public Children notLikeLeft(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_LEFT_NOT, new Object[]{param});
    }

    @Override
    public Children notLikeRight(String column, Object param) {
        return addWhereConnector(new String[]{column}, WhereType.LIKE_RIGHT_NOT, new Object[]{param});
    }

    @Override
    public Children between(String column, Object value1, Object value2) {
        return addWhereConnector(new String[]{column}, WhereType.BETWEEN, new Object[]{value1, value2});
    }

    @Override
    public Children notBetween(String column, Object value1, Object value2) {
        return addWhereConnector(new String[]{column}, WhereType.BETWEEN_NOT, new Object[]{value1, value2});

    }

    @Override
    public Children isNull(String column) {
        return addWhereConnector(new String[]{column}, WhereType.IS_NULL, null);
    }

    @Override
    public Children isNotNull(String column) {
        return addWhereConnector(new String[]{column}, WhereType.IS_NOT_NULL, null);
    }

    @Override
    public Children in(String column, Object[] params) {
        return addWhereConnector(new String[]{column}, WhereType.IN, params);
    }

    @Override
    public Children notIn(String column, Object[] params) {
        return addWhereConnector(new String[]{column}, WhereType.IN_NOT, params);
    }

    @Override
    public Children and() {
        return addLogicConnector(LogicType.AND);
    }

    @Override
    public Children or() {
        return addLogicConnector(LogicType.OR);
    }

    /**
     * 添加条件连接器
     *
     * @param columns 列名
     * @param type    类型
     * @param params  参数
     * @return
     */
    private Children addWhereConnector(String[] columns, WhereType type, Object[] params) {
        WhereConnector connector = new WhereConnector(type, columns, params);
        expression.add(connector);
        return _this;
    }

    /**
     * 条件条件逻辑构造器
     *
     * @param logicType
     * @return
     */
    private Children addLogicConnector(LogicType logicType) {
        WhereConnector connector = new WhereConnector(logicType);
        expression.add(connector);
        return _this;
    }

    @Override
    public String getSqlSegment() {
        //清空参数列表：防止该函数被多次调用参数列表被迭代
        whereParams.clear();
        StringBuilder sb = new StringBuilder();
        //拼接where条件
        ArrayList<WhereConnector> whereConnectors = expression.getWhereConnector();
        if (whereConnectors != null && whereConnectors.size() > 0) {
            sb.append(SQLSentenceType.WHERE);
            sb.append(StringPool.SPACE);
            for (WhereConnector whereConnector : whereConnectors) {
                ISqlSegment operator = whereConnector.getOperator();
                if (operator instanceof LogicType) {
                    LogicType logicType = (LogicType) operator;
                    sb.append(logicType.getSqlSegment());
                } else if (operator instanceof WhereType) {
                    WhereType whereType = (WhereType) operator;
                    String[] columns = whereConnector.getColumns();
                    //将{}替换成column
                    if (whereType.getType().equalsIgnoreCase(StringPool.IN)) {
                        //处理in
                        //获取列的?占位符: ?,?,?
                        int paramSize = whereConnector.getParams().length;
                        String questionMark = SqlUtils.getQuestionMark(paramSize);
                        columns = ArrayUtils.add(columns, new String[]{questionMark});
                    } else if (whereType.getType().equalsIgnoreCase(StringPool.LIKE)) {
                        //处理like
                        //{0} NOT LIKE '%{1}'
                        Object[] params = whereConnector.getParams();
                        if (params == null || params.length < 0) {
                            throw new TswineJdbcException("like条件语句参数不能为空");
                        }
                        String param = String.valueOf(params[0]);
                        columns = ArrayUtils.add(columns, new String[]{param});
                        whereConnector.setParams(null);
                    }
                    String where = StringUtils.formatBrackets(whereType.getSqlSegment(), columns);
                    sb.append(where);
                }
                Object[] params = whereConnector.getParams();
                if (ArrayUtils.isNotEmpty(params)) {
                    whereParams.addAll(Arrays.asList(params));
                }
            }
        }

        return sb.toString().trim();
    }

    public Object[] getParams() {
        return whereParams.toArray();
    }


}
