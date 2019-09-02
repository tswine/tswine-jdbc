jdbc-plus INSERT 语句操作API 
=====

## 主键注解 @TableId
```java
IdType type() default IdType.AUTO
```
### IdType类型
|字段|描述 |
| :---:|:---:|
|AUTO|数据库自增|
|INPUT|用户输入|
|ID_WORKER|全局唯一|
|UUID|采用UUID方式（去除-）生成全局唯一32位字符串|
|NONE|该类型为未设置主键类型|

## API列表
### int insert(String sql, Object[] params)
> 自定义插入sql语句
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|sql| String|插入数据执行的SQL语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int|  插入的行数| 
#### 样例
```java
String sql = "INSERT INTO `tswine_jdbc`.`user` (  `id`,`user_name`,`pass_word`,`is_delete`,`create_time`,`sex`)" +
  "VALUES (?,?,?,?,?,?) ";
int insert = userDao.insert(sql, new Object[]{String.valueOf(System.currentTimeMillis()), "tswine", "密码", 0, new Date(), 1});
Assert.assertEquals(insert, 1);
```
***
### int insert (T entity)
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|T|  泛型T实体对象|需要插入的实体对象类型 |
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int|  插入的行数| 
#### 样例
```java
User user = new User();
user.setUserName("tswine");
user.setPassWord("123456");
user.setIsDelete(0);
user.setCreateTime(LocalDateTime.now());
user.setSex(1);

int insert = userDao.insert(user);
Assert.assertEquals(insert, 1);
```
>  此处,将id注解：@TableId(value = "id", type = IdType.UUID)，则插入的时候会自动用UUID字符串填充ID列
