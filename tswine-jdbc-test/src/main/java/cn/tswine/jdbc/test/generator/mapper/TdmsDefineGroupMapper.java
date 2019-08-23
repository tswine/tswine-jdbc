package cn.tswine.jdbc.test.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.tswine.jdbc.test.generator.model.TdmsDefineGroup;

/**
 * <p>
 * 组定义 mapper接口
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@Mapper
public interface TdmsDefineGroupMapper {

    /**
     * 插入
     *
     * @param tdmsDefineGroup 组定义
     * @return
     */
    int insert(@Param("tdmsDefineGroup") TdmsDefineGroup tdmsDefineGroup);

    /**
     * 通过主键查找
     *
     * @param id id 主键id
     * @return
     */
    TdmsDefineGroup selectByPrimaryKey(@Param("id") String id);

    /**
     * 通过主键删除
     *
     * @param id id 主键id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 通过主键更新
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(TdmsDefineGroup tdmsDefineGroup);


}