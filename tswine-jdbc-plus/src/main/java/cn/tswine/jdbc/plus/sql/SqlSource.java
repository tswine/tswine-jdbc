package cn.tswine.jdbc.plus.sql;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/23 20:17
 * @Version 1.0
 * @Desc
 */
public class SqlSource<T> {
    /**
     * 执行的sql语句
     */
    private final String sql;
    /**
     * sql参数
     */
    private final List<Object> parameters;
    /**
     * 查询结果
     */
    private List<Map<String, Object>> resultMap;

    @Getter
    @Setter
    private T result;
    @Getter
    @Setter
    private List<T> resultList;

    /**
     * 更新结果
     */
    private int resultUpdate;

    /**
     * 实体类型
     */
    @Getter
    private Class<T> classEntity;

    public void setClazzEntity() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) type;
            Type clazz = pType.getActualTypeArguments()[0];
            if (clazz instanceof Class) {
                this.classEntity = (Class<T>) clazz;
            }
        }
    }


    public SqlSource(String sql, List<Object> parameter) {
        this.sql = sql;
        this.parameters = parameter;
        this.setClazzEntity();
    }

    public SqlSource setResultSelect(List<Map<String, Object>> resultSelect) {
        this.resultMap = resultSelect;
        return this;
    }

    public int getResultUpdate() {
        return resultUpdate;
    }

    public SqlSource setResultUpdate(int resultUpdate) {
        this.resultUpdate = resultUpdate;
        return this;
    }

    public List<Map<String, Object>> getResultSelect() {
        return resultMap;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public String getSql() {
        return sql;
    }
}
