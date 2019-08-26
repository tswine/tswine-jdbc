package cn.tswine.jdbc.test.plus.entity;

import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import cn.tswine.jdbc.common.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@ApiModel(value = "SysMenu对象", description = "系统菜单")
@Data
@Accessors(chain = true)
@TableName(value = "sys_menu")
public class SysMenu {

    public static final String TABLE_NAME = "sys_menu";

    public static final String FIELD_ID = "id";
    public static final String FIELD_PROJECT_ID = "project_id";
    public static final String FIELD_PARENT_ID = "parent_id";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_PATH = "path";
    public static final String FIELD_ICON = "icon";
    public static final String FIELD_SORT = "sort";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_PERMISSION = "permission";
    public static final String FIELD_IS_VALID = "is_valid";
    public static final String FIELD_REMARKS = "remarks";
    public static final String FIELD_IS_DELETE = "is_delete";
    public static final String FIELD_DELETE_TIME = "delete_time";
    public static final String FIELD_LEVEL = "level";

    /**
     *
     */
    @ApiModelProperty(value = "")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 工程ID,多个项目的ID，暂为空，不启用
     */
    @ApiModelProperty(value = "工程ID,多个项目的ID，暂为空，不启用")
    @TableField(value = "project_id")
    private String projectId;
    /**
     * 父类ID
     */
    @ApiModelProperty(value = "父类ID")
    @TableField(value = "parent_id")
    private String parentId;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @TableField(value = "name")
    private String name;
    /**
     * 菜单路径
     */
    @ApiModelProperty(value = "菜单路径")
    @TableField(value = "path")
    private String path;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "菜单图标")
    @TableField(value = "icon")
    private String icon;
    /**
     * 排序  有小到大排序
     */
    @ApiModelProperty(value = "排序  有小到大排序")
    @TableField(value = "sort")
    private Integer sort;
    /**
     * 菜单类型  menu:菜单 view:视图  button:按钮
     */
    @ApiModelProperty(value = "菜单类型  menu:菜单 view:视图  button:按钮")
    @TableField(value = "type")
    private String type;
    /**
     * 权限
     */
    @ApiModelProperty(value = "权限")
    @TableField(value = "permission")
    private String permission;
    /**
     * 是否有效 1:有效 0:无效
     */
    @ApiModelProperty(value = "是否有效 1:有效 0:无效")
    @TableField(value = "is_valid")
    private Integer isValid;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField(value = "remarks")
    private String remarks;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField(exist = false)
    private Integer isDelete;
    /**
     * 删除时间
     */
    @ApiModelProperty(value = "删除时间")
    @TableField(value = "delete_time")
    private LocalDateTime deleteTime;
    /**
     * 级数
     */
    @ApiModelProperty(value = "级数")
    @TableField(value = "level")
    private Integer level;


}