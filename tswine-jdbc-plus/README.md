jdbc plus 基于jdbc操作通用封装,自动实现单表CRUD功能 
=====
添加maven依赖
--------
```xml
  <dependency>
    <groupId>cn.tswine.jdbc</groupId>
    <artifactId>tswine-jdbc-plus</artifactId>
    <version>${version}</version>
  </dependency>
```


开始使用
--------
##  1.新建全局配置类
```java
PlusConfig plusConfig = new PlusConfig();
```


## 2.配置多数据源
> 数据源根据自己需要设置不同的数据源，然后将数据源加入全局配置类：
```java
plusConfig.addDataSource(DbType.MYSQL, dataSource);
```
**GlobalConfig->addDataSource(IDBLabel label, DataSource ds);**
  <br/>IDBLabel：数据库标签，需要用户自行实现IDBLabel接口，用来区分数据库
  <br/>DataSource：新建的数据源


## 3.自建Dao实体类实现AbstractDao<T>抽象类
>泛型T：Dao层所对应的实体对象-->实体对象对应的表或者视图<br/>
>泛型接口：需要返回该操作类对应的数据库标签（操作使用的数据源）
```java
public IDBLabel getDbLabel() {
  return DbType.MYSQL;
}
```

## 配置完成，可使用基础功能CRUD 
 <h4> INSERT插入： https://github.com/tswine/tswine-jdbc/blob/master/tswine-jdbc-plus/INSERT.md  </h4>
 <h4> SELECT查询： https://github.com/tswine/tswine-jdbc/blob/master/tswine-jdbc-plus/SELECT.md  </h4>
 <h4> DELETE删除： https://github.com/tswine/tswine-jdbc/blob/master/tswine-jdbc-plus/DELETE.md  </h4>
 <h4> UPDATE更新： https://github.com/tswine/tswine-jdbc/blob/master/tswine-jdbc-plus/UPDATE.md  </h4>
 
***
使用样例：基础CRUD
--------
### 参数配置:配置数据源
```java
PlusConfig plusConfig = new PlusConfig();
DruidDataSource dataSource = new DruidDataSource();
dataSource.setUrl("jdbc:mysql://192.168.47.100:3306/tswine_jdbc?characterEncoding=UTF-8&useUnicode=true&useSSL=false");
dataSource.setUsername("tswine_jdbc");
dataSource.setPassword("123456");
dataSource.setDriverClassName("com.mysql.jdbc.Driver");
plusConfig.addDataSource(DbType.MYSQL, dataSource);
```

### 创建工厂
```java
JdbcPlusFactory factory = new JdbcPlusFactory(globalConfig);
```

### 创建实体对象（User）:可使用tswine-jdbc-generator模块自动生成
```java
/**
 * <p>
 * 用户信息
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
    @TableId(value = "id")
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
```
### 创建数据库操作对象（UserDao）
```java
public class UserDao extends AbstractDao<User> {
    public IDBLabel getDbLabel() {
        return DbType.MYSQL;
    }
}
```

### 使用CRUD API
```java
//获取操作对象
UserDao userDao = factory.getDao(UserDao.class);
//根据主键查询数据
User user = userDao.selectById("1");
```


  
