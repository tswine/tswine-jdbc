package cn.tswine.jdbc.test.generator.model;

import java.time.LocalDateTime;

/**
 * <p>
 * 主机定义
 * </p>
 *
 * @Author silly
 * @Date 2019-08-09
 * @Desc
 */
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
    private String id;
    /**
     * 主机名
     */
    private String hostName;
    /**
     * 别名
     */
    private String alias;
    /**
     * 监控的主机IP
     */
    private String ipAddress;
    /**
     * 所属组ID
     */
    private String groupId;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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
     * 设置主机名
     */
    public void setHostname(String hostName) {
        this.hostName = hostName;
    }

    /**
     * 获取主机名
     */
    public String getHostname() {
        return hostName;
    }
    /**
     * 设置别名
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取别名
     */
    public String getAlias() {
        return alias;
    }
    /**
     * 设置监控的主机IP
     */
    public void setIpaddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 获取监控的主机IP
     */
    public String getIpaddress() {
        return ipAddress;
    }
    /**
     * 设置所属组ID
     */
    public void setGroupid(String groupId) {
        this.groupId = groupId;
    }

    /**
     * 获取所属组ID
     */
    public String getGroupid() {
        return groupId;
    }
    /**
     * 设置创建时间
     */
    public void setCreatetime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建时间
     */
    public LocalDateTime getCreatetime() {
        return createTime;
    }
}