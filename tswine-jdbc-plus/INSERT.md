jdbc-plus INSERT 语句API 
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
> 执行自定义的插入数据SQL语句
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
int insert = userDao.insert(sql, new Object[]{String.valueOf(System.currentTimeMillis()), "tswine1", "密码", 0, new Date(), 1});
Assert.assertEquals(insert, 1);
```
***
###  int insert(String tableName, Map<String, Object> columnValues)
> 插入数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|tableName|String |数据库表名|
|columnValues|Map<String,Object>|表中的列及值|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int|  插入的行数| 
#### 样例
```java
Map<String, Object> columns = new HashMap<>();
columns.put("id", String.valueOf(System.currentTimeMillis()));
columns.put("user_name", "tswine3");
columns.put("pass_word", "密码");
columns.put("is_delete", 0);
columns.put("create_time", new Date());
columns.put("sex", 1);

int insert = userDao.insert("user", columns);
Assert.assertEquals(insert, 1);
```

***
### int insert (T entity)
> 插入实体对象
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
user.setUserName("tswine2");
user.setPassWord("密码");
user.setIsDelete(0);
user.setCreateTime(LocalDateTime.now());
user.setSex(1);

int insert = userDao.insert(user);
Assert.assertEquals(insert, 1);
```
>  此处,将id注解：@TableId(value = "id", type = IdType.UUID)，则插入的时候会自动用UUID字符串填充ID列
***


### int insert (T entity,String[] excludeColumns)
> 插入实体对象(排除指定列)
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|T|  泛型T实体对象|插入的实体对象 |
|excludeColumns|String[]|排除的列集合 |
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int|  插入的行数| 
#### 样例
```java
User user = new User();
user.setUserName("insert4");
user.setPassWord("123456");
user.setIsDelete(0);
user.setCreateTime(LocalDateTime.now());

int insert = userDao.insert(user, new String[]{User.FIELD_SEX});
Assert.assertEquals(insert, 1);
```
***

###  int[] insert(List<T> listEntity)
> 批量插入数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|listEntity|List<T>|批量插入的实体对象|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int[]|  插入的结果集| 
#### 样例
```java
List<User> list = new ArrayList<>();
for (int i = 0; i < 10; i++) {
  User user = new User();
  user.setUserName("tswine" + i);
  user.setPassWord("密码" + i);
  user.setIsDelete(0);
  user.setCreateTime(LocalDateTime.now());
  user.setSex(1);
  list.add(user);
}

int[] insert = userDao.insert(list);
Assert.assertEquals(insert.length, 10);
```
***

###  int[] insert(List<T> listEntity,String[] excludeColumns)
> 批量插入数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|listEntity|List<T>|批量插入的实体对象|
|excludeColumns|String[]|插入排除的列|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int[]|  插入的结果集| 
#### 样例
```java
List<User> list = new ArrayList<>();
  for (int i = 0; i < 10; i++) {
    User user = new User();
    user.setUserName("tswine" + i);
    user.setPassWord("密码" + i);
    user.setCreateTime(LocalDateTime.now());
    user.setIsDelete(0);
    list.add(user);
  }

int[] insert = userDao.insert(list, new String[]{User.FIELD_SEX});
Assert.assertEquals(insert.length, 10);
```



