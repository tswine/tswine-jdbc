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
### int insert (T entity)
##### 请求参数
|参数|类型|描述 |
| :---:|:---:|:---:|
|T|  泛型T实体对象|需要插入的实体对象类型 |
##### 返回参数
|类型|描述| 
| :---:|:---:|
|int|  插入的行数| 
##### 样例
```java
SysMenu sysMenu = new SysMenu();
sysMenu.setName("测试");
sysMenu.setParentId("/admin/test");
sysMenu.setIcon("test");
sysMenu.setIsValid(0);
sysMenu.setRemarks("备注");
sysMenu.setIsDelete(1);

int insert = sysMenuDao.insert(sysMenu);
```
