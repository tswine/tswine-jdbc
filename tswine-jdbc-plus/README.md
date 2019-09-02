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
####  1.新建全局配置类
```java
GlobalConfig globalConfig = new GlobalConfig();
```


##### 2.配置多数据源
> 数据源根据自己需要设置不同的数据源
将数据源加入全局配置类
```java
globalConfig.addDataSource(DbType.MYSQL, dataSource);
```
**GlobalConfig->addDataSource(IDBLabel label, DataSource ds);**
  <br/>IDBLabel：数据库标签，需要用户自行实现IDBLabel接口，用来区分数据库
  <br/>DataSource：新建的数据源
