package cn.tswine.jdbc.plus.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 顶级的Dao接口中扩展接口
 *
 * @Author: silly
 * @Date: 2019/8/26 18:51
 * @Version 1.0
 * @Desc
 */
public interface ExpandDao<T> {


    /***********插入***********/
    /**
     * 插入（一条记录）
     *
     * @param entity 实体对象
     * @return 插入的条数
     */
    int insert(T entity);

    /**
     * 插入（插入多条记录）
     *
     * @param listEntity 批量实体对象
     * @return 插入的条数
     */
    int[] insert(List<T> listEntity);

    /**
     * 插入
     *
     * @param tableName    表名
     * @param columnValues 列值
     * @return 插入的条数
     */
    int insert(String tableName, Map<String, Object> columnValues);

    /***********查找***********/
    /**
     * 查询（根据where条件查询）
     *
     * @param whereSql
     * @param params
     * @return
     */
    List<T> selectByWhere(String whereSql, Object... params);

    /**
     * 查询（根据columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     * @return 查询的实体集合
     */
    List<T> selectByMap(Map<String, Object> columnMap);

    /**
     * 查询（根据主键：支持多主键）
     *
     * @param ids
     * @return
     */
    T selectByIds(Serializable... ids);

    /**
     * 查询（根据主键批量查询；不支持多主键）
     *
     * @param idList
     * @return
     */
    List<T> selectBatchIds(Collection<? extends Serializable> idList);


}