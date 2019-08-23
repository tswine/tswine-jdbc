package cn.tswine.jdbc.test.generator.model;

import lombok.Data;
import lombok.experimental.Accessors;
import cn.tswine.jdbc.common.annotation.TableField;
import cn.tswine.jdbc.common.annotation.TableId;
import cn.tswine.jdbc.common.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 组定义
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@ApiModel(value = "TdmsDefineGroup对象", description = "组定义")
@Data
@Accessors(chain = true)
@TableName(value = "tdms_define_group")
public class TdmsDefineGroup {

    public static final String TABLE_NAME = "tdms_define_group";

    public static final String FIELD_ID = "id";
    public static final String FIELD_GROUP_NAME = "group_name";
    public static final String FIELD_GROUP_TYPE = "group_type";

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id")
    private String id;
    /**
     * 组名
     */
    @ApiModelProperty(value = "组名")
    @TableField(value = "group_name")
    private String groupName;
    /**
     * 组类别
     */
    @ApiModelProperty(value = "组类别")
    @TableField(value = "group_type")
    private String groupType;

}