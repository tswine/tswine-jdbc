package cn.tswine.jdbc.test.generator.model;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @Author silly
 * @Date 2019-08-09
 * @Desc
 */
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
     * 主键
     */
    private String id;
    /**
     * 工程ID,多个项目的ID，暂为空，不启用
     */
    private String projectId;
    /**
     * 父类ID
     */
    private String parentId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单路径
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 排序  有小到大排序
     */
    private Integer sort;
    /**
     * 菜单类型  menu:菜单 view:视图  button:按钮
     */
    private String type;
    /**
     * 权限
     */
    private String permission;
    /**
     * 是否有效 1:有效 0:无效
     */
    private Integer isValid;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 删除时间
     */
    private LocalDateTime deleteTime;
    /**
     * 级数
     */
    private Integer level;

    /**
     * 设置主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取主键
     */
    public String getId() {
        return id;
    }
    /**
     * 设置工程ID,多个项目的ID，暂为空，不启用
     */
    public void setProjectid(String projectId) {
        this.projectId = projectId;
    }

    /**
     * 获取工程ID,多个项目的ID，暂为空，不启用
     */
    public String getProjectid() {
        return projectId;
    }
    /**
     * 设置父类ID
     */
    public void setParentid(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父类ID
     */
    public String getParentid() {
        return parentId;
    }
    /**
     * 设置菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置菜单路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取菜单路径
     */
    public String getPath() {
        return path;
    }
    /**
     * 设置菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取菜单图标
     */
    public String getIcon() {
        return icon;
    }
    /**
     * 设置排序  有小到大排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取排序  有小到大排序
     */
    public Integer getSort() {
        return sort;
    }
    /**
     * 设置菜单类型  menu:菜单 view:视图  button:按钮
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取菜单类型  menu:菜单 view:视图  button:按钮
     */
    public String getType() {
        return type;
    }
    /**
     * 设置权限
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取权限
     */
    public String getPermission() {
        return permission;
    }
    /**
     * 设置是否有效 1:有效 0:无效
     */
    public void setIsvalid(Integer isValid) {
        this.isValid = isValid;
    }

    /**
     * 获取是否有效 1:有效 0:无效
     */
    public Integer getIsvalid() {
        return isValid;
    }
    /**
     * 设置备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * 获取备注
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * 设置是否删除
     */
    public void setIsdelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取是否删除
     */
    public Integer getIsdelete() {
        return isDelete;
    }
    /**
     * 设置删除时间
     */
    public void setDeletetime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * 获取删除时间
     */
    public LocalDateTime getDeletetime() {
        return deleteTime;
    }
    /**
     * 设置级数
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取级数
     */
    public Integer getLevel() {
        return level;
    }
}