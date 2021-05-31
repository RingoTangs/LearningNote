# 一、Mongo基础

## 1. 基本使用

### 1.1. 安装

```shell
# 1. docker 拉取最新的镜像
docker pull mongo:latest

# 2. 运行 mongo 容器
# 挂载到容器中的/data/db, 作为 mongodb 的存储目录
docker run -p 27017:27017 -v /root/mongo/data:/data/db --name mongodb -d mongo:latest

# 3. 进入容器
docker exec -it mongo /bin/bash

# 4. 输入命令 "mongo" 启动客户端, 即可连接上数据库服务器
root@537ceeed6b0c:/# mongo
MongoDB shell version v4.4.6
connecting to: mongodb://127.0.0.1:27017/?compressors=disabled&gssapiServiceName=mongodb
Implicit session: session { "id" : UUID("17cb1fb0-e232-48dc-a0be-400fab54eae8") }
MongoDB server version: 4.4.6
>
```



### 1.2. 基本概念

**数据库、集合、文档**。

> - 在 MongoDB 中，数据库和集合都不需要手动创建。当创建文档时，如果文档所在的集合或数据库不存在，会自动创建数据库和集合。



### 1.2. 基本指令

```shell
# 1. 显示所有的数据库
show dbs

# 2. use + “数据库名” 表示进入到指定数据库(数据库不存在, 也可以创建, 第一次插入文档时自动创建数据库)
use test

# 3. db命令: 查看当前所在数据库(默认是test)
> db
test

# 4. 查看数据库中所有的集合
show collections
```



## 2. CRUD操作

### 2.1. 插入文档









