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
### List<T> selectByWhere(String whereSql, Object... params) 
> 根据条件语句和参数查询
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|whereSql| String|where条件SQL语句|
|params|Object[]|条件子句中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|List<T>| 数据集合 | 
#### 样例
```java
String whereSql = "user_name = ? AND sex = ? AND create_time >= ?";
List<User> list = userDao.selectByWhere(whereSql, new Object[]{"tswine", 1, "2019-09-02"});
Assert.assertNotNull(list);
```
***
### T selectById(Serializable... ids)
> 根据map条件查询数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|ids|主键（多）|主键值：支持多主键|
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
|ids|主键（多）|主键值：支持多主键|
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

