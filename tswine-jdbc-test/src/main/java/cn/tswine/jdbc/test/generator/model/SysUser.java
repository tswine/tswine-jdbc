package cn.tswine.jdbc.test.generator.model;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;
import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@ApiModel(value = "SysUser对象", description = "系统用户表")
@Data
@Accessors(chain = true)
@TableName(value = "sys_user")
public class SysUser {

    public static final String TABLE_NAME = "sys_user";

    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_REALNAME = "realname";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_SALT = "salt";
    public static final String FIELD_AVATAR = "avatar";
    public static final String FIELD_BIRTHDAY = "birthday";
    public static final String FIELD_SEX = "sex";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_STATUS = "status";
    public static final String FIELD_DEL_FLAG = "del_flag";
    public static final String FIELD_CREATE_BY = "create_by";
    public static final String FIELD_CREATE_TIME = "create_time";

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id")
    private String id;
    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    @TableField(value = "username")
    private String username;
    /**
     * 真实姓名
     */
    @ApiModelProperty(value = "真实姓名")
    @TableField(value = "realname")
    private String realname;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "password")
    private String password;
    /**
     * md5密码盐
     */
    @ApiModelProperty(value = "md5密码盐")
    @TableField(value = "salt")
    private String salt;
    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField(value = "avatar")
    private String avatar;
    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    @TableField(value = "birthday")
    private LocalDateTime birthday;
    /**
     * 性别（1：男 2：女 0:保密）
     */
    @ApiModelProperty(value = "性别（1：男 2：女 0:保密）")
    @TableField(value = "sex")
    private Integer sex;
    /**
     * 电子邮件
     */
    @ApiModelProperty(value = "电子邮件")
    @TableField(value = "email")
    private String email;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @TableField(value = "phone")
    private String phone;
    /**
     * 状态(1：正常  2：冻结 ）
     */
    @ApiModelProperty(value = "状态(1：正常  2：冻结 ）")
    @TableField(value = "status")
    private Integer status;
    /**
     * 删除状态（0，正常，1已删除）
     */
    @ApiModelProperty(value = "删除状态（0，正常，1已删除）")
    @TableField(value = "del_flag")
    private Integer delFlag;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(value = "create_by")
    private String createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

}