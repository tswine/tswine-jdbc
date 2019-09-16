jdbc-plus SELECT 语句API 
=====
## API列表
### List<Map<String, Object>> select(String sql, Object[] params)
> 根据自定义sql语句查询数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|sql| String|执行的SQL语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List<Map<String, Object>>|  查询到的数据集合 | 
#### 样例
```java
String sql = "SELECT user_name FROM `user` WHERE create_time >=?  AND sex = ? GROUP BY user_name ";
List<Map<String, Object>> select = userDao.select(sql, new Object[]{"2019-09-02", 1});
Assert.assertNotNull(select);
```
***

### List\<T> selectList(String sql, Object[] params)
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|sql| String|执行的SQL语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List\<T>|  查询到的集合 | 
#### 样例
```java
String sql = "SELECT user_name FROM `user` WHERE create_time >=?  AND sex = ? GROUP BY user_name ";
List<User> list = userDao.selectList(sql, new Object[]{"2019-09-02", 1});
Assert.assertNotNull(list);
```
***

### List<Map<String, Object>> select(String tableName, String[] columns, String where, Object... params)
> 根据条件语句查询
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|tableName| String|查询的表名|
|columns|String[]|查询列集合，查询所有的话为NULL|
|where |String |查询子条件|
|params|Object[]|查询条件参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List<Map<String, Object>> | 数据集合 | 
#### 样例
```java
String where = "where create_time >= ?  AND sex = ?";
List<Map<String, Object>> list = userDao.select(User.TABLE_NAME, new String[]{"id","user_name"}, where, new Object[]{"2019-09-02", 1});
Assert.assertNotNull(list);
```
***

### List\<T> selectList(String tableName, String[] columns, String where, Object... params)
> 根据条件语句查询
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|tableName| String|查询的表名|
|columns|String[]|查询列集合，查询所有的话为NULL|
|where |String |查询子条件|
|params|Object[]|查询条件参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List\<T> | 数据集合 | 
#### 样例
```java
String where = "where create_time >= ?  AND sex = ?";
List<User> list = userDao.selectList(User.TABLE_NAME, new String[]{"id", "user_name"}, where, new Object[]{"2019-09-02", 1});
Assert.assertNotNull(list);
```
***

### T selectById(Serializable... ids)
> 根据map条件查询数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|ids|Serializable[]|主键值：支持多主键|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|T| 查询到的数据 | 
#### 样例
```java
User user = userDao.selectById("21e84e67a6a243d8ba04209d1dccca29");
Assert.assertNotNull(user);
```
***

### List<T> selectBatchIds(Collection<? extends Serializable> idList)
> 根据主键集合
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|idList|List|主键集合，不支持多主键批量查询|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List<T>| 查询到的数据 | 
#### 样例
```java
List<String> ids = new ArrayList<>();
ids.add("00fe3989a258429c9692ad450b5eac55");
ids.add("21e84e67a6a243d8ba04209d1dccca29");
ids.add("fe4fde91818f47698d4aa8ad361cbf4c");
List<User> list = userDao.selectBatchIds(ids);
Assert.assertEquals(list.size(), 3);
```
***

### List<T> select(Wrapper wrapper)
> 根据主键集合
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|wrapper|QueryWraaper|查询条件构造器|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List<T>| 查询到的数据 | 
#### 样例
```java
QueryWrapper wrapper = new QueryWrapper();
wrapper.select("id", "user_name", "sex").
       bracketLeft().le(User.FIELD_CREATE_TIME, "2019-09-16")
       .or().in(User.FIELD_USER_NAME, new Object[]{"tswine", "tswine1", "tswine2", "tswine3"}).bracketRight()
       .or().eq(User.FIELD_IS_DELETE, 0);
List<User> select = userDao.select(wrapper);
Assert.assertNotNull(select);
```

编译后执行sql:
```
SELECT `id`,`user_name`,`sex` FROM `user` WHERE ( create_time <= ? OR user_name IN ( ?,?,?,? ) )OR is_delete = ?
```
***

