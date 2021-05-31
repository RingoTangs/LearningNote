# Tip:

**笔记是根据B站秦疆老师的视频记的，课程全部免费，有需要的小伙伴可以收藏一下！**

**视频传送门：https://www.bilibili.com/video/BV1S54y1R7SB**

# 1.Nosql概述

## 1.1.数据库发展历史

> 时代一：单机MySQL时代

<img src="https://img-blog.csdnimg.cn/20200628142237948.png" alt="单机MySQL" style="zoom: 150%;" />

- 90年代，一个基本的网站访问量一般不会太大，单个数据库完全足够！

- 那个时候更多的使用`Static HTML`！服务器根本没有太大压力。

- 这种情况下，整个网站的瓶颈式什么？
  - 数据量如果太大，一个机气放不下了。
  - MySQL数据库的索引（B+ Tree），一个机器也放不下。
  - 访问量（读写混合），一个服务器承受不了。

只要出现以上的三种情况之一，那么系统的架构就必须要升级了！

> 时代二：Memcached + MySQL + 垂直拆分（读写分离）

<img src="https://img-blog.csdnimg.cn/20200628142905504.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="数据库添加缓存" style="zoom:150%;"  />

网站80%的数据都是在读，每次查询都要去数据库查询数据的话数据库性能会大大降低！所以我们希望减轻数据库的压力，我们可以使用缓存来保证效率！

发展过程：优化数据结构和索引 ---> 文件缓存（IO）---> Memcached（当时最热门的技术）



> 时代三：分库分表 + 水平拆分 + MySQL集群

<img src="https://img-blog.csdnimg.cn/20200628143811317.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70" alt="MySQL集群" style="zoom:150%;" />

- 本质：数据库（读，写）。

- 早些年`MyISAM`：表锁，十分影响效率！高并发下会出现严重的问题！

- 转战`InnoDB`：行锁，比表锁效率要高跟多。
- 慢慢的就开始使用分库分表来解决写的压力。

> 时代四：我们现在的时代

- 2010—2020十年之间，计算机互联网行业已经发生了很大的变化，数据变的越来越多元化。
- MySQL等关系型数据库已经不够用了！数据量很多，变化超快。
- MySQL有的使用它来存储一些比较大的文件，博客，图片等，数据库表很大，查询效率非常低！如果有一种数据库专门处理这种数据，MySQL数据库压力就变得非常小了。

## 1.2.为什么要用NoSQL

- 用户的社交网络，地理位置，用户自定义的数据，用户日志等数据爆发式的增长，这些数据用关系型数据库就不合适了！
- NoSQL数据库可以很好处理以上的情况。

## 1.3.什么是NoSQL

- `NoSQL(Not Only SQL)`：不仅仅是SQL，泛指非关系型数据库，伴随着web2.0互联网的诞生！
- 关系型数据库：有表格，由行和列组成。
- 传统的关系型数据库很难对付web2.0时代！尤其是超大规模的高并发的社区，暴露出很多难以克服的问题。NoSQL在当今大数据环境下发展的十分迅速，Redis是发展最快的，是我们当下必须要掌握的一门技术。
- 很多的数据类型用户的个人信息，社交网络，地理位置，这些数据类型的存储不需要一个固定的格式！不需要多余的操作就可以横向扩展的！

## 1.4.NoSQL特点

- 方便扩展（数据之间没有关系，很好扩展）。
- 大数据量高性能（Redis一秒可以写8万次，读取11万次，NoSQL的缓存是一种细粒度的缓存）。
- 数据类型多样（不需要事先设计数据库！随取随用！如果是数据量十分大的表，很多人就无法设计了！）。

## 1.5.传统的RDBMS和NoSQL

```
传统的 RDBMS
- 结构化组织
- SQL
- 数据和关系都存在单独的表中
- 操作数据，数据定义语言
- 严格的一致性
- 基础的事物
```

```
NoSQL
- 不仅仅是数据
- 没有固定的查询语言
- 键值对存储，列存储，文档存储，图形数据库(社交关系)
- 最终一致性
- CAP定理 和 BASE理论
- 高性能，高可用，高可扩展
```

## 1.6.NoSQL的四大分类

- **KV键值对：**
  - 新浪：Redis。
  - 美团：Redis + Tair。
  - 阿里、百度：Redis + Memecache。
- **文档型数据库：**
  - MongoDB(一般必须要掌握)：基于分布式文件存储的数据库，C++编写，主要用来处理大量的文档！
  - **MongoDB是一个介于关系型数据库和非关系型数据库的中间产品。**
- **列存储数据库：**
  - HBase。
  - 分布式文件系统。
- **图形关系数据库：**
  - 不是存储图片的，是用来存储社交关系的，广告推荐。
  - Neo4j，InfoGrid。

# 2.Redis入门

## 2.1.Redis概述

> Redis是什么？

Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI [C语言](https://baike.baidu.com/item/C语言)编写、支持网络、可基于内存亦可持久化的日志型、Key-Value[数据库](https://baike.baidu.com/item/数据库/103728)，并提供多种语言的API。

> Redis能做什么？

- Redis是内存存储、内存中是断电就会消失，所以说持久化很重要(rdb、aof)。
- 效率高，可以用于告诉缓存。
- 发布订阅系统。
- 地图信息分析。
- 计时器、计数器（浏览量）。

## 2.2.Redis安装

```shell
# 1、下载安装包！redis-6.0.1.tar.gz。

# 2、上传到Linux服务器并解压，进入到Redis根目录下。

# 3、安装gcc套餐
yum install cpp
yum install binutils
yum install glibc
yum install glibc-kernheaders
yum install glibc-common
yum install glibc-devel
yum install gcc
yum install make

# 4、升级gcc
yum -y install centos-release-scl

yum -y install devtoolset-9-gcc devtoolset-9-gcc-c++ devtoolset-9-binutils

scl enable devtoolset-9 bash

# 5、执行make
make

# 6、安装Redis
make install

[root@mingyu redis-6.0.1]# make install
cd src && make install
make[1]: Entering directory `/opt/redis/redis-6.0.1/src'

Hint: It's a good idea to run 'make test' ;)

    INSTALL install
    INSTALL install
    INSTALL install
    INSTALL install
    INSTALL install
make[1]: Leaving directory `/opt/redis/redis-6.0.1/src'

# 7、Redis默认安装路径 这里有redis-server和redis-cli
/usr/local/bin

# 8、Redis默认不是后台启动的，需要修改配置文件！
201 ################################# GENERAL #####################################
202 
203 # By default Redis does not run as a daemon. Use 'yes' if you need it.
204 # Note that Redis will write a pid file in /var/run/redis.pid when daemonized.
205 daemonize yes # 这里默认是no 修改为yes代表后台启动

# 9、启动Redis服务 通过指定的配置文件来运行服务
[root@mingyu bin]# ./redis-server /opt/redis/redis-6.0.1/redis.conf
8995:C 28 Jun 2020 16:32:28.758 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
8995:C 28 Jun 2020 16:32:28.758 # Redis version=6.0.1, bits=64, commit=00000000, modified=0, pid=8995, just started
8995:C 28 Jun 2020 16:32:28.758 # Configuration loaded

# 10、redis客户端进行连接测试
[root@mingyu bin]# ./redis-cli -p 6379
127.0.0.1:6379> 

# 12、关闭Redis服务
127.0.0.1:6379> shutdown
```

## 2.3.Redis性能测试

**redis-benchmark是一个压力测试工具，官方自带的性能测试工具！**

```shell
redis-benchmark -h localhost -p 6379 -c 100 -n 100000

root@b72a84f52d13:~# redis-benchmark -h localhost -p 6379 -c 100 -n 100000
====== PING_INLINE ======
100000 requests completed in 1.63 seconds # 10W个请求在1.63秒执行成功
100 parallel clients	# 100个并发客户端
3 bytes payload	# 每次写入3个字节
keep alive: 1	# 只有一台Redis服务器来处理这些请求
host configuration "save": 900 1 300 10 60 10000
host configuration "appendonly": no
multi-thread: no

99.81% <= 2 milliseconds
99.99% <= 3 milliseconds
100.00% <= 3 milliseconds	# 所有请求在3ms内完成
54884.74 requests per second	# 每秒处理54884.74个请求
```

## 2.4.Redis基础知识

```shell
# 1、Redis默认有16个数据库 可以用SELECT来切换数据库
127.0.0.1:6379[1]> SELECT 0
OK
127.0.0.1:6379> 

# 2、查看DB大小 DBSIZE
127.0.0.1:6379> DBSIZE
(integer) 5

# 3、set get
127.0.0.1:6379> set name ringo
OK
127.0.0.1:6379> get name
"ringo"

# 4、查看数据库所有的key keys *
127.0.0.1:6379> KEYS *
1) "k1"
2) "key:{tag}:__rand_int__"
3) "myhash:{tag}:__rand_int__"
4) "mylist:{tag}"
5) "counter:{tag}:__rand_int__"
6) "name"

# 5、清空数据库
# flushdb 清空当前数据库
# flushall 清空所有数据库的数据

# 6、Redis是单线程的！
# Redis是很快的，Redis是基于内存操作的，CPU不是Redis的性能瓶颈，Redis的瓶颈是根据机器的内存和网络带宽，既然可以使用单线程来实现，就使用单线程了！

# 7、为什么单线程还这么快？
# Redis是C语言写的，Redis是将所有的数据全部放在内存中的，所以用单线程去操作内存效率是最高的！(多线程会引起CPU的上下文切换)
```

# 3.五大数据类型

## 3.1.Redis官网简介

- Redis是一个开源（BSD许可）的内存数据结构存储，用作数据库、缓存和消息代理。
- 它支持数据结构，如字符串、哈希、列表、集合、带范围查询的排序集合、位图、超日志、带半径查询的地理空间索引和流。
- Redis具有内置的复制、Lua脚本、LRU逐出、事务和不同级别的磁盘持久性，并通过Redis Sentinel和带有Redis集群的自动分区提供高可用性。

## 3.2.Redis Key

```shell
# 1、KEYS *               查看所有的key
# 2、SET name ringo       设置key
# 3、EXISTS name          是否存在当前key
# 4、MOVE name 1          将当前key移动到指定数据库
# 5、EXPIRE name 10       设置key的过期时间
# 6、TTL name             查看当前key的剩余时间
# 7、TYPE name            查看当前key的数据类型
# 8、DEL name             删除当前key
```

## 3.3.String(字符串)

> 基本操作

```shell
# 1、SET k1 v1              设置k-v
# 2、GET k1                 通过key获得value
# 3、KEYS *                 获得所有的key
# 4、EXISTS k1              判断当前key是否存在
# 5、APPEND k1 "hello"      在v1的基础上追加字符串变成”v1hello“，如果当前key不存在就相当于SET
# 6、STRLEN k1              获得k1对应的值的长度
```

> INCR 和 DECR

```shell
# 1、SET views 0            设置views的值为"0"
# 2、INCR views             views的值增加1，"0"变成"1"
# 3、DECR views             views的值减少1，"1"变成"0"
# 4、INCRBY views 10        views的值直接加10，"0"变成"10"
# 5、DECRBY views 10        views的值直接减10，"10"变成"0"
```

> GETRANGE截取字符串

```shell
# 1、SET k1 “hello,ringo!”  设置k1的值
# 2、GETRANGE k1 0 4        获得k1下标[0,4]的值，就是"hello"
# 3、GETRANGE k1 1 -1       获得k1下标[1,maxLen]的值，就是"ello,ringo!"  
```

> SETRANGE替换字符串

```shell
# 1、SET k2 abcdefg        设置k2的值
# 2、SETRANGE k2 2 xxx     从下标为2开始，3个字符替换为"xxx"，结果是"abxxxfg"
```

> SETEX 和 SETNX

```shell
# 1、SETEX(set with expire)         设置过期时间
127.0.0.1:6379[1]> SETEX k3 60 v3   # 设置k3的过期时间为60秒
OK
127.0.0.1:6379[1]> TTL k3
(integer) 57
           
# 2、SETNX(set if not exist)        如果不存在就设置k-v(在分布式锁中常常会用到)
127.0.0.1:6379[1]> SETNX k1 v1
(integer) 0
127.0.0.1:6379[1]> GET k1
"hello,Ringo"
```

> 批量设置

```shell
# 1、MSET k1 v1 k2 v2 ......      可以批量设置多个k-v
127.0.0.1:6379[1]> MSET k1 v1 k2 v2 k3 v3
OK
127.0.0.1:6379[1]> KEYS *
1) "k2"
2) "k1"
3) "k3"

# 2、MSETNX k1 vip1 k2 vip2 k4 vip4  虽然没有设置k4 但是批量设置k4也没有成功
# MSETNX 是一个原子性的操作，要么一起成功，要么一起失败！
127.0.0.1:6379[1]> MSETNX k1 vip1 k2 vip2 k4 vip4
(integer) 0
127.0.0.1:6379[1]> get k4
(nil)

# 3、巧妙设计key来存储对象
127.0.0.1:6379[1]> MSET user:1:name ringo user:1:age 18 user:1:id 968
OK
127.0.0.1:6379[1]> MGET user:1:name user:1:age user:1:id
1) "ringo"
2) "18"
3) "968"
```

> 组合命令

```shell
# 1、GETSET db redis 先获取db的值，由于没有设置GET的值是null，然后再SET db的值为redis
127.0.0.1:6379[1]> GETSET db redis
(nil)
127.0.0.1:6379[1]> GET db
"redis"
```

## 3.4.List(列表)

> 基本操作

```shell
# 在Redis里面，我们可以把List玩成栈、队列、阻塞队列！

# 1、LPUSH list01 one       从左边插入字符串"one"到列表list01
127.0.0.1:6379[1]> LPUSH list01 one
(integer) 1
127.0.0.1:6379[1]> LPUSH list01 two
(integer) 2
127.0.0.1:6379[1]> LPUSH list01 three
(integer) 3

# 2、RPUSH list01 right     从右边插入字符串"right"到列表list01
127.0.0.1:6379[1]> RPUSH list01 right
(integer) 4

# 3、LRANGE list01 0 -1     获取列表指定下标范围的值
127.0.0.1:6379[1]> LRANGE list01 0 -1
1) "three"
2) "two"
3) "one"
4) "right"
127.0.0.1:6379[1]> LRANGE list01 1 2
1) "two"
2) "one"

# 4、LPOP list01      将左边的第一个元素移出列表
127.0.0.1:6379[1]> LPOP list01
"three"
127.0.0.1:6379[1]> LRANGE list01 0 -1
1) "two"
2) "one"
3) "right"

# 5、RPOP list01      将右边的第一个元素移出列表
127.0.0.1:6379[1]> RPOP list01
"right"
127.0.0.1:6379[1]> LRANGE list01 0 -1
1) "two"
2) "one"

# 6、LINDEX list01 0      获取列表list01下标为0的值
127.0.0.1:6379[1]> LINDEX list01 0
"two"
127.0.0.1:6379[1]> LINDEX list01 1
"one"

# 7、LLEN list01     查看列表list01的长度
127.0.0.1:6379[1]> LLEN list01
(integer) 2

# 8、列表中是可以有相同的元素的
127.0.0.1:6379[1]> LPUSH list01 two
(integer) 3
127.0.0.1:6379[1]> LRANGE list01 0 -1
1) "two"
2) "two"
3) "one"

# 9、LREM list01 1 one 移除列表list01中1个元素(指定为"one")
127.0.0.1:6379[1]> LREM list01 1 one
(integer) 1
127.0.0.1:6379[1]> LRANGE list01 0 -1
1) "two"
2) "two"

# LREM list01 2 two  移除列表list01中2个元素(指定为"two")
127.0.0.1:6379[1]> LREM list01 2 two
(integer) 2
127.0.0.1:6379[1]> LRANGE list01 0 -1
(empty array)
```

> LTRIM截取列表中的元素

```shell
127.0.0.1:6379[1]> LPUSH mylist "hello,ringo"
(integer) 1
127.0.0.1:6379[1]> LPUSH mylist "hello,tangs"
(integer) 2
127.0.0.1:6379[1]> LPUSH mylist "hello,liming"
(integer) 3
127.0.0.1:6379[1]> LRANGE mylist 0 -1
1) "hello,liming"
2) "hello,tangs"
3) "hello,ringo"

# LTRIM mylist 1 2    截取列表mylist 下标[1,2]的元素  列表mylist已经被修改了！！
127.0.0.1:6379[1]> LTRIM mylist 1 2
OK
127.0.0.1:6379[1]> LRANGE mylist 0 -1
1) "hello,tangs"
2) "hello,ringo"
```

> RPOPLPUSH

```shell
# 列表src中从左边插入3个元素
127.0.0.1:6379[1]> LPUSH src s1 s2 s3
(integer) 3
127.0.0.1:6379[1]> LRANGE src 0 -1
1) "s3"
2) "s2"
3) "s1"

# 列表dest中从左边插入3个元素
127.0.0.1:6379[1]> LPUSH dest d1 d2 d3
(integer) 3
127.0.0.1:6379[1]> LRANGE dest 0 -1
1) "d3"
2) "d2"
3) "d1"

# RPOPLPUSH src dest  列表src右边弹出一个元素从左边压入到列表dest中
127.0.0.1:6379[1]> RPOPLPUSH src dest
"s1"
127.0.0.1:6379[1]> LRANGE src 0 -1
1) "s3"
2) "s2"
127.0.0.1:6379[1]> LRANGE dest 0 -1
1) "s1"
2) "d3"
3) "d2"
4) "d1"
```

> LSET

```shell
# LSET 修改列表中指定下标的值，前提是列表要存在并且不能下标越界！

# 向列表list02中插入3个元素
127.0.0.1:6379[1]> LPUSH list02 one two three
(integer) 3
127.0.0.1:6379[1]> LRANGE list02 0 -1
1) "three"
2) "two"
3) "one"

# LSET list02 0 "hello,world"  修改列表list02下标为0的值，改为"hello,world"
127.0.0.1:6379[1]> LSET list02 0 "hello,world"  
OK
127.0.0.1:6379[1]> LRANGE list02 0 -1
1) "hello,world"
2) "two"
3) "one"
```

> LINSET

```shell
# LINSET 在列表某个元素之前或者之后插入值

# 向列表list03中从左边压入5个元素
127.0.0.1:6379[1]> LPUSH list03 one two three four five
(integer) 5
127.0.0.1:6379[1]> LRANGE list03 0 -1
1) "five"
2) "four"
3) "three"
4) "two"
5) "one"

# LINSERT list03 before four "hello,world"  列表list03中元素"four"之前插入"hello,world"
127.0.0.1:6379[1]> LINSERT list03 before four "hello,world"
(integer) 6
127.0.0.1:6379[1]> LRANGE list03 0 -1
1) "five"
2) "hello,world"
3) "four"
4) "three"
5) "two"
6) "one"

# LINSERT list03 after one "Best To you!"   列表list03中元素"one"之后插入"Best To you!"
127.0.0.1:6379[1]> LINSERT list03 after one "Best To you!"
(integer) 7
127.0.0.1:6379[1]> LRANGE list03 0 -1
1) "five"
2) "hello,world"
3) "four"
4) "three"
5) "two"
6) "one"
7) "Best To you!"
```

> 小结

- `List`实际上是一个链表，可以在`Node`之前或者之后插入值。
- 如果`key`不存在，创建新的链表。
- 如果`key`存在，新增内容。
- 如果移除了链表中所有的元素，空链表，也代表不存在！
- 在两边插入或者改动值，效率最高！中间元素，相对来说效率会低一点。

## 3.5.Set(集合)

> 基本操作

```shell
# Set中的值是无序的并且不能重复的！

# 1、SADD set01 one  在集合set01中添加元素"one"
127.0.0.1:6379[1]> SADD set01 one two three four five
(integer) 5

# 2、SMEMBERS set01  查看集合set01的所有元素
127.0.0.1:6379[1]> SMEMBERS set01
1) "four"
2) "three"
3) "one"
4) "two"
5) "five"

# 3、SISMEMBER set01 one   查看元素"one"是否存在于集合set01中
127.0.0.1:6379[1]> SISMEMBER set01 one
(integer) 1
127.0.0.1:6379[1]> SISMEMBER set01 abc
(integer) 0

# 4、SCARD set01   获取集合set01中元素的个数
127.0.0.1:6379[1]> SCARD set01
(integer) 5

# 5、SREM set01 one two       移除集合set01中的元素"one","two"
127.0.0.1:6379[1]> SREM set01 one two
(integer) 2
127.0.0.1:6379[1]> SMEMBERS set01
1) "three"
2) "five"
3) "four"
```

> SRANDMEMBER

```shell
# SRANDMEMBER 在set集合中随机抽取n个元素

# 在集合set02中添加6个元素
127.0.0.1:6379[1]> SADD set02 one two three four five six
(integer) 6
127.0.0.1:6379[1]> SMEMBERS set02
1) "two"
2) "six"
3) "three"
4) "five"
5) "one"
6) "four"

# SRANDMEMBER set02 3  在集合set02中随机抽取三个元素 不会对集合set02做出修改
127.0.0.1:6379[1]> SRANDMEMBER set02 3
1) "three"
2) "two"
3) "four"
```

> SPOP

```shell
# SPOP 随机删除Set集合中的元素

# 在集合set03中添加5个元素
127.0.0.1:6379[1]> SADD set03 "hello" "hi" "good" "nice" "better"
(integer) 5
127.0.0.1:6379[1]> SMEMBERS set03
1) "nice"
2) "good"
3) "hello"
4) "hi"
5) "better"

# SPOP set03 2     在集合set03中随机删除2个元素
127.0.0.1:6379[1]> SPOP set03 2
1) "good"
2) "better"
127.0.0.1:6379[1]> SMEMBERS set03
1) "hi"
2) "nice"
3) "hello"
```

> SMOVE

```shell
# SMOVE     将源集合的元素移动到目标集合中

# 在集合src中添加4个元素
127.0.0.1:6379[1]> SADD src s1 s2 s3 s4
(integer) 4
127.0.0.1:6379[1]> SMEMBERS src
1) "s4"
2) "s3"
3) "s1"
4) "s2"

# 在集合dest中添加4个元素
127.0.0.1:6379[1]> SADD dest d1 d2 d3 d4
(integer) 4
127.0.0.1:6379[1]> SMEMBERS dest
1) "d3"
2) "d4"
3) "d2"
4) "d1"

# 将集合src中的元素"s1"移动到集合dest中
127.0.0.1:6379[1]> SMOVE src dest s1
(integer) 1
127.0.0.1:6379[1]> SMEMBERS src
1) "s4"
2) "s3"
3) "s2"
127.0.0.1:6379[1]> SMEMBERS dest
1) "d3"
2) "d4"
3) "d2"
4) "d1"
5) "s1"
```

> S 

```shell
# 在集合key1插入元素
127.0.0.1:6379[1]> SADD key1 a b c d e f
(integer) 6
127.0.0.1:6379[1]> SMEMBERS key1
1) "d"
2) "c"
3) "a"
4) "e"
5) "b"
6) "f"

# 在集合key2中插入元素
127.0.0.1:6379[1]> SADD key2 a b x y z
(integer) 5
127.0.0.1:6379[1]> SMEMBERS key2
1) "x"
2) "y"
3) "b"
4) "a"
5) "z"

# SDIFF key1 key2   求集合key1和集合key2的差集 key1 - (key1 ∩ key2)
127.0.0.1:6379[1]> SDIFF key1 key2
1) "e"
2) "f"
3) "c"
4) "d"

# SINTER key1 key2   求集合key1和集合key2的交集
127.0.0.1:6379[1]> SINTER key1 key2
1) "a"
2) "b"

# SUNION key1 key2   求集合key1和集合key2的并集
127.0.0.1:6379[1]> SUNION key1 key2
1) "y"
2) "a"
3) "c"
4) "e"
5) "b"
6) "f"
7) "d"
8) "z"
9) "x"
```

应用：微博，A用户将所有关注的人放在一个Set集合中！将A用户的粉丝也放在一个集合中！算出两个用户的共同关注！

## 3.6.Hash(哈希)

> 基本操作

```shell
# Hash就是Map集合 表述为key-map,map存储的是键值对！
# Redis Hash ---> Map<String, Map<Object, Object>>

# HSET key field value [field value ...]
# 1、HSET user1 id 001 name zhangsan age 18  设置user1的字段id、name、age的值
127.0.0.1:6379[1]> HSET user1 id 001 name zhangsan age 18
(integer) 3

# 2、HGET user1 id   获取user1字段id的值
127.0.0.1:6379[1]> HGET user1 id
"001"
127.0.0.1:6379[1]> HGET user1 name
"zhangsan"
127.0.0.1:6379[1]> HGET user1 age
"18"

# 3、HMGET user1 id name age   批量获取user1的字段的值
127.0.0.1:6379[1]> HMGET user1 id name age
1) "001"
2) "zhangsan"
3) "18"

# 4、HGETALL user1   获取user1所有的字段和对应的值
127.0.0.1:6379[1]> HGETALL user1
1) "id"
2) "001"
3) "name"
4) "zhangsan"
5) "age"
6) "18"

# 5、HKEYS user1    获取user1所有的字段
127.0.0.1:6379[1]> HKEYS user1
1) "id"
2) "name"
3) "age"



# 6、HDEL user1 id age    删除user1指定的字段
127.0.0.1:6379[1]> HDEL user1 id age
(integer) 2
127.0.0.1:6379[1]> HGETALL user1
1) "name"
2) "zhangsan"

# 7、HLEN user1   查看user1字段的个数
127.0.0.1:6379[1]> HLEN user1
(integer) 1

# 8、HEXISTS user1 name    查看user1的name字段是否存在
127.0.0.1:6379[1]> HEXISTS user1 name
(integer) 1

# 9、HVALS user1    获得user1所有的值
127.0.0.1:6379[1]> HVALS user1
1) "zhangsan"
```

> HINCREBY

```shell
# 设置user2的字段age的值为2
127.0.0.1:6379[1]> HSET user2 age 2
(integer) 1

# 将字段age的值增加3
127.0.0.1:6379[1]> HINCRBY user2 age 3
(integer) 5
127.0.0.1:6379[1]> HGET user2 age
"5"

# 将字段age的值减1
127.0.0.1:6379[1]> HINCRBY user2 age -1
(integer) 4
127.0.0.1:6379[1]> HGET user2 age
"4"
```

> HSETNX

```shell
# HSETNX user2 id 001 如果user不存在id字段就设置id的值
127.0.0.1:6379[1]> HSETNX user2 id 001
(integer) 1

```

## 3.7.Zset(有序集合)

> 基本操作

```shell
# Zset是在Set的基础上，增加了一个值，set k1 v1; zset k1 score1 v1

# 1、ZADD zset01 3 three 2 two 1 one  在有序集合zset01中添加元素
127.0.0.1:6379[1]> ZADD zset01 3 three 2 two 1 one
(integer) 3

# 2、查看集合zset01中的所有元素
127.0.0.1:6379[1]> ZRANGE zset01 0 -1
1) "one"
2) "two"
3) "three"

# 3、ZREM zset01 two  移除有序集合zset01的元素"two"
127.0.0.1:6379[1]> ZRANGE zset01 0 -1
1) "one"
2) "two"
3) "three"
127.0.0.1:6379[1]> ZREM zset01 two
(integer) 1
127.0.0.1:6379[1]> ZRANGE zset01 0 -1
1) "one"
2) "three"

# 4、ZCARD zset01  获取有序集合zset01元素的个数
127.0.0.1:6379[1]> ZCARD zset01
(integer) 2

# 5、ZCOUNT zset02 2 3  获取有序集合zset02指定区间的成员数量 
127.0.0.1:6379[1]> ZADD zset02 1 hello
(integer) 1
127.0.0.1:6379[1]> ZADD zset02 2 world 3 Ringo
(integer) 2
127.0.0.1:6379[1]> ZCOUNT zset02 2 3
(integer) 2

```

> ZRANGEBYSCORE和ZREVRANGEBYSCORE

```shell
127.0.0.1:6379[1]> ZADD salary 2500 XiaoHong
(integer) 1
127.0.0.1:6379[1]> ZADD salary 5000 ZhangSan
(integer) 1
127.0.0.1:6379[1]> ZADD salary 500 Ringo
(integer) 1

# 1、ZRANGEBYSCORE salary -inf +inf 按照score排序 从最小值到最大值
# ZRANGEBYSCORE key min max 必须是从小到大排列
127.0.0.1:6379[1]> ZRANGEBYSCORE salary -inf +inf
1) "Ringo"
2) "XiaoHong"
3) "ZhangSan"

# 2、ZRANGEBYSCORE salary -inf +inf withscores 排序结果带上分数
127.0.0.1:6379[1]> ZRANGEBYSCORE salary -inf +inf withscores
1) "Ringo"
2) "500"
3) "XiaoHong"
4) "2500"
5) "ZhangSan"
6) "5000"

# 3、ZREVRANGEBYSCORE salary +inf -inf 按照score 从大到小进行排序
127.0.0.1:6379[1]> ZREVRANGEBYSCORE salary +inf -inf
1) "ZhangSan"
2) "XiaoHong"
3) "Ringo"
```

# 4.三种特殊的数据类型

## 4.1.Geospatial(地理位置)

> GEOADD

```shell
# 1、GEOADD china:city 116.397128 39.916527 "beijing"  添加城市数据
# 规则：有效的经度从-180度到180度。有效的纬度从-85.05112878度到85.05112878度
127.0.0.1:6379> GEOADD china:city 116.397128 39.916527 "beijing" 
(integer) 1
127.0.0.1:6379> GEOADD china:city 121.48941 31.40527 "shanghai"
(integer) 1
127.0.0.1:6379> GEOADD china:city 117.30983 39.71755 "tianjin"
(integer) 1
127.0.0.1:6379> GEOADD china:city 106.54041 29.40268 "chongqing"
(integer) 1

# 2、GEO本质是Zset 我们可以使用Zset命令来操作GEO
127.0.0.1:6379> TYPE china:city
zset

# 3、GEOPOS 返回china:city中所有城市的经度维度，返回的是一个数组
127.0.0.1:6379> GEOPOS china:city beijing shanghai tianjin chongqing
1) 1) "116.39712899923324585"
   2) "39.91652647362980844"
2) 1) "121.48941010236740112"
   2) "31.40526993848380499"
3) 1) "117.30983108282089233"
   2) "39.71755086262169954"
4) 1) "106.54040783643722534"
   2) "29.40268053517299762"

```

> GEODIST

```shell
# GEODIST china:city beijing shanghai km  显示beijing和shanghai的距离(单位:km)
127.0.0.1:6379> GEODIST china:city beijing shanghai km
"1052.1056"
```

> GEORADIUS

```shell
# 以石家庄(114.53952,38.03647)为中心在china:city集合中搜索半径为500km的城市
127.0.0.1:6379> GEORADIUS china:city 114.53952 38.03647 500 km
1) "tianjin"
2) "beijing"

# withcoord：返回经纬度
# withdist：返回中心的距离
# count：返回匹配到的N个城市
# asc：升序排列 desc：降序排列
127.0.0.1:6379> GEORADIUS china:city 114.53952 38.03647 500 km withcoord withdist count 2 desc
1) 1) "tianjin"
   2) "304.1166"
   3) 1) "117.30983108282089233"
      2) "39.71755086262169954"
2) 1) "beijing"
   2) "263.6661"
   3) 1) "116.39712899923324585"
      2) "39.91652647362980844"

```

> GEORADIUSBYMEMBER

```shell
# GEORADIUSBYMEMBER可以找出位于指定范围内的元素,但是 GEORADIUSBYMEMBER 的中心点是由给定的位置元素决定的,而不是像 GEORADIUS 那样使用输入的经度和纬度来决定中心点
127.0.0.1:6379> GEORADIUSBYMEMBER china:city beijing 1000 km withcoord withdist asc
1) 1) "beijing"
   2) "0.0000"
   3) 1) "116.39712899923324585"
      2) "39.91652647362980844"
2) 1) "tianjin"
   2) "81.0536"
   3) 1) "117.30983108282089233"
      2) "39.71755086262169954"
```

应用场景：可以找到附近的人！！！

## 4.2.HyperLogLog(基数统计)

> 什么是基数？

A{1,3,5,7,8,7}

B{1,3,5,7,8}

**基数就是不重复的元素个数。集合A基数是5！**

> 简介

- Redis2.8.9就更新了`HyperLogLog`数据结构！
- `HyperLogLog`基数统计的算法。
- 优点：`HyperLogLog`在Redis中占用内存是固定的，2^64不同元素的基数，只需要12KB内存。
- 场景：可以统计网页的访问量，同一个用户访问多次算作一次。

> 基本使用

```shell
# 创建第一组元素key1
127.0.0.1:6379> PFADD key1 a b c d e f
(integer) 1
127.0.0.1:6379> PFCOUNT key1
(integer) 6

# 创建第二组元素key2
127.0.0.1:6379> PFADD key2 a b i o x y z
(integer) 1
127.0.0.1:6379> PFCOUNT key2
(integer) 7

# 将key1和key2合并到key3
127.0.0.1:6379> PFMERGE key3 key1 key2
OK
127.0.0.1:6379> PFCOUNT key3
(integer) 11
```

## 4.3.Bitmaps(位存储)

> 位存储

统计用户信息，活跃，不活跃！登录、未登录！打卡，365天打卡！两个状态的，都可以使用`Bitmaps`！

`Bitmaps`位图，数据结构！都是操作二进制位来进行记录，就只有0和1两种状态！

> 基本使用

```shell
# 1、使用bitmaps可以记录一周的打卡情况
127.0.0.1:6379> SETBIT sign 0 1
(integer) 0
127.0.0.1:6379> SETBIT sign 1 0
(integer) 0
127.0.0.1:6379> SETBIT sign 2 1
(integer) 0
127.0.0.1:6379> SETBIT sign 3 1
(integer) 0
127.0.0.1:6379> SETBIT sign 4 1
(integer) 0
127.0.0.1:6379> SETBIT sign 5 0
(integer) 0
127.0.0.1:6379> SETBIT sign 6 1
(integer) 0

# 2、查看某天是否打卡
127.0.0.1:6379> GETBIT sign 1
(integer) 0

# 3、统计打卡的天数
127.0.0.1:6379> BITCOUNT sign
(integer) 5
```

# 5.Redis事务

> Redis事务简介

- **Redis单条命令是保证原子性的，但是Redis的事务是不保证原子性的！**
- **Redis事务没有隔离级别的概念。**
- **Redis事务：**
  - **开启事务(MULTI)**
  - **命令入队**
  - **执行事务(EXEC)**

> 正常执行事务

```shell
# 1、开启事务
127.0.0.1:6379> MULTI
OK

# 2、命令入队
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED

# 执行事务
127.0.0.1:6379> EXEC
1) OK
2) OK
3) "v2"
4) OK
```

> DISCARD取消事务

```shell
# DISCARD取消事务 入队中的命令都不会执行

127.0.0.1:6379> MULTI 
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED

# 取消事务
127.0.0.1:6379> DISCARD
OK
127.0.0.1:6379> get k4
(nil)
```

> 编译时异常(代码有问题！命令有错误！)，Redis事务中所有的命令都不会执行

```shell
# 1、开启事务
127.0.0.1:6379> MULTI
OK

# 2、命令入队
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED

# 3、这里set命令出错了！
127.0.0.1:6379> set k3
(error) ERR wrong number of arguments for 'set' command

# 4、结果所有的命令都不会执行
127.0.0.1:6379> exec 
(error) EXECABORT Transaction discarded because of previous errors.
```

> 运行时异常(1/0)，如果事务队列中存在运行时错误，那么执行命令时，完全正确的命令是可以正常执行的

```shell
# 1、开启事务
127.0.0.1:6379> MULTI
OK

# 2、命令入队
127.0.0.1:6379> LPUSH list01 1 2 3 4 5
QUEUED
127.0.0.1:6379> LRANGE list01 0 -1
QUEUED

# 3、这里数组下标越界了！
127.0.0.1:6379> LSET list01 6 "update"
QUEUED
127.0.0.1:6379> EXEC
1) (integer) 5
2) 1) "5"
   2) "4"
   3) "3"
   4) "2"
   5) "1"
3) (error) ERR index out of range

# 4、虽然事务中有错误，但是并没有影响其他命令的执行
# Redis事务不能保证原子性
127.0.0.1:6379> LRANGE list01 0 -1
1) "5"
2) "4"
3) "3"
4) "2"
5) "1"
```

# 6.Redis实现乐观锁

## 6.1.悲观锁和乐观锁

- **悲观锁**：认为什么时候都会出现问题，无论什么时候都会加锁。
- **乐观锁**：认为什么时候都不会出现问题，所以不会上锁！更新数据的时候判断一下，是否有人修改过这个数据！
  - 获取version。
  - 更新的时候比较version。

## 6.2.Redis监控测试

> 正常执行成功

```shell
# 1、设置余额有100元
127.0.0.1:6379> SET money 100
OK
# 2、设置支出0元
127.0.0.1:6379> SET out 0
OK
# 3、监控money
127.0.0.1:6379> WATCH money
OK

# 4、开启事务
127.0.0.1:6379> MULTI
OK

# 5、余额减去20
127.0.0.1:6379> DECRBY money 20
QUEUED
# 6、支出增加20
127.0.0.1:6379> INCRBY out 20
QUEUED

# 7、执行事务，事务正常结束
127.0.0.1:6379> EXEC
1) (integer) 80
2) (integer) 20
```

> 测试多线程修改值，使用watch可以当做Redis乐观锁操作

```shell
# WATCH 标记所有指定的key 被监视起来，在事务中有条件的执行（乐观锁）。
# 刷新一个事务中已被监视的所有key。如果执行EXEC 或者DISCARD， 则不需要手动执行UNWATCH 。

127.0.0.1:6379> WATCH money # 监视 money
OK
127.0.0.1:6379> MULTI
OK
127.0.0.1:6379> DECRBY money 10
QUEUED
127.0.0.1:6379> INCRBY out 10
QUEUED
127.0.0.1:6379> EXEC # 执行事务之前，有另外一个线程修改了我们的值，这个时候就会导致事务提交失败！！！
(nil)
```

# 7.Jedis操作Redis

## 7.1.Jedis简介

- Jedis是Redis官方推荐的Java连接开发工具！

- Jedis使用Java操作Redis的中间件。如果使用Java来操作Redis，那么一定要对Jedis十分熟悉！

  

```shell
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
</dependency>
```

## 7.2.Jedis操作

### 7.2.1.测试Jedis连接Redis

> 依赖

```xml
<dependency>
    <groupId>redis.clients</groupId>
    <artifactId>jedis</artifactId>
    <version>2.9.0</version>
    <exclusions>
        <exclusion>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-pool2</artifactId>
    <version>2.7.0</version>
</dependency>
```

> 测试连接

```java
import redis.clients.jedis.Jedis;
import com.ymy.jedis.support.RedisSupport;

public class TestPing {
    public static void main(String[] args) {
        // 1、new Jedis 对象即可
        Jedis jedis = new Jedis(RedisSupport.HOST, RedisSupport.PORT);
        // 2、Jedis有Redis所有的指令
        System.out.println(jedis.ping());   // PONG 测试连通
    }
}
```

### 7.2.2.Jedis事务

```java
package com.ymy.jedis;

import com.ymy.jedis.support.RedisSupport;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TestTX {
    public static void main(String[] args) {
        // 1、创建Redis的连接
        Jedis jedis = new Jedis(RedisSupport.HOST, RedisSupport.PORT);
        jedis.flushDB();

        // 2、开启事务
        Transaction transaction = jedis.multi();
        try {
            // 3、命令入队
            System.out.println(transaction.lpush("list01", "hello", "world", "ringo", "tangs"));
            System.out.println(transaction.set("money", "100"));
            System.out.println(transaction.sadd("set01", "a", "b", "c"));
            // 4、执行事务
            List<Object> ret = transaction.exec();
            System.out.println(ret);
        } catch (Exception e) {
            // 放弃事务
            transaction.discard();
        } finally {
            // 5、查看结果
            System.out.println("列表list01：" + jedis.lrange("list01", 0, -1));
            System.out.println("key money的值：" + jedis.get("money"));
            System.out.println("集合set01：" + jedis.smembers("set01"));
            jedis.close();
        }
    }
}
```

## 7.3.SpringBoot整合Redis

说明：在SpringBoot2.x之后，原来使用的Jedis被替换成了lettuce。

Jedis：采用的是直连，多个线程操作的话，是不安全的，如果想要避免不安全，使用Jedis pool连接池。

lettuce：采用netty，实例可以在多个线程中进行共享，不存在线程不安全的情况！可以减少线程数据了，更像NIO模式。

### 7.3.1.RedisTemplate基础API

> 依赖

```xml
<!--web-->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!--redis-->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

> 源码分析

```java
@Bean
@ConditionalOnMissingBean(
    name = {"redisTemplate"}  // 我们可以自定义RedisTemplate来替换这个默认的
)
public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    // 默认的RedisTemplate没有过多的设置，Redis对象都需要序列化
    // 两个泛型都是Object类型，我们以后使用需要强制转换
    RedisTemplate<Object, Object> template = new RedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}

@Bean
@ConditionalOnMissingBean  // 由于String是Redis最常使用的数据类型 所以说单独提出来 StringRedisTemplate
public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
    StringRedisTemplate template = new StringRedisTemplate();
    template.setConnectionFactory(redisConnectionFactory);
    return template;
}
```

> 配置Redis

```yaml
spring:
  application:
    name: redis-02-spring-boot
# 配置Redis
  redis:
    database: 0 # 默认是0
    host: 39.97.3.60 # 默认是localhost
    port: 6379 # 默认是6379
```

> 测试RedisTemplate的API

```java
package com.ymy.spring.boot.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redisTemplate.opsForValue()：操作String(字符串)
     * redisTemplate.opsForList()：操作List(列表)
     * redisTemplate.opsForSet()：操作Set(集合)
     * redisTemplate.opsForZSet()：操作ZSet(有序集合)
     * redisTemplate.opsForHash()：操作Hash(哈希)
     * redisTemplate.opsForGeo()：操作GEO(地理位置)
     * redisTemplate.opsForHyperLogLog()：操作HyperLogLog(基数统计)
     * redisTemplate.opsForValue().setBit()：操作BitMap(位图)
     * <p>
     * redisTemplate.getConnectionFactory().getConnection().flushDb(); 清空数据库
     * redisTemplate.getConnectionFactory().getConnection().keys(); 获得所有的key 需要将byte[]数组转成String
     */
    @Test
    public void testRedisTemplate() {
        redisTemplate.opsForValue().set("name", "RingoTangs");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
```

### 7.3.2.自定义RedisTemplate

> RedisTemplate源码

```java
private boolean enableDefaultSerializer = true;
@Nullable
private RedisSerializer keySerializer = null;
@Nullable
private RedisSerializer valueSerializer = null;
@Nullable
private RedisSerializer hashKeySerializer = null;
@Nullable
private RedisSerializer hashValueSerializer = null;

public void afterPropertiesSet() {
    super.afterPropertiesSet();
    boolean defaultUsed = false;
    
    // 1、如果没有指定默认的系列化方式就会使用JDK自带的序列化方式
    if (this.defaultSerializer == null) {
        this.defaultSerializer = new JdkSerializationRedisSerializer(this.classLoader != null ? this.classLoader : this.getClass().getClassLoader());
    }

    // 2、enableDefaultSerializer 默认是开启的！
    if (this.enableDefaultSerializer) {
        
        // 3、如果Redis的key的序列化没有指定，那么就用默认的序列化机制
        if (this.keySerializer == null) {
            this.keySerializer = this.defaultSerializer;
            defaultUsed = true;
        }

        // 4、如果Redis的value的序列化没有指定，那么就用默认的序列化机制
        if (this.valueSerializer == null) {
            this.valueSerializer = this.defaultSerializer;
            defaultUsed = true;
        }

        // 5、如果Redis的Hash数据类型的key的序列化没有指定，那么就用默认的序列化机制
        // 这里的key指的是 Hash中key1-key2-value 中的key2
        if (this.hashKeySerializer == null) {
            this.hashKeySerializer = this.defaultSerializer;
            defaultUsed = true;
        }

        // 6、如果Redis的Hash数据类型的value的序列化没有指定，那么就用默认的序列化机制
        if (this.hashValueSerializer == null) {
            this.hashValueSerializer = this.defaultSerializer;
            defaultUsed = true;
        }
    }

    if (this.enableDefaultSerializer && defaultUsed) {
        Assert.notNull(this.defaultSerializer, "default serializer null and not all serializers initialized");
    }

    if (this.scriptExecutor == null) {
        this.scriptExecutor = new DefaultScriptExecutor(this);
    }

    this.initialized = true;
}
```

> 自定义的RedisTemplate

```java
/**
 * @author Ringo
 * @date 2021/4/22 11:35
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        // 1: 字符串序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 2: 对象序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);

        // 解决 Java 8 LocalDateTime 不能反序列化问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        om.registerModule(new JavaTimeModule());
        jackson2JsonRedisSerializer.setObjectMapper(om);

        // 3: 设置 string key value hashKey hashValue 的序列化器
        template.setStringSerializer(stringRedisSerializer);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
```

# 8.Redis配置文件

> NETWORK

```shell
bind 127.0.0.1           # 绑定的端口

protected-mode yes       # 保护模式

port 6379                # 端口
```

> GENERAL

```shell
daemonize no            # 以守护进程的方式运行，默认是no

pidfile /var/run/redis_6379.pid    # 如果以后台运行，需要指定pid文件

# 日志
# debug (a lot of information, useful for development/testing)
# verbose (many rarely useful info, but not a mess like the debug level)
# notice (moderately verbose, what you want in production probably)
# warning (only very important / critical messages are logged)
loglevel notice

logfile ""      # 日志文件位置名

databases 16    # 数据库的数量，默认16个

always-show-logo yes    # 是否总是显示log
```

> SNAPSHOTTING

```shell
# 900S内，至少有1个key被修改，Redis进行持久化操作
save 900 1

# 300S内，至少有10个key被修改，Redis进行持久化操作
save 300 10

# 60S内，至少有1W个key被修改，Redis进行持久化操作
save 60 10000


stop-writes-on-bgsave-error yes     # 持久化出错，Redis是否还要继续工作，默认是yes

rdbcompression yes                  # 是否压缩rdb文件，需要消耗CPU的资源，默认是yes

rdbchecksum yes                     # 保存rdb文件的时候，进行错误的检查

dir ./                              # rdb文件保存的目录！
```

> SECURITY

```shell
# 可以为Redis设置密码，默认没有密码

# 配置文件这里可以设置密码
# IMPORTANT NOTE: starting with Redis 6 "requirepass" is just a compatiblity
# layer on top of the new ACL system. The option effect will be just setting
# the password for the default user. Clients will still authenticate using
# AUTH <password> as usually, or more explicitly with AUTH default <password>
# if they follow the new protocol: both will work.
#
requirepass 123456

# 命令行可以设置密码
127.0.0.1:6379> config set requirepass "123456"
OK
127.0.0.1:6379> config get requirepass
1) "requirepass"
2) "123456"
127.0.0.1:6379> PING
(error) NOAUTH Authentication required.
127.0.0.1:6379> AUTH 1234
OK
```

> CLIENTS

```shell
maxclients 10000   # 设置能连上Redis的最大客户端数量
```

> MEMORY MANAGEMENT

```shell
maxmemory <bytes>                    # 设置Redis最大的内存容量
maxmemory-policy noeviction          # 内存达到上限时的处理策略

# 内存达到上限时的6种处理策略
# volatile-lru -> Evict using approximated LRU, only keys with an expire set.
# allkeys-lru -> Evict any key using approximated LRU.
# volatile-random -> Remove a random key having an expire set.
# allkeys-random -> Remove a random key, any key.
# volatile-ttl -> Remove the key with the nearest expire time (minor TTL)
# noeviction -> Don't evict anything, just return an error on write operations.
```

> APPEND ONLY MODE

```shell
appendonly no            # aof持久化存储，默认不开启aof，使用rdb存储，在大部分情况下rdb已经够用了！
appendfilename "appendonly.aof"   # aof文件的名字

# appendfsync always     # 每次修改都会同步
appendfsync everysec     # 每秒都同步一次
# appendfsync no         # 不同步
```

# 9.Redis持久化

Redis是内存数据库，如果不将内存中的数据库状态保存到磁盘，那么一旦服务器进程退出，服务器中的数据库状态也会消失。所以Redis提供了持久化功能！

## 9.1.RDB(Redis DataBase)

> RDB基本介绍

![RDB](https://img-blog.csdnimg.cn/20200801140759756.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1JyaW5nb18=,size_16,color_FFFFFF,t_70)

- RDB持久化是指在指定的时间间隔内将内存中的数据集快照写入磁盘，实际操作过程是fork一个子进程，先将数据集写入临时文件，写入成功后，再替换之前的文件，用二进制压缩存储。
- **RDB保存的文件是dump.rdb。**

> RDB触发机制

- 配置文件中`save`规则满足的情况下，会自动触发rdb规则。
- 执行`flushall`命令，也会触发我们的rdb规则。
- 退出Redis，也会产生rdb文件。

> 如何恢复rdb文件？

只需要将rdb文件放在Redis启动目录就可以了，Redis启动的时候会自动检查`dump.rdb`文件，恢复其中的数据。

```shell
# 查看dump.rdb需要存放的目录
127.0.0.1:6379> CONFIG GET dir
1) "dir"
2) "/data"
```

> RDB的优缺点

- 优点：
  - 适合大规模的数据恢复。
  - 対数据的完整性要求不高。
- 缺点：
  - 需要一定的时间间隔进程操作！如果Redis意外宕机了，最后一次修改数据就没有了。
  - fork进程的时候，会占用一定的内存空间。

## 9.2.AOF(Append Only File)

> AOF基本介绍

![AOF](https://img-blog.csdnimg.cn/20200801141125975.png)

- AOF将我们所有的命令都记录下来，恢复的时候就把这个文件全部在执行一遍。
- **AOF只记录写的操作。**
- `redis.conf`配置文件中修改`appendonly yes`就开启了AOF持久化存储。
- 如果AOF文件内容出错，Redis是无法启动的！Redis给我们提供了工具`redis-check-aof --fix`

```shell
# redis-check-aof 修复aof文件
[root@Ringo bin]# ./redis-check-aof --fix /root/redis/data/appendonly.aof 
0x              9d: Expected prefix '*', got: 'a'
AOF analyzed: size=173, ok_up_to=157, diff=16
This will shrink the AOF from 173 bytes, with 16 bytes, to 157 bytes
Continue? [y/N]: y
Successfully truncated AOF
```

> AOF优越点

```shell
appendonly no            # aof持久化存储，默认不开启aof，使用rdb存储，在大部分情况下rdb已经够用了！
appendfilename "appendonly.aof"   # aof文件的名字

# appendfsync always     # 每次修改都会同步
appendfsync everysec     # 每秒都同步一次
# appendfsync no         # 不同步
```

- 优点：
  - 每一次修改都同步，文件的完整性会更好。
  - 每秒同步一次，可能会丢失一秒的数据。
  - 从不同步，效率最高。

- 缺点：
  - 相对于数据文件来说，aof远远大于rdb，修复的速度也比rdb慢。
  - AOF运行效率也要比rdb慢，所以Redis默认的配置就rdb持久化。

> AOF重写规则说明

```shell
no-appendfsync-on-rewrite nono-appendfsync-on-rewrite nono-appendfsync-on-rewrite nono-appendfsync-on-rewrite nono-appendfsync-on-rewrite nono-appendfsync-on-rewrite nono-appendfsync-on-rewrite no
auto-aof-rewrite-percentage 100
auto-aof-rewrite-min-size 64mb
```

- AOF默认的就是文件无限追加，文件越来越大！
- 如果aof文件大于64MB，Redis会fork一个新的进程来将我们的文件进行重写。

## 9.3.小结

- RDB持久化方式能够在指定的时间间隔内対数据进行快照存储。。。。。。
- AOF持久化方式记录每次対Redis写的操作，当服务器重启的时候会重新执行这些命令来恢复原始的数据，AOF命令以Redis协议追加保存每次写的操作到文件末尾，Redis还能対AOF文件进行后台重写，使得AOF文件的体积不会太大。
- **Redis只做缓存，可以不用任何持久化方式。**
- 同时开启RDB和AOF两种持久化方式：
  - 在这种情况下，当Redis重启的时候会优先载入AOF文件来恢复原始的数据，因为在通常情况下AOF文件保存的数据集要比RDB文件保存的数据集要完整。
  - RDB的数据不实时，同时使用两者时服务器重启也只会找AOF文件，那要不要只使用AOF文件呢？作者建议不要，因为RDB更适合用于备份数据库。
- 性能建议：
  - 因为RDB文件只用作后备用途，建议只在Slave上持久化RDB文件，而且只要15分支备份一次就够了，只保留`save 900`这条规则。
  - 如果开启AOF持久化存储，好处是在最恶劣的情况下也只会丢失不超过2秒的数据，重启Redis比较简单，只需要加载AOF文件即可。代价一是带来了持续的IO，二是AOF rewrite的最后将rewrite过程中产生的新数据写到新文件造成的阻塞是不可避免的。只要硬盘许可，应该尽量减少AOF rewrite的频率，AOF重写的默认值64M太小了，可以设置到5G以上，默认超过原大小100%大小重写可以改到适当的数值。
  - 如果不开启AOF，紧靠主从复制实现高可用性也可以，能省去一大笔IO，也减少了rewrite时带来的系统波动。代价是如果Master-Slave同时倒掉，会丢失十几分钟的数据，启动Redis也要比较Master-Slave中的rdb文件，载入较新的那个，微博就是这种架构。

# 10.Redis主从复制

## 10.1.主从复制简介

<img src="http://p99.pstatp.com/large/pgc-image/1535954351656917bb056c9" alt="Redis主从"  />



- 主从复制，读写分离！80%的情况下都是在进行读操作，减轻服务器的压力，架构中经常使用。
- 最低配置Redis集群至少需要3台Redis。

## 10.2.主从复制作用

- 数据冗余：主从复制实现了数据的热备份，是持久化之外的一种数据冗余方式。
- 故障恢复：当主节点出现问题时，可以由从节点提供服务，实现快速的故障恢复；实际上是一种服务的冗余。
- 负载均衡：在主从复制的基础上，配合读写分离，可以由主节点提供写服务，从节点提供读服务，分担服务器负载。
- 高可用：主从复制是哨兵和集群能够实时的基础，因此主从复制是Redis高可用的基础！

## 10.3.环境配置

```shell
# 1、Docker启动slave1
docker run -p 6380:6379 --name redis-slave1 \
-v /root/redis-slave1/data:/data \
-v /root/redis-slave1/conf/redis.conf:/etc/redis/redis.conf \
-d redis:6.0.5 redis-server /etc/redis/redis.conf

# 2、Docker启动slave2
docker run -p 6381:6379 --name redis-slave2 \
-v /root/redis-slave2/data:/data \
-v /root/redis-slave2/conf/redis.conf:/etc/redis/redis.conf \
-d redis:6.0.5 redis-server /etc/redis/redis.conf

# 3、INFO replication  查看Redis主机状态
# 默认情况下，每一台Redis服务器都是主节点！！！！
127.0.0.1:6379> INFO replication
role:master       # 角色 master
connected_slaves:0  # 当前没有从机
master_replid:1e3b734fb1636080fa017841303f3d98e33a7d15
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:0
second_repl_offset:-1
repl_backlog_active:0
repl_backlog_size:1048576
repl_backlog_first_byte_offset:0
repl_backlog_histlen:0
```

## 10.4.主从配置：一主两从

- **默认情况下每台Redis服务器都是主节点！**
- 一般情况下，我们只配置从机就可以了！`6379`端口的Redis设为主机；`6380、6381`端口的Redis设为从机！

> Redis客户端命令配置

```shell
# Redis命令配置的主从是临时的！
# SLAVEOF host port 在从机Redis上设置Redis主机的地址和端口
# 端口6379的Redis Docker容器地址为172.18.0.2

# 1、设置6380端口的Redis从机
127.0.0.1:6379> SLAVEOF 172.18.0.2 6379
OK
127.0.0.1:6379> INFO replication
# Replication
role:slave
master_host:172.18.0.2
master_port:6379
master_link_status:up
master_last_io_seconds_ago:10
master_sync_in_progress:0
slave_repl_offset:14
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:34eb49159fa1dc3613e65019ce3b4ff4d1e54f8f
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:14
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:14

# 2、设置6381端口的Redis从机
127.0.0.1:6379> SLAVEOF 172.18.0.2 6379
OK
127.0.0.1:6379> INFO replication
# Replication
role:slave
master_host:172.18.0.2
master_port:6379
master_link_status:up
master_last_io_seconds_ago:4
master_sync_in_progress:0
slave_repl_offset:28
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:34eb49159fa1dc3613e65019ce3b4ff4d1e54f8f
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:28
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:15
repl_backlog_histlen:14


# 3、查看端口6379的主机状态
127.0.0.1:6379> INFO replication
# Replication
role:master
connected_slaves:2
slave0:ip=172.18.0.5,port=6379,state=online,offset=14,lag=0
slave1:ip=172.18.0.6,port=6379,state=online,offset=14,lag=0
master_replid:34eb49159fa1dc3613e65019ce3b4ff4d1e54f8f
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:14
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:14
```

> redis.conf配置

```shell
# 在Redis配置文件中配置主从是永久的！

#   +------------------+      +---------------+
#   |      Master      | ---> |    Replica    |
#   | (receive writes) |      |  (exact copy) |
#   +------------------+      +---------------+
#
# 1) Redis replication is asynchronous, but you can configure a master to
#    stop accepting writes if it appears to be not connected with at least
#    a given number of replicas.
# 2) Redis replicas are able to perform a partial resynchronization with the
#    master if the replication link is lost for a relatively small amount of
#    time. You may want to configure the replication backlog size (see the next
#    sections of this file) with a sensible value depending on your needs.
# 3) Replication is automatic and does not need user intervention. After a
#    network partition replicas automatically try to reconnect to masters
#    and resynchronize with them.
#
replicaof 172.18.0.2 6379

# 从机只能读不能写！主机可以写可以读
# Note: read only replicas are not designed to be exposed to untrusted clients
# on the internet. It's just a protection layer against misuse of the instance.
# Still a read only replica exports by default all the administrative commands
# such as CONFIG, DEBUG, and so forth. To a limited extent you can improve
# security of read only replicas using 'rename-command' to shadow all the
# administrative / dangerous commands.
replica-read-only yes
```

> 注意事项

```shell
# 这里讨论的是配置文件配置的Redis主从

# 1、Redis从机只能读不能写！主机可以写可以读！

# 2、一主二从的默认情况下，Master宕机了，Slave还是Slave，需要手动配置主机才可以！
# Redis主机宕机后 从机的状态 从机还是只能读不能写
127.0.0.1:6379> INFO replication
role:slave
master_host:172.18.0.2
master_port:6379
master_link_status:down
master_last_io_seconds_ago:-1
master_sync_in_progress:0
slave_repl_offset:1550
master_link_down_since_seconds:19
slave_priority:100
slave_read_only:1
connected_slaves:0
master_replid:be766c8e8935f20b49aefb74e3691f189f2434cc
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:1550
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:1550
127.0.0.1:6379> set k3 v3
(error) READONLY You can't write against a read only replica.

# 3、一主二从的默认情况下，Master又重新启动了，原来主从模式又恢复了！

# 4、redis-slave1宕机了！原来的一主二从就变成了一主一从
# 主机状态
127.0.0.1:6379> INFO replication
# Replication
role:master
connected_slaves:1
slave0:ip=172.18.0.6,port=6379,state=online,offset=196,lag=1
master_replid:b6cadea87c25c20297f4615fba2b023a32e2e6c1
master_replid2:0000000000000000000000000000000000000000
master_repl_offset:196
second_repl_offset:-1
repl_backlog_active:1
repl_backlog_size:1048576
repl_backlog_first_byte_offset:1
repl_backlog_histlen:196

# 5、redis-slave1重新启动，又恢复了连接，redis-slave1会迅速同步主机的数据！
```

## 10.5.主从复制原理

- Slave启动成功连接到master后会发送一个sync同步命令。
- Master接到命令后，启动后台的存盘进程，同时收集所有接收到的用于修改数据集命令，在后台进程执行完毕之后，Master将传送整个数据文件到Slave，并完成一次完全同步。
- 复制的形式：
  - **全量复制**：Slave第一次连接到Master后，当Slave服务在接收到主机的数据后，将数据存盘并加载到内存中。
  - **增量复制**：Master继续将新的所有收集到的修改命令依次传给Slave，完成同步。
- **只要Slave重新连接到Master，一次完全同步（全量复制）将自动执行！主机的数据一定可以在从机中读到！**

## 10.6.哨兵模式：自动选举

> 概述

主从切换技术的方法是：当主服务器宕机后，需要手动把一台服务器设置为主服务器，这就需要人工干预，费时费力，还会造成一段时间内服务不可用。这不是推荐的方式，更多时候，我们优先考虑哨兵模式。Redis从2.8开始正式提供了`Sentinel`哨兵架构来解决这个问题！

**哨兵模式是一种特殊的模式，首先Redis提供了哨兵的命令，哨兵是一个独立的进程，作为进程，它会独立运行。其原理是哨兵通过发送命令，等待Redis服务器响应，从而监控运行的多个Redis实例。**

<img src="https://upload-images.jianshu.io/upload_images/11320039-57a77ca2757d0924.png?imageMogr2/auto-orient/strip|imageView2/2/w/507/format/webp" alt="哨兵模式" style="zoom:150%;" />

> 启动哨兵

```shell
# 哨兵配置文件最主要的配置
# How many replicas we can reconfigure to point to the new replica simultaneously
# during the failover. Use a low number if you use the replicas to serve query
# to avoid that all the replicas will be unreachable at about the same
# time while performing the synchronization with the master.
sentinel monitor mymaster 172.18.0.2 6379 1


# docker启动哨兵
# 再设置哨兵之前要设置好Redis的主从复制
docker run -p 26379:26379 --name redis-sentinel \
-v /root/redis-sentinel/data:/data \
-v /root/redis-sentinel/conf/sentinel.conf:/etc/redis/sentinel.conf \
-d redis:6.0.5 redis-sentinel /etc/redis/sentinel.conf
```

> 哨兵优缺点

- 优点：
  - 哨兵集群，基于主从复制模式，所有的主从配置优点，它全有。
  - 主从可以切换，故障可以转移，系统的可用性就会更好！
  - **哨兵模式就是主从模式的升级，从手动到自动，更加健壮！**

- 缺点：
  - Redis不好在线扩容，集群容量一旦到达上限，在线扩容就十分麻烦！
  - 实现哨兵模式的配置其实是很麻烦的，里面有很多选择！

> sentinel.conf

```shell
# 1、哨兵sentinel实例运行的默认端口 26379
port 26379

# 2、哨兵sentinel的工作目录
dir "/tmp"

# 3、哨兵sentinel监控的redis主节点的ip port
# master-name 可以自己命名主节点名字
# quorm 配置多少个sentinel统一认为master主节点失联 那么这时客观上认为主节点失联了
# sentinel monitor <master-name> <ip> <redis-port> <quorm>
sentinel monitor mymaster 172.18.0.2 6379 1

# 4、当Redis实例中开启了requirepass foobared授权密码 这样所有连接Redis实例的客户端都需要提供密码
# 设置哨兵sentinel 连接主从的密码 注意必须为主从设置一样的密码
# sentinel auth-pass <master-name> <password>
sentinel auth-pass mymaster MySUPER--secret-0123passw0rd

# 5、指定多少毫秒后 主节点没有应答哨兵sentinel 此时 哨兵主观上认为主节点下线 默认30S
# sentinel down-after-milliseconds <master-name> <milliseconds>
sentinel down-after-milliseconds mymaster 30000

# 6、在发生故障转移的时候最多可以有多少个slave同时对新的master进行同步
# 这个数字越小，完成故障转移所需的时间就越长
# 但是如果这个数字越大，就意味着，多的slave因为replication而不可用
# 通常将这个值设置为 1 来保证每次只有一个slave处于不能处理命令请求的状态
# sentinel parallel-syncs <master-name> <numreplicas>
sentinel parallel-syncs mymaster 1

# 7、故障转移的超时时间 failover-timeout 可以用在一下这些方面
# (1) 同一个sentinel 対同一个master两次failover之间的间隔时间。
# (2) 当一个slave从一个错误的master那里同步数据开始计算时间。直到slave被纠正为向正确的master那里同步数据时。
# (3) 当想要取消一个正在进行failover所需要的时间。
# (4) 当进行failover时，配置所有slaves指向新的master所需的最大时间。不过，即使过了这个超时，slaves依然会被正确配置为指向master，但是就不按parallel-syncs所配置的规则来了
# 默认是3分钟
# sentinel failover-timeout <master-name> <milliseconds>
sentinel failover-timeout mymaster 180000
```

# 11.Redis缓存穿透和雪崩

Redis缓存的使用，极大的提升了应用程序的性能和效率，特别是数据查询方面。但同时，也带来了一些问题。其中，最要害的问题，就是数据一致性的问题，从严格意义上讲，这个问题无解。如果对数据的一致性要求很高，那么就不能使用缓存。另外的一些典型问题就是，缓存穿透、缓存雪崩和缓存击穿。目前，业界也都有比较流行的解决方案。

## 11.1.缓存穿透(查不到)

> 概念

**缓存穿透**：假设用户想要查询一个数据，发现Redis内存数据库没有，也就是缓存没有命中，于是向关系型数据库查询。发现关系型数据库中也没有，于是本次查询失败。当用户很多的时候，缓存都没有命中，于是请求都到了持久层数据库。这会给持久层数据库造成很大的压力。这时候就相当于出现了缓存穿透。

> 解决方案

**布隆过滤器**：布隆过滤器是一种数据结构，対所有可能查询的参数以hash形式存储，在控制层先进行校验，不符合则丢弃，从而避免了対底层存储系统的查询压力。

## 11.2.缓存击穿(高并发,缓存过期)

> 概念

这里注意缓存击穿和缓存穿透的区别，缓存击穿，是指一个key非常热点，在不停的扛着高并发，高并发集中対这一个点进行访问，当这个key在失效瞬间，持续的高并发就穿破缓存，直接请求数据库，就像在屏障上凿开了一个洞。

当某个key在过期的瞬间，有大量的请求并发访问，这数据一般是热点数据，由于缓存过期，会同时访问数据库来查询最新数据，并且写回缓存，会导致数据库瞬间压力增大！

> 解决方案

**1.设置热点数据永不过期**

从缓存层来看，没有设置过期时间，所以不会出现热点key过期后产生的问题。

**2.加互斥锁**

分布式锁：使用分布式锁，保证对于每个key同时只有一个线程去查询后端微服务，其他线程没有获得分布式锁的权限，因此只需要等待即可。这种方式将高并发的压力转移到了分布式锁，因此对分布式锁的考验极大。

## 11.3.缓存雪崩(缓存集体失效)

> 概念

缓存雪崩，是指在某一个时间段，缓存集体过期失效。

产生雪崩的原因之一，比如马上就要到双十一零点，很快就会迎来一波抢购，这波商品时间比较集中的放入了缓存，假设缓存一个小时。那么到了凌晨一点钟的时候，这批商品的缓存就都过期了。而对这批商品的访问查询，都落到了数据库上，对于数据库而言，就会产生周期性的压力波峰。于是所有请求都会达到存储层，存储层的调用量会暴增，造成存储层会挂掉的情况。

> 解决方案

**Redis高可用**

这个思想的含义是，既然Redis有可能挂掉，那么就多增加几台Redis服务器，这样一台服务器挂掉之后其他的还可以继续工作，其实就是搭建集群(异地多活)。

**限流降级**

SpringCloud的服务降级，SpringCloudAlibaba的服务限流。

**数据预热**

在即将发生高并发访问前手动触发加载缓存不同的key，设置不同的过期时间，让缓存失效的时间尽量均匀！