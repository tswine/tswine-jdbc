package cn.tswine.jdbc.test.generator.model;


/**
 * <p>
 * 组定义
 * </p>
 *
 * @Author silly
 * @Date 2019-08-09
 * @Desc
 */
public class TdmsDefineGroup {

    public static final String TABLE_NAME = "tdms_define_group";

    public static final String FIELD_ID = "id";
    public static final String FIELD_GROUP_NAME = "group_name";
    public static final String FIELD_GROUP_TYPE = "group_type";

    /**
     * 主键id
     */
    private String id;
    /**
     * 组名
     */
    private String groupName;
    /**
     * 组类别
     */
    private String groupType;

    /**
     * 设置主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取主键id
     */
    public String getId() {
        return id;
    }
    /**
     * 设置组名
     */
    public void setGroupname(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取组名
     */
    public String getGroupname() {
        return groupName;
    }
    /**
     * 设置组类别
     */
    public void setGrouptype(String groupType) {
        this.groupType = groupType;
    }

    /**
     * 获取组类别
     */
    public String getGrouptype() {
        return groupType;
    }
}