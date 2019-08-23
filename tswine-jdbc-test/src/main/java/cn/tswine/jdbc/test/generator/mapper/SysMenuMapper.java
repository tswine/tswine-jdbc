package cn.tswine.jdbc.test.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import cn.tswine.jdbc.test.generator.model.SysMenu;

/**
 * <p>
 * 系统菜单 mapper接口
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@Mapper
public interface SysMenuMapper {

    /**
     * 插入
     *
     * @param sysMenu 系统菜单
     * @return
     */
    int insert(@Param("sysMenu") SysMenu sysMenu);

    /**
     * 通过主键查找
     *
     * @param id id 
     * @return
     */
    SysMenu selectByPrimaryKey(@Param("id") String id);

    /**
     * 通过主键删除
     *
     * @param id id 
     * @return
     */
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 通过主键更新
     *
     * @param
     * @return
     */
    int updateByPrimaryKey(SysMenu sysMenu);


}