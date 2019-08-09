package cn.tswine.jdbc.test.generator.model;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表
 * </p>
 *
 * @Author silly
 * @Date 2019-08-09
 * @Desc
 */
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
    private String id;
    /**
     * 登录账号
     */
    private String username;
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 密码
     */
    private String password;
    /**
     * md5密码盐
     */
    private String salt;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 生日
     */
    private LocalDateTime birthday;
    /**
     * 性别（1：男 2：女 0:保密）
     */
    private Integer sex;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 电话
     */
    private String phone;
    /**
     * 状态(1：正常  2：冻结 ）
     */
    private Integer status;
    /**
     * 删除状态（0，正常，1已删除）
     */
    private Integer delFlag;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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
     * 设置登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取登录账号
     */
    public String getUsername() {
        return username;
    }
    /**
     * 设置真实姓名
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 获取真实姓名
     */
    public String getRealname() {
        return realname;
    }
    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置md5密码盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取md5密码盐
     */
    public String getSalt() {
        return salt;
    }
    /**
     * 设置头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取头像
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置生日
     */
    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取生日
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }
    /**
     * 设置性别（1：男 2：女 0:保密）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取性别（1：男 2：女 0:保密）
     */
    public Integer getSex() {
        return sex;
    }
    /**
     * 设置电子邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取电子邮件
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取电话
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置状态(1：正常  2：冻结 ）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取状态(1：正常  2：冻结 ）
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * 设置删除状态（0，正常，1已删除）
     */
    public void setDelflag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取删除状态（0，正常，1已删除）
     */
    public Integer getDelflag() {
        return delFlag;
    }
    /**
     * 设置创建人
     */
    public void setCreateby(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建人
     */
    public String getCreateby() {
        return createBy;
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