package cn.tswine.jdbc.plus.dao;

import cn.tswine.jdbc.plus.conditions.Wrapper;

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
     * 查询：根据条件构造器
     *
     * @param wrapper 条件构造器
     * @return
     */
    List<T> select(Wrapper<T> wrapper);

    /**
     * 查询：根据条件构造器查询一个
     *
     * @param wrapper
     * @return
     */
    T selectOne(Wrapper<T> wrapper);

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
    T selectById(Serializable... ids);

    /**
     * 查询（根据主键批量查询；不支持多主键）
     *
     * @param idList
     * @return
     */
    List<T> selectBatchIds(Collection<? extends Serializable> idList);

    /**
     * 根据条件构造器：查询总记录
     *
     * @param wrapper
     * @return
     */
    Integer selectCount(Wrapper<T> wrapper);
    /***********删除***********/
    /**
     * 通过where条件删除
     *
     * @param tableName 表名
     * @param sqlWhere  where条件
     * @param params    参数
     * @return
     */
    int deleteByWhere(String tableName, String sqlWhere, Object[] params);

    /**
     * 删除（根据主键：支持多主键）
     *
     * @param ids
     * @return
     */
    int deleteById(Serializable... ids);

    /**
     * 删除（根据主键批量删除；不支持多主键）
     *
     * @param idList
     * @return
     */
    int deleteBatchIds(Collection<? extends Serializable> idList);


    /**
     * 删除（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(Map<String, Object> columnMap);

    /**
     * 删除
     *
     * @param wrapper 条件构造器
     * @return 删除的行数
     */
    int delete(Wrapper<T> wrapper);

    /***********更新***********/
    /**
     * 更新（根据ID）
     *
     * @param entity 实体对象
     */
    int updateById(T entity);

    /**
     * 更新（指定列和条件更新）
     *
     * @param tableName 表名
     * @param update    更新数据
     * @param where     条件数据
     * @return
     */
    int update(String tableName, Map<String, Object> update, Map<String, Object> where);
}
