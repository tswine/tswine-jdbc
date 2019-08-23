package cn.tswine.jdbc.test.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.tswine.jdbc.test.generator.model.SysUser;

/**
 * <p>
 * 系统用户表 mapper接口
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@Mapper
public interface SysUserMapper {

    /**
     * 插入
     *
     * @param sysUser 系统用户表
     * @return
     */
    int insert(@Param("sysUser") SysUser sysUser);

    /**
     * 通过主键查找
     *
     * @param id id 主键id
     * @return
     */
    SysUser selectByPrimaryKey(@Param("id") String id);

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
    int updateByPrimaryKey(SysUser sysUser);


}