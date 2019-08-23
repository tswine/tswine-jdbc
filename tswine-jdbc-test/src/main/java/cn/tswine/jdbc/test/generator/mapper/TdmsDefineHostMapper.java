package cn.tswine.jdbc.test.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.tswine.jdbc.test.generator.model.TdmsDefineHost;

/**
 * <p>
 * 主机定义 mapper接口
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@Mapper
public interface TdmsDefineHostMapper {

    /**
     * 插入
     *
     * @param tdmsDefineHost 主机定义
     * @return
     */
    int insert(@Param("tdmsDefineHost") TdmsDefineHost tdmsDefineHost);

    /**
     * 通过主键查找
     *
     * @param hostName hostName 主机名
     * @param id id 主键
     * @return
     */
    TdmsDefineHost selectByPrimaryKey(@Param("hostName") String hostName,@Param("id") String id);

    /**
     * 通过主键删除
     *
     * @param hostName hostName 主机名
     * @param id id 主键
     * @return
     */
    int deleteByPrimaryKey(@Param("hostName") String hostName,@Param("id") String id);

    /**
     * 通过主键更新
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(TdmsDefineHost tdmsDefineHost);


}