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
 *
 * </p>
 *
 * @Author silly
 * @Date 2019-09-02
 * @Desc
 */
@ApiModel(value = "User对象", description = "")
@Data
@Accessors(chain = true)
@TableName(value = "user")
public class User {

    public static final String TABLE_NAME = "user";

    public static final String FIELD_ID = "id";
    public static final String FIELD_USER_NAME = "user_name";
    public static final String FIELD_PASS_WORD = "pass_word";
    public static final String FIELD_IS_DELETE = "is_delete";
    public static final String FIELD_CREATE_TIME = "create_time";
    public static final String FIELD_SEX = "sex";

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField(value = "user_name")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField(value = "pass_word")
    private String passWord;
    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField(value = "is_delete")
    private Integer isDelete;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    /**
     * 性别:  0:未知 1:男  2:女
     */
    @ApiModelProperty(value = "性别:  0:未知 1:男  2:女")
    @TableField(value = "sex")
    private Integer sex;

}