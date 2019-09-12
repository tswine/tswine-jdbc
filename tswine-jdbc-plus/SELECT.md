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
