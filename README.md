tswine jdbc基于jdbc封装的数据库操作
=====
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

## 开发环境
- jdk:Java 8
- 开发工具：IDEA
- 依赖管理：Maven

## 引入
- git clone  https://github.com/tswine/tswine-jdbc
- cd tswine-jdbc && mvn clean compile package install
- 引入需要的模块代码

## 模块
- tswine-jdbc-common：基础公共封装
- tswine-jdbc-generator：代码生成器  https://github.com/tswine/tswine-jdbc/blob/master/tswine-jdbc-generator/README.md
- tswine-jdbc-plus：基于jdbc操作通用封装,自动实现单表CRUD功能 

