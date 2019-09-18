jdbc-plus UPDATE 语句API 
=====
## API列表
### int update(String sql, Object[] params)
> 根据自定义sql语句更新数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|sql| String|执行的SQL语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
String sql = "UPDATE user SET user_name = ?,pass_word = ? WHERE id = ? AND sex = ?";
int update = userDao.update(sql, new Object[]{"test1", "password1", "04fcef0986254f389438a663e662e4bf", 0});
Assert.assertEquals(1, update);
```
***

### int updateByWhere(String tableName, Map<String, Object> update, String whereSql,Object[] params)
> 根据where语句更新数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
| :---:|:---:|:---:|
|tableName| String|表名|
|update|Map<String, Object>|更新列及值|
|sqlWhere|String|where语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
Map<String, Object> params = new HashMap<>();
params.put("user_name", "test22");
params.put("pass_word", "password22");
String whereSql = "WHERE id = ? ";
int update = userDao.updateByWhere("user", params, whereSql, new Object[]{"04fcef0986254f389438a663e662e4bf"});
Assert.assertEquals(1, update);
```
***

### int updateById(T entity)
> 根据主键id更新数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
| :---:|:---:|:---:|
|entity| T|实体对象数据|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
User user = userDao.selectById(id);
user.setSex(3);
user.setUserName("username3");
user.setPassWord("password3");
user.setSort(3);
int update = userDao.updateById(user);
Assert.assertEquals(1, update);
```
***

### int updateById(T entity, String[] excludeColumns)
> 根据主键id更新数据并排除指定列数据不更新
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
| :---:|:---:|:---:|
|entity| T|实体对象数据|
|excludeColumns| String[]|排除列集合|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
User user = userDao.selectById(id);
user.setSex(4);
user.setUserName("username4");
user.setPassWord("password4");
user.setSort(4);
int update = userDao.updateById(user, new String[]{"sex", "sort"});
Assert.assertEquals(1, update);
```
***

### int update(String tableName, Map<String, Object> update, Map<String, Object> where)
> 根据map更新列和条件更新数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
| :---:|:---:|:---:|
|tableName| String|表名|
|update| Map<String, Object>|更新列及值|
|where|Map<String, Object>|条件列及值|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
Map<String, Object> set = new HashMap<>();
set.put("user_name", "test22");
set.put("pass_word", "password22");
Map<String, Object> where = new HashMap<>();
where.put("id", "04fcef0986254f389438a663e662e4bf");
int update = userDao.update("user", set, where);
Assert.assertEquals(1, update);
```
***

### int update(T entity, Wrapper updateWrapper)
> 根据条件构造器更新数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
| :---:|:---:|:---:|
|entity| T|实体对象数据|
||entity| Wrapper|条件构造器|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
User user = userDao.selectById(id);
user.setSex(6);
user.setUserName("username66");
user.setPassWord("password66");
user.setSort(66);

UpdateWrapper updateWrapper = new UpdateWrapper();
updateWrapper.exclude("id", "sort").eq("id", user.getId()).eq("sex", 6);
int update = userDao.update(user, updateWrapper);
Assert.assertEquals(1, update);
```
***


