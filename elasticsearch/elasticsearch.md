# 1.elasticsearch概述

Elasticsearch 是一个**分布式、高扩展、高实时**的搜索与[数据分析](https://baike.baidu.com/item/数据分析/6577123)引擎。它能很方便的使大量数据具有搜索、分析和探索的能力。它提供**可扩展**的搜索，具有**接近实时**的搜索。ES本身扩展性很好，可以扩展到上百台服务器。ES也使用Java开发并使用Lucene作为核心来实现所有索引和搜索的功能，但是它的目的是通过简单的RESTful API来隐藏Lucene的复杂性，从而让全文检索变得简单。

据国际权威的数据库产品评测机构DB Engines的统计，在2016年1月，ElasticSearch已经超过Solr等，成为排名第一的搜索引擎类应用！

 

**谁在使用ES：**

- 维基百科，类似百度百科，全文检索，高亮，搜索推荐。
- Stack Overflow，国外程序异常论坛。
- GitHub。
- 电商网站，检索商品。
- 国内：站内搜索（电商，招聘，门户），IT系统搜索，数据分析。

# 2.elasticsearch环境

## 2.1.安装elasticsearch

> Linux普通安装

**下载地址：https://www.elastic.co/cn/downloads/elasticsearch**

```shell
# jdk1.8最低要求！elasticsearch支持客户端，界面工具！
# Java开发，elasticsearch的版本和我们之后对应的Java的核心包！版本对应！JDK环境正常

# 1、下载elasticsearch-7.8.0-linux-x86_64.tar.gz，然后解压到指定文件，就可以使用了！

# 2、熟悉elasticsearch目录
- bin                   # 启动文件    
- config                # 配置文件
    log4j2              # 日志配置文件
    jvm.options         # JVM相关配置 如过内存小 修改一下JVM的配置
    elasticsearch.yml   # ElasticSearch配置文件  默认9200端口
- lib                   # 相关jar
- modules               # 功能模块
- plugins               # 插件
- logs                  # 日志

# 3、启动elasticsearch之前的准备工作
# 由于elasticsearch-7.X不能以Root启动elasticsearch，所以需要创建用户
adduser Tangs # 添加用户
passwd Tangs # 设置密码

chown -R Tangs /opt/elasticsearch/elasticsearch-7.8.0/  # Root个用户权限！

# 4、以新的用户到bin目录启动elasticsearch脚本
[Tangs@Ringo bin]$ ./elasticsearch

# 5、测试连接
[root@Ringo elasticsearch-7.8.0]# curl localhost:9200
{
  "name" : "Ringo",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "IS5Y80WYRJOj4AHmIQ32Fw",
  "version" : {
    "number" : "7.8.0",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "757314695644ea9a1dc2fecd26d1a43856725e65",
    "build_date" : "2020-06-14T19:35:50.234439Z",
    "build_snapshot" : false,
    "lucene_version" : "8.5.1",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

> Docker安装

```shell
# 1、需要将elasticsearch的config文件夹拷贝到挂载目录下

# 2、修改elasticsearch.yml 文件
network.host: 0.0.0.0
http.port: 9200

# 3、启动并运行
docker run -e "ES_JAVA_OPTS=-Xms256m -Xmx256m" \
--name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-v /root/elasticsearch/config:/usr/share/elasticsearch/config \
-d elasticsearch:7.8.0

# 4、测试连接
[root@Ringo config]# curl localhost:9200
{
  "name" : "78f3957f5cb9",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "-IsEt9kXQxKemftK6b8RaA",
  "version" : {
    "number" : "7.8.0",
    "build_flavor" : "default",
    "build_type" : "docker",
    "build_hash" : "757314695644ea9a1dc2fecd26d1a43856725e65",
    "build_date" : "2020-06-14T19:35:50.234439Z",
    "build_snapshot" : false,
    "lucene_version" : "8.5.1",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```



## 2.2.安装elasticsearch-head

**下载地址：https://github.com/mobz/elasticsearch-head**

```shell
# 1、安装node.js

# 2、安装grunt
npm install -g grunt-cli

# 查看grunt是否安装成功
grunt -version

# 3、克隆elasticsearch-head项目

# 4、到elasticsearch-head目录下安装依赖
npm install

# 5、运行elasticsearch-head
npm run start

# 6、开启elasticsearch服务端的跨域支持，进入elasticsearch.yml
# 在文件末尾添加如下配置
http.cors.enabled: true
http.cors.allow-origin: "*"
node.master: true
node.data: true

# 取消以下两行的注释
cluster.name: my-application
node.name: node-1

# 7、测试连接
http://localhost:9100/
```

## 2.3.安装kibana

**下载地址：https://www.elastic.co/cn/downloads/kibana**

**Kibana的版本要和elasticsearch版本对应！**

> Docker安装

```shell
# 1、拉取kibana镜像
docker pull kibana:7.8.0

# 2、修改kibana.yml
server.port: 5601
server.name: "kibana"
server.host: "0.0.0.0"
elasticsearch.hosts: ["http://172.18.0.5:9200"]

# 3、运行kibana
docker run --name kibana --privileged=true -p 5601:5601 \
-v /root/kibana/config:/usr/share/kibana/config \
-d kibana:7.8.0

# 4、测试连接
http://39.97.3.60:5601

# 5、kibana汉化 打开kibana.yml
i18n.locale: "zh-CN"   # 重启docker容器这样就可以将kibana汉化了！ 
```

# 3.ES核心概念

> elasticsearch是__面向文档__的 关系型数据库 和 elasticsearch客观对比

| Relational DB    | Elasticsearcg |
| :--------------- | :------------ |
| 数据库(database) | 索引(index)   |
| 表(tables)       | types         |
| 行(rows)         | documents     |
| 字段(columns)    | fields        |

elasticsearch(集群)中可以包含多个索引(数据库)，每个索引中可以包含多个类型(表)，每个类型下又包含多个文档(行)，每个文档中又包含多个字段(列)。

**物理设计：**

elasticsearch在后台把每个索引划分成多个分片，每分分片可以在集群中的不同服务器之间迁移！

一个elasticsearch就是一个集群。

**逻辑设计：**

一个索引类型中，包含多个文档，比如说文档1，文档2。当我们索引一篇文档时，可以通过这样的一个序列找到它：`索引>类型>文档ID`，通过这个组合我们就能索引到某个具体的文档。**注意：ID不必是整数，实际上它是个字符串！**

> 文档

就是我们的一条条数据

```md
user
1      zangsan     18
2      lisi        3
```

之前说elasticsearch是面向文档的，那么就意味着索引和搜索数据的最小单位是文档，elasticsearch中，文档有几个重要的属性：

- 自我包含，一篇文档同时包含字段和对应的值，也就是同时包含key-value。
- 可以是层次型的，一个文档中包含自文档，复杂的逻辑实体就是这么来的！
- 灵活的结构，文档不依赖预先定义的模式，我们知道关系型数据库中，要提前定义字段才能使用，在elasticsearch中，对于字段是非常灵活的，有时候，我们可以忽略该字段，或者动态的添加一个新的字段。

> 类型

类型是文档的逻辑容器，就像关系型数据库一样，表格是行的容器。类型中对于字段的定义成为映射，比如name映射为字符串类型。我们说文档是无模式的，他们不需要拥有映射中所定义的所有字段，比如新增一个字段，那么elasticsearch是怎么做的呢？elasticsearch会自动的将新字段加入映射，但是这个字段的不确定它是什么类型，elasticsearch就开始猜，如果这个值是18，那么elasticsearch就会认为它是整型，但是elasticsearch也可能猜不对，所以最安全的办法的是提前定义好所需要的映射，这点跟关系型数据库殊途同归了，先是定义好字段，然后再使用。

> 索引

就是数据库！

索引是映射类型的容器，elasticsearch中的索引是一个非常大的文档集合。索引存储了映射类型的字段和其他设置。然后它们被存储到了各个分片上。我们来研究下分片是如何工作的。



**物理设计：节点和分片 如何工作**

一个集群至少有一个节点，而一个节点就是一个elasticsearch进程，节点可以有多个索引，如果创建索引，那么索引将会有5个分片(primary shard，又称主分片)构成，每一个主分片会有一个副本（replica shard，又称复制分片）。

<img src="https://upload-images.jianshu.io/upload_images/13236273-fccfde47aa46d285.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp" alt="elasticsearch集群和分片"  />

上图是一个有2个节点的集群，可以直接看到主分片和对应的复制分片都不会在同一个节点内，这样有利于某个节点挂掉了，数据也不会丢失。实际上，一个分片是一个Lucene索引，一个包含__倒排索引__的文件目录，倒排索引的结构使得elasticsearch在不扫描全部文档的情况下，就能告诉你哪些文档包含特定的关键字。不过，倒排索引是什么？

> 倒排索引

elasticsearch使用的是一种称为倒排索引的结构，采用Lucene倒排索引作为底层。这种结构适用于快速的全文检索，一个索引由文档中所有不重复的列表构成，对于每一个词，都有一个包含它的文档列表。例如，现在有两个文档，每个文档包含如下内容：

```shell
Study every day,good good up to forever # 文档1包含的内容
To forever,study every day, good good up # 文档2包含的内容
```

为了创建倒排索引，我们首先要将每个文档拆分成独立的词(或称为词条或者tokens)，然后创建一个包含所有不重复的词条的排序列表，然后列出每个词条出现在哪个文档！

| term    | doc.1 | doc.2 |
| ------- | ----- | ----- |
| Study   | √     | ×     |
| To      | ×     | √     |
| every   | √     | √     |
| forever | √     | √     |
| day     | √     | √     |
| study   | ×     | √     |
| good    | √     | √     |
| every   | √     | √     |
| to      | √     | ×     |
| up      | √     | √     |

现在我们试图搜索to forever，只需要查看包含每个词条的文档

| term    | doc.1 | doc.2 |
| ------- | ----- | ----- |
| to      | √     | ×     |
| forever | √     | √     |
| total   | 2     | 1     |

两个文档都匹配，但是第一个文档比第二个匹配程度更高。如果没有别的条件，现在，这两个包含关键字的都将返回。

# 4.IK分词器插件

## 4.1.什么是IK分词器？

分词：即把一段中文或者别的划分为一个个的关键字，我们在搜索时候会把自己的信息进行分词，会把数据库中或者索引库中的数据进行分词，然后进行一个匹配操作，默认的中文分词是将每个字看成一个词，比如"我喜欢你"会被分为"我"，"喜"，"欢"，"你"，这显然是不符合要求的，所以我们需要安装中文分词器ik来解决这个问题。

如果要使用中文，建议使用ik分词器。

IK提供了两个分词算法：ik_smart和ik_max_word，其中ik_smart为最少切分，ik_max_word为最细粒度划分！

## 4.2.安装IK分词器插件

> Docker安装

**压缩文件下载地址：https://github.com/medcl/elasticsearch-analysis-ik/releases**

```shell
# 注意IK要和ES版本一直

# 1、进入Docker容器执行
./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v7.8.0/elasticsearch-analysis-ik-7.8.0.zip

# 2、查看IK是否安装成功
[root@a2fa79fcb8ef bin]# ./elasticsearch-plugin list
analysis-ik
```

> kibana测试IK

```json
# 1、ik_smart为最少切分
# 测试样例
GET _analyze
{
  "analyzer": "ik_smart",
  "text": "我好喜欢你"
}

# 结果
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "好喜欢",
      "start_offset" : 1,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "你",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "CN_CHAR",
      "position" : 2
    }
  ]
}

# 2、ik_max_word为穷尽词库的可能
# 测试样例
GET _analyze
{
  "analyzer": "ik_max_word",
  "text": "我好喜欢你"
}

# 结果
{
  "tokens" : [
    {
      "token" : "我",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "好喜欢",
      "start_offset" : 1,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 1
    },
    {
      "token" : "喜欢",
      "start_offset" : 2,
      "end_offset" : 4,
      "type" : "CN_WORD",
      "position" : 2
    },
    {
      "token" : "你",
      "start_offset" : 4,
      "end_offset" : 5,
      "type" : "CN_CHAR",
      "position" : 3
    }
  ]
}
```

## 4.3.自定义字典

> 给IK增加字典

```xml 
<!--第一步：在IKAnalyzer.cfg.xml同级目录下新建一个自己的字典ringo.dic并写入内容-->
棠时

<!--找到IKAnalyzer.cfg.xml可以增加自己的字典-->
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
        <comment>IK Analyzer 扩展配置</comment>
        <!--用户可以在这里配置自己的扩展字典 -->
        <entry key="ext_dict">ringo.dic</entry>
         <!--用户可以在这里配置自己的扩展停止词字典-->
        <entry key="ext_stopwords"></entry>
        <!--用户可以在这里配置远程扩展字典 -->
        <!-- <entry key="remote_ext_dict">words_location</entry> -->
        <!--用户可以在这里配置远程扩展停止词字典-->
        <!-- <entry key="remote_ext_stopwords">words_location</entry> -->
</properties>

<!--第三步：重启elasticsearch-->
```

> 测试

```json
# 1、没有增加自己定义的字典之前
# 测试样例
GET _analyze
{
  "analyzer": "ik_smart",
  "text": "棠时"
}

# 结果
{
  "tokens" : [
    {
      "token" : "棠",
      "start_offset" : 0,
      "end_offset" : 1,
      "type" : "CN_CHAR",
      "position" : 0
    },
    {
      "token" : "时",
      "start_offset" : 1,
      "end_offset" : 2,
      "type" : "CN_CHAR",
      "position" : 1
    }
  ]
}

# 2、增加自己自定义的词典并重启elasticsearch之后
# 测试样例
GET _analyze
{
  "analyzer": "ik_smart",
  "text": "棠时"
}

# 结果
{
  "tokens" : [
    {
      "token" : "棠时",
      "start_offset" : 0,
      "end_offset" : 2,
      "type" : "CN_WORD",
      "position" : 0
    }
  ]
}
```

# 5.Rest风格说明

> Rest模板

| method |                     url地址                     |         描述         |
| :----: | :---------------------------------------------: | :------------------: |
|  PUT   |     localhost:9200/索引名称/类型名称/文档id     | 创建文档(指定文档id) |
|  POST  |        localhost:9200/索引名称/类型名称         | 创建文档(随机文档id) |
|  POST  | localhost:9200/索引名称/类型名称/文档id/_update |       修改文档       |
| DELETE |     localhost:9200/索引名称/类型名称/文档id     |       删除文档       |
|  GET   |     localhost:9200/索引名称/类型名称/文档id     |  通过文档id查询文档  |
|  POST  |    localhost:9200/索引名称/类型名称/_search     |     查询所有数据     |

> kibana基础测试

当然不是只有`kibana`可以测试，使用其他软件如`Postman`或者T`alend APT Tester`都可以。

```json
# 测试创建一个索引

# 1、kibana语法
# 当然也可用Postman等软件来测试
PUT /索引名/类型名/文档id
{
    请求体
}

# 2、执行语句
PUT /index1/type/1
{
  "name": "Ringo",
  "age": 18
}

# 3、执行结果
{
  "_index" : "index1",
  "_type" : "type",
  "_id" : "1",
  "_version" : 2,
  "result" : "updated",
  "_shards" : {
    "total" : 2,
    "successful" : 1,
    "failed" : 0
  },
  "_seq_no" : 1,
  "_primary_term" : 1
}

# 4、可以在elasticsearch-head中去查看索引！
```

> 创建索引

```json
PUT /test2
{
  "mappings": {
    "properties": {
      "name": {
        "type": "text"
      },
      "age": {
        "type": "long"
      },
      "birthday": {
        "type": "date"
      }
    }
  }
}
```

# 6.关于文档的基本操作

## 6.1.简单查询

```json
# 创建文档
PUT /ringo/user/1
{
  "name": "RingoTangs",
  "age": 18,
  "describe": "铃铃铃",
  "tags": ["阳光","喜欢篮球","喜欢学习"]
}

# 带参数查询
GET /ringo/user/_search?q=tags:"学"
```

## 6.2.复杂查询

```json
# 1、带上查询条件，默认返回全部字段
GET /ringo/user/_search
{
  "query": {
    "match": {
      "name": "RingoTangs"
    }
  }
}

# 2、查询结果返回指定字段
GET /ringo/user/_search
{
  "query": {
    "match": {
      "name": "RingoTangs"
    }
  },
  "_source": ["name","age"]      # 结果的过滤
}

# 3、通过某个字段进行排序
GET /ringo/user/_search
{
  "query": {
    "match": {
      "name": "RingoTangs"
    }
  },
  "sort": [
    {
      "age": {
        "order": "asc"
      }
    }
  ]
}

# 4、分页查询
GET /ringo/user/_search
{
  "query": {
    "match": {
      "name": "RingoTangs"
    }
  },
  "from": 0, # 从第几个数据开始 数据下标是从0开始
  "size": 1 # 返回多少条数据
}

# 5、must命令多条件查询，所有条件都要符合！等价于and
GET /ringo/user/_search
{
  "query": {
    "bool": {
      "must": [          # must 等价于and
        {
          "match": {
            "name": "RingoTangs"
          }
        },
        {
          "match": {
            "age": 18
          }
        }
      ]
    }
  }
}

# 4、should有一个条件符合即可，等价于or
GET /ringo/user/_search
{
  "query": {
    "bool": {
      "should": [    # should等价于or
        {
          "match": {
            "name": "RingoTangs"
          }
        },
        {
          "match": {
            "age": 3
          }
        }
      ]
    }
  }
}


# 5、must_not查询年龄不是3岁的人
GET /ringo/user/_search
{
  "query": {
    "bool": {
      "must_not": [      # 等价于"非"
        {
          "match": {
            "age": 3
          }
        }
      ]
    }
  }
}

# 6、查询结果添加过滤器
# gt：大于
# gte：大于等于
# lt：小于
# lte：小于等于
GET /ringo/user/_search
{
  "query": {
    "bool": {
      "must": [
        {
          "match": {
            "name": "RingoTangs"
          }
        }
      ],
      "filter": [
        {
          "range": {     # 按照年龄的范围过滤
            "age": {
              "gte": 10,
              "lte": 20
            }
          }
        }
      ]
    }
  }
}

# 7、条件查询 多个条件用空格隔开
# 通过匹配度可以用权重表示
GET /ringo/user/_search
{
  "query": {
    "match": {
      "tags": "喜欢 阳光"
    }
  }
}

# 8、精确查询term  term输入的词不会被分词
# term查询是直接通过倒排索引指定的词条进行精确查找的！
# 注意：keyword类型的字段不会被分词器解析！！！
PUT /testdb
{
  "mappings": {
    "properties": {
      "name": {
        "type": "text"   # text类型会走分词器
      },
      "describe": {
        "type": "keyword"  # keyword不会走分词器，当成一个整体
      }
    }
  }
}

GET /testdb/_search
{
  "query": {
    "term": {
      "describe": "Ringo 每天都要好好学习3"
    }
  }
}

# 9、高亮查询
# 测试样例
GET /testdb/_search
{
  "query": {
    "match": {
      "name": "棠时"
    }
  },
  "highlight": {
    "pre_tags": "<p class='key' style='color:red'>", 
    "post_tags": "</p>", 
    "fields": {
      "name": {}
    }
  }
}

# 结果
"highlight" : {
    "name" : [
        "<p class='key' style='color:red'>棠</p><p class='key' style='color:red'>时</p>每天都要开心"
    ]
}
```

- 基本查询
- 按照条件查询
- 精确匹配
- 区间范围查询
- 匹配字段过滤
- 多条件查询
- 高亮查询

# 7.SpringBoot整合ES

**官方文档地址：https://www.elastic.co/guide/en/elasticsearch/client/index.html**

## 7.1.基本环境和配置

> pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ymy</groupId>
    <artifactId>elastic-search-api</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <!--注意这里要和我们Linux上安装的客户端版本对应-->
        <elasticsearch.version>7.8.0</elasticsearch.version>
    </properties>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.4.RELEASE</version>
    </parent>

    <dependencies>
        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--elasticsearch 注意elasticsearch的jar要和Linux上装的客户端版本对应-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
        </dependency>

        <!--test-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.2.6.RELEASE</version>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

> elasticsearch配置

```java
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConf {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("39.97.3.60",9200)));
        return client;
    }
}
```

## 7.2.关于索引的API操作

```java
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestESAPI {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 1、测试创建索引
     */
    @Test
    public void createIndex() throws Exception {
        // 1、创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("ringo_index");
        // 2、执行创建请求
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        // 3、打印响应
        System.out.println(response);
    }

    /**
     * 2、测试获取索引
     */
    @Test
    public void indexIsExists() throws Exception{
        // 1、获得索引请求
        GetIndexRequest request = new GetIndexRequest("ringo_index");
        // 2、执行请求
        boolean exist = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        // 3、打印结果
        System.out.println(exist);
    }

    /**
     * 3、测试删除索引
     */
    @Test
    public void deleteIndex() throws Exception{
        // 1、删除索引请求
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("ringo_index");
        // 2、执行请求
        AcknowledgedResponse ack = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        // 3、打印ack
        System.out.println(ack.isAcknowledged());
    }

}
```

## 7.3.关于文档的API操作

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymy.elasticsearch.entity.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestESDocAPI {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 1、测试添加文档
     */
    @Test
    public void addDocument() throws Exception {
        // 1、创建对象
        User user = new User("Ringo", "123");
        // 2、创建请求
        IndexRequest indexRequest = new IndexRequest("ringo_index");
        // 3、设置规则
        indexRequest.id("1");
        indexRequest.timeout(TimeValue.timeValueSeconds(100));
        // 4、将我们的对象放入请求
        indexRequest.source(objectMapper.writeValueAsString(user), XContentType.JSON);
        // 5、客户端发送请求
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        //6、获取响应结果
        System.out.println(indexResponse);
        System.out.println(indexResponse.status()); // CREATED 表示创建成功！
    }

    /**
     * 2、测试文档判断是否存在
     * get /ringo_index/_doc/1
     */
    @Test
    public void docIsExists() throws Exception {
        // 1、创建获取文档请求
        GetRequest getRequest = new GetRequest("ringo_index", "1");
        // 2、不获取返回的 _source的上下文
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        // 3、执行请求
        boolean ret = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        // 4、打印结果
        System.out.println(ret);
    }

    /**
     * 3、测试获取文档信息
     */
    @Test
    public void getDocsInfo() throws Exception {
        // 1、创建获取文档请求
        GetRequest getRequest = new GetRequest("ringo_index", "1");
        // 2、执行请求
        GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        // 3、打印结果
        System.out.println(getResponse);
        System.out.println(getResponse.getSourceAsString());
    }

    /**
     * 4、测试更新文档
     */
    @Test
    public void updateDocsInfo() throws Exception {
        // 1、创建更新文档请求
        UpdateRequest updateRequest = new UpdateRequest("ringo_index", "1");
        updateRequest.timeout(TimeValue.timeValueSeconds(2));
        // 2、创建更新的对象
        User user = new User("Ringo", "abc");
        // 3、将更新的数据放入请求
        updateRequest.doc(objectMapper.writeValueAsString(user), XContentType.JSON);
        // 4、执行请求
        UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
        // 5、打印结果
        System.out.println(updateResponse.status());
    }

    /**
     * 5、测试删除文档
     */
    @Test
    public void deleteDoc() throws Exception {
        // 1、创建删除文档请求
        DeleteRequest deleteRequest = new DeleteRequest("ringo_index", "1");
        deleteRequest.timeout("2s");
        // 2、执行请求
        DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        // 3、打印结果
        System.out.println(deleteResponse.status());
    }

    /**
     * 6、批量插入数据
     */
    @Test
    public void batchAddDocs() throws Exception {
        // 1、创建批量添加请求
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        // 2、创建数据
        List<User> users = new ArrayList<>();
        users.add(new User("zs", "111"));
        users.add(new User("ls", "222"));
        users.add(new User("ww", "333"));
        users.add(new User("zl", "444"));
        // 3、将数据放入到请求中
        for (int i = 0; i < users.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("ringo_index")
                            .id((i + 1) + "")
                            .source(objectMapper.writeValueAsString(users.get(i)),XContentType.JSON)
            );
        }
        // 4、执行请求
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        // 5、打印结果
        System.out.println(bulkResponse.status());
    }

    /**
     * 7、查询
     */
    @Test
    public void search() throws Exception{
        // 1、创建查询请求
        SearchRequest searchRequest = new SearchRequest("ringo_index");
        // 2、添加搜索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 可以使用QueryBuilders来实现
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("username", "s");
        searchSourceBuilder.query(matchQueryBuilder);
        // 设置分页
        searchSourceBuilder.from();
        searchSourceBuilder.size();
        // 设置查询超时时间
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(60));

        // 3、执行请求
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 4、打印结果
        System.out.println(objectMapper.writeValueAsString(searchResponse.getHits()));
        // 遍历hits
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit: hits) {
            System.out.println(hit.getSourceAsMap());
        }
    }
}
```
