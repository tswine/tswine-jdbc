jdbc-plus DELETE 语句API 
=====
## API列表
### int delete(String sql, Object[] params)
> 根据自定义sql语句删除数据
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
String sql = "DELETE FROM user WHERE user_name = ? AND sex = ?";
int delete = userDao.delete(sql, new Object[]{"tswine117", 0});
Assert.assertEquals(1, delete);
```
***

### int deleteByWhere(String tableName, String sqlWhere, Object[] params)
> 根据自定义where语句删除数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|tableName| String|表名|
|sqlWhere| String|where语句|
|params|Object[]|执行SQL中的参数|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
String sql = "WHERE user_name = ? AND sex = ?";
int delete = userDao.deleteByWhere(User.TABLE_NAME, sql, new Object[]{"tswine117", 0});
Assert.assertEquals(1, delete);
```
***

### int deleteById(Serializable... ids)
> 根据主键删除数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|ids| Serializable[]|主键值(支持多主键)|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
int delete = userDao.deleteById("037479b2ec5f42af9aa264ce98836870");
Assert.assertEquals(0, delete);
```
***

### int deleteBatchIds(Collection\<? extends Serializable> idList)
> 根据主键批量删除数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|idList| Collection\<? extends Serializable>|批量删除主键列表(不支持多主键)|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
int delete = userDao.deleteBatchIds(Arrays.asList(
                "09d9d8efb397462abbb7c642eee2d9dd", "11946bc3fa304bf4a7879212a115fc1d"));
Assert.assertEquals(3, delete);
```
***

### int deleteByMap(Map\<String, Object> columnMap)
> 根据map条件删除指定数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|columnMap| Map\<String, Object>|map条件|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
Map<String, Object> params = new HashMap<>();
params.put(User.FIELD_ID, "xxxx");
params.put(User.FIELD_USER_NAME, "xxxx");
params.put(User.FIELD_PASS_WORD, "xxxx");
int delete = userDao.deleteByMap(params);
Assert.assertEquals(1, delete);
```
***

### int delete(Wrapper wrapper)
> 根据条件构造器删除指定数据
#### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|wrapper|Wrapper|条件构造器|
#### 返回参数
|类型|描述| 
| :---:|:---:|
|int| 受影响的行数| 
#### 样例
```java
UpdateWrapper wrapper = new UpdateWrapper();
wrapper.eq(User.FIELD_ID, "19ca9e349ebf442299b16f83fde56553")
    .or().eq(User.FIELD_USER_NAME, "tswine68");
int delete = userDao.delete(wrapper);
Assert.assertEquals(2, delete);
```
***