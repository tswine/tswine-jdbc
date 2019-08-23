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
 * 主机定义
 * </p>
 *
 * @Author silly
 * @Date 2019-08-23
 * @Desc
 */
@ApiModel(value = "TdmsDefineHost对象", description = "主机定义")
@Data
@Accessors(chain = true)
@TableName(value = "tdms_define_host")
public class TdmsDefineHost {

    public static final String TABLE_NAME = "tdms_define_host";

    public static final String FIELD_ID = "id";
    public static final String FIELD_HOST_NAME = "host_name";
    public static final String FIELD_ALIAS = "alias";
    public static final String FIELD_IP_ADDRESS = "ip_address";
    public static final String FIELD_GROUP_ID = "group_id";
    public static final String FIELD_CREATE_TIME = "create_time";

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(value = "id")
    private String id;
    /**
     * 主机名
     */
    @ApiModelProperty(value = "主机名")
    @TableId(value = "host_name")
    private String hostName;
    /**
     * 别名
     */
    @ApiModelProperty(value = "别名")
    @TableField(value = "alias")
    private String alias;
    /**
     * 监控的主机IP
     */
    @ApiModelProperty(value = "监控的主机IP")
    @TableField(value = "ip_address")
    private String ipAddress;
    /**
     * 所属组ID
     */
    @ApiModelProperty(value = "所属组ID")
    @TableField(value = "group_id")
    private String groupId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time")
    private LocalDateTime createTime;

}