

## 1.SpringCloudAlibaba简介

### 1.1.为什么会出现SpringCloudAlibaba？

- 2018年12月份宣布SpringCloudNetflix项目进入了维护模式。。。。


### 1.2.SpringCloudAlibaba带来了什么？

- 服务限流降级：默认支持 WebServlet、WebFlux, OpenFeign、RestTemplate、Spring Cloud Gateway, Zuul, Dubbo 和 RocketMQ 限流降级功能的接入，可以在运行时通过控制台实时修改限流降级规则，还支持查看限流降级 Metrics 监控。
- 服务注册与发现：适配 Spring Cloud 服务注册与发现标准，默认集成了 Ribbon 的支持。
- 分布式配置管理：支持分布式系统中的外部化配置，配置更改时自动刷新。
- 消息驱动：基于 Spring Cloud Stream 为微服务应用构建消息驱动能力。
- 分布式事务：使用 @GlobalTransactional 注解， 高效并且对业务零侵入地解决分布式事务问题。
- 阿里云对象存储：阿里云提供的海量、安全、低成本、高可靠的云存储服务。支持在任何应用、任何时间、任何地点存储和访问任意类型的数据。
- 分布式任务调度：提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。同时提供分布式的任务执行模型，如网格任务。网格任务支持海量子任务均匀分配到所有 Worker（schedulerx-client）上执行。
- 阿里云短信服务：覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

### 1.3.SpringAlibaba学习资源网址

- **参考资料：https://github.com/alibaba/spring-cloud-alibaba/blob/master/README-zh.md**
- **SpringCloudAlibaba官网：https://spring.io/projects/spring-cloud-alibaba**

### 1.4.SpringCloudAlibaba相关组件

- **[Sentinel](https://github.com/alibaba/Sentinel)**：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

- **[Nacos](https://github.com/alibaba/Nacos)**：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。

- **[RocketMQ](https://rocketmq.apache.org/)**：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。

- **[Dubbo](https://github.com/apache/dubbo)**：Apache Dubbo™ 是一款高性能 Java RPC 框架。

- **[Seata](https://github.com/seata/seata)**：阿里巴巴开源产品，一个易于使用的高性能微服务分布式事务解决方案。

- **[Alibaba Cloud ACM](https://www.aliyun.com/product/acm)**：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。

- **[Alibaba Cloud OSS](https://www.aliyun.com/product/oss)**: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。

- **[Alibaba Cloud SchedulerX](https://help.aliyun.com/document_detail/43136.html)**: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。

- **[Alibaba Cloud SMS](https://www.aliyun.com/product/sms)**: 覆盖全球的短信服务，友好、高效、智能的互联化通讯能力，帮助企业迅速搭建客户触达通道。

## 2.Nacos服务注册与配置中心

### 2.1.Nacos简介

> 为什么叫Nacos？

Nacos的前四个字母分别为Naming和Configuration的前两个字母，最后的s为service。

> Nacos是什么？

- Nacos：Dynamic Naming and Configuration Service。
- **一句话：Nacos就是注册中心 + 配置中心的组合。**

> Nacos能干嘛

- 替代Eureka做服务注册中心。
- 替代Config做服务配置中心。

> Nacos下载地址和参考手册

- **下载地址：https://github.com/alibaba/nacos**
- **参考手册：https://nacos.io/zh-cn/docs/quick-start.html**

### 2.2.安装并运行Nacos

> Nacos的安装并运行

```shell
# 1、本地有Java8的环境
[root@mingyu nacos]# java -version
java version "1.8.0_251"
Java(TM) SE Runtime Environment (build 1.8.0_251-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.251-b08, mixed mode)

# 2、从github上下载Nacos的安装包并解压
tar -zxvf nacos-server-1.1.4.tar.gz

# 3、运行Nacos bin目录下运行startup.sh
sh startup.sh -m standalone # standalone代表着单机模式运行，非集群模式

# 4、确定Nacos运行成功? Nacos默认端口8848
[root@mingyu logs]# lsof -i:8848
COMMAND  PID USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
java    8183 root  168u  IPv4 426553      0t0  TCP *:8848 (LISTEN)

# 5、浏览器访问Nacos
http://主机地址:8848/nacos # 默认的用户名密码都是nacos

# 6、关闭Nacos Server
sh shutdown.sh
```

### 2.3.父工程POM文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ymy</groupId>
    <artifactId>spring-cloud-alibaba</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>spring-cloud-alibaba-payment-9001</module>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <java.version>1.8</java.version>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
    </properties>

    <dependencyManagement>
        <dependencies>
            <!--spring cloud alibaba-->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>2.1.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring cloud Hoxton.SR1-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Hoxton.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--spring boot 2.2.2-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.2.2.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

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

### 2.4.Nacos服务提供者注册

> pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-alibaba</artifactId>
        <groupId>com.ymy</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-alibaba-payment-9001</artifactId>

    <dependencies>
        <!--spring cloud alibaba nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
</project>
```

> application.yml

```yaml
server:
  port: 9001
spring:
  application:
    name: nacos-payment-provider
  cloud:
    # nacos地址和端口的配置 
    nacos:
      discovery:
        server-addr: Nacos服务器地址:8848
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

> 主启动类

```java
package com.ymy.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务的发现
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class, args);
    }
}
```

> 业务类

```java
package com.ymy.spring.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    
    // 获取配置文件中服务的端口
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/port")
    public String getServerPort() {
        return "server port----> " + serverPort;
    }
}
```

### 2.5.Nacos服务消费者和负载

> pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-alibaba</artifactId>
        <groupId>com.ymy</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-alibaba-nacos-consumer-83</artifactId>

    <dependencies>
        <!--spring cloud alibaba nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
</project>
```

> application.yml

```yaml
server:
  port: 83
spring:
  application:
    name: nacos-order-consumer
  cloud:
    nacos:
      discovery:
        server-addr: Nacos服务器地址:8848

# 消费者将要去访问的微服务名称(注册成功进Nacos的微服务提供者) 这个属性是自己写的 不是Spring提供的
service-url:
  nacos-user-service: http://nacos-payment-provider
```

> 主启动类

```java
package com.ymy.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务的发现
public class ConsumerMain83 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain83.class, args);
    }
}
```

> 配置类—将RestTemplate加入到Spring容器并开启Ribbon的负载均衡

```java
package com.ymy.spring.cloud.alibaba.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MainConf {

    /**
     * 将RestTemplate加入到Spring容器
     * 开启负载均衡
     */
    @Bean
    @LoadBalanced // 一定不要忘记@LoadBalanced开启负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

> 业务类—Consumer调用Provider并且提供负载

```java
package com.ymy.spring.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    // 从配置文件中读取到Provider的微服务名称
    @Value("${service-url.nacos-user-service}") 
    private String serverURL;

    @GetMapping("/consumer")
    public String paymentInfo() {
        return restTemplate.getForObject(serverURL + "/payment/nacos/port", String.class);
    }
}
```

### 2.6.Nacos服务注册中心对比

#### 2.6.1.Nacos支持AP和CP模式的切换

- **C是所有节点在同一时间看到的数据是一致的；而A的定义是所有的请求都会收到响应。**

- 一般来说，如果不需要存储服务级别的信息且服务实例是通过nacos-client注册，并且能够保证心跳上限，那么就可以选择AP模式。当前主流的服务如Spring Cloud和Dubbo服务，都适用于AP模式，AP模式为了服务的可用性而减弱了一致性，因此AP模式下只支持注册临时实例。
- 如果需要在服务级别编辑或者存储配置信息，那么CP是必须的，K8S服务和DNS服务则适用于CP模式。CP模式下则支持注册持久化实例，此时则是以Raft协议为集群运行模式，该模式下注册实例前必须先注册服务，如果服务不存在，则会返回错误。
- **Nacos默认是AP模式，切换CP命令如下：**
  - **curl -X PUT '$NACOS_SERVER:8848/nacos/v1/ns/operator/switches?entry=serverMode&value=CP'**

### 2.7.Nacos服务配置中心

#### 2.7.1.Nacos作为配置中心—基础配置

> pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-alibaba</artifactId>
        <groupId>com.ymy</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-alibaba-nacos-config-client-3377</artifactId>

    <dependencies>
        <!--spring cloud alibaba nacos config 和配置中心相关-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
        </dependency>

        <!--spring cloud alibaba nacos discovery 和服务注册相关-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>
</project>
```

> bootstrap.yml

```yaml
# 1、为什么要写两个配置文件？？
#   Nacos和SpringCloudConfig一样，在项目初始化时，要保证先从配置中心进行拉取配置，配置拉取成功之后，才能保证项目的正常启动。

# 2、SpringBoot中配置文件的加载顺序是存在优先级的，bootstrap.yml优先级高于application.yml

# 3、 Nacos dataID格式：${prefix}-${spring.profile.active}.${file-extension}
#  · prefix 默认为 spring.application.name 的值，也可以通过配置项 spring.cloud.nacos.config.prefix来配置。
#  · spring.profile.active 即为当前环境对应的 profile，详情可以参考 Spring Boot文档。 注意：当 spring.profile.active 为空时，对应的连接符 - 也将不存在，dataId 的拼接格式变成 ${prefix}.${file-extension}
#  · file-exetension 为配置内容的数据格式，可以通过配置项 spring.cloud.nacos.config.file-extension 来配置。目前只支持 properties 和 yaml 类型。

# 4、Nacos服务器上配置文件的DataID：global-service-config-dev.yaml

server:
  port: 3377
spring:
  application:
    name: nacos-config-client
  cloud:
    nacos:
      discovery:
        server-addr: 39.97.3.60:8848 # Nacos服务注册中心地址
      config:
        server-addr: 39.97.3.60:8848 # Nacos 服务配置中心地址
        file-extension: yaml # 指定配置文件的类型是yaml
        prefix: global-service-config
```

> application.yml

```yaml
spring:
  profiles:
    active: dev # 代表开发环境
```

> 主启动类

```java
package com.ymy.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务发现
public class ConfigClientMain3377 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3377.class, args);
    }
}
```

> 业务类

```java
package com.ymy.spring.cloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope   // @RefreshScope 支持Nacos的动态刷新功能 这个注解非常关键！！！！
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

> Nacos Config Server上的配置

```markdown
# 1、点击左侧【配置列表】，然后点击页面右侧【加号】，进入新建配置页面。
# 2、DataID：严格遵守${prefix}-${spring.profile.active}.${file-extension}官方提供的格式，必须以.yaml结尾！	使用.yml会报错！
# 3、配置格式选择【YAML】即可。
```

> 测试

```shell
C:\Users\14666>curl localhost:3377/config/info
global config for dev environment version = 1...  # 微服务从Nacos配置中心拉取到的配置信息
```

#### 2.7.2.Nacos作为配置中心—分类配置

> Nacos为什么要有分类配置？

```markdown
# 问题1：实际开发中，通常一个系统会准备dev开发环境、test测试环境、prod生成环境，如何保证指定环境启动时微服务能正确读到Nacos上相应环境的配置文件呢？？？

# 问题2：一个大型的分布式微服务系统会有很多微服务子项目，每个微服务项目又都会有相应的开发环境、测试环境、预发环境、正式环境。。。那么如何对这些微服务配置进行管理呢？？？
```

> Namespcae + Group + DataID 三者关系？ 为什么这么设计？

````markdown
# 1、Namespcae + Group + DataID 是什么？
类似Java中的Package名和类名，最外层的Namespace是可以用于区分部署环境的，Group和DataID逻辑上区分两个目标对象。

# 2、三者情况 默认：Namespace=public，Group=DEFAULT_GROUP，Cluster是DEFAULT。
Nacos默认的命名空间是public，Namespace主要用来实现隔离。
比如说我们现在有三个环境：开发、测试、生产环境，我们就可以创建3个Namespace，不同的Namespace之间是隔离的。

Group默认是DEFAULT_GROUP，Group可以把不同的微服务划分到同一个分组中去。

Service就是微服务，一个Service可以包含多个Cluster(集群)，Nacos默认Cluster是DEFAULT，Cluster是对指定微服务的一个虚拟划分。比如为了容灾，将Service微服务分别部署在杭州机房和广州机房，这时就可以给杭州机房的Service微服务起一个集群名称(HZ)，给广州机房的Service微服务起一个集群名称(GZ)，还可以尽量让一个机房的微服务相互调用，来提升性能。
````

### 2.8.Nacos集群和持久化配置（重要）

#### 2.8.1.Nacos部署说明

```markdown
# 1、Nacos存储简介
默认Nacos使用嵌入式数据库来实现数据的存储。所以，如果启动多个默认配置下的Nacos节点，数据存储一致性是存在问题的。
`**为了解决这个问题，Nacos采用了集中式存储的方式支持集群化部署，目前只支持MySQL存储。**`

# 2、Nacos支持三种部署模式
# 2.1、单机模式-用于测试和单机使用。
# 2.2、集群模式-用于生产环境，确保高可用性。
# 2.3、多集群模式-用于多数据中心场景。
```

#### 2.8.2.Nacos持久化切换配置

```shell
# 官网：https://nacos.io/zh-cn/docs/deployment.html

# Nacos默认自带的是嵌入式数据库Derby。


# Derby到mysql切换步骤配置：（mysql version 5.6.5+）

# 1、再 nacos/conf/ 目录下找到sql脚本 nacos-mysql.sql。

# 2、创建数据库，数据库名字为 nacos_config。

# 3、在 nacos_config 数据库中执行sql脚本到数据库创建表。

# 4、修改conf/application.properties文件，增加支持mysql数据源配置（目前只支持mysql），添加mysql数据源的url、用户名和密码。
spring.datasource.platform=mysql

db.num=1
db.url.0=jdbc:mysql://39.97.3.60:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user=root
db.password=333

# 5、再以单机模式启动nacos，nacos所有写嵌入式数据库的数据都写到了mysql
```

#### 2.8.3.一台Linux服务器搭建Nacos伪集群 

> Nacos集群搭建

```shell
# 1、提前准备
安装1个Nginx、安装3个Nacos(3个或3个以上的Nacos节点才能成为集群)、安装1个mysql。

# 2、Linux服务器上mysql数据库持久化配置(具体步骤建2.8.2)

# 3、复制cluster.conf.example 为cluster.conf
[root@mingyu conf]# cp cluster.conf.example cluster.conf
[root@mingyu conf]# ll
total 56
-rw-r--r-- 1  502 games  1769 Jun 19 17:34 application.properties
-rw-r--r-- 1  502 games   408 Oct 11  2019 application.properties.example
-rw-r--r-- 1 root root     58 Jun 20 13:53 cluster.conf
-rw-r--r-- 1  502 games    58 Oct 11  2019 cluster.conf.example
-rw-r--r-- 1  502 games 20210 Nov  4  2019 nacos-logback.xml
-rw-r--r-- 1  502 games  9788 Oct 11  2019 nacos-mysql.sql
-rw-r--r-- 1  502 games  7196 Oct 11  2019 schema.sql

# 4、修改cluster.conf内容
39.97.3.60:3333
39.97.3.60:4444
39.97.3.60:5555

# 5、编辑Nacos的启动脚本startup.sh，使它能够接受不同的端口。

# 修改前
 57 while getopts ":m:f:s:" opt
 58 do
 59     case $opt in
 60         m)
 61             MODE=$OPTARG;;
 62         f)
 63             FUNCTION_MODE=$OPTARG;;
 64         s)
 65             SERVER=$OPTARG;;
 66         ?)
 67         echo "Unknown parameter"
 68         exit 1;;
 69     esac
 70 done
 
133 echo "$JAVA ${JAVA_OPT}" > ${BASE_DIR}/logs/start.out 2>&1 &
134 nohup $JAVA ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &
135 echo "nacos is starting，you can check the ${BASE_DIR}/logs/start.out"
 

# 修改后
 57 while getopts ":m:f:s:p:" opt
 58 do
 59     case $opt in
 60         m)
 61             MODE=$OPTARG;;
 62         f)
 63             FUNCTION_MODE=$OPTARG;;
 64         s)
 65             SERVER=$OPTARG;;
 66         p)
 67             PORT=$OPTARG;;
 68         ?)
 69         echo "Unknown parameter"
 70         exit 1;;
 71     esac
 72 done

133 echo "$JAVA ${JAVA_OPT}" > ${BASE_DIR}/logs/start.out 2>&1 &
134 nohup $JAVA -Dserver.port=${PORT} ${JAVA_OPT} nacos.nacos >> ${BASE_DIR}/logs/start.out 2>&1 &
135 echo "nacos is starting，you can check the ${BASE_DIR}/logs/start.out"

# 6、Nginx的配置文件，反向代理 + 负载均衡
 
 35     upstream cluster{
 36         server 39.97.3.60:3333;
 37         server 39.97.3.60:4444;
 38         server 39.97.3.60:5555;
 39     }
 40 
 41     server {
 42         listen       1111;
 43         server_name  localhost;
 44 
 45         #charset koi8-r;
 46 
 47         #access_log  logs/host.access.log  main;
 48 
 49         location / {
 50            # root   html;
 51            # index  index.html index.htm;
 52            proxy_pass http://cluster;
 53         }

# 7、启动mysql5.7、Nacos集群、nginx
./startup.sh -p 3333
./startup.sh -p 4444
./startup.sh -p 5555

# 8、测试 http://39.97.3.60:1111/nacos 通过nginx访问nacos集群
```

## 3.Sentinel实现服务熔断与限流

**官网：https://github.com/alibaba/Sentinel/**

### 3.1.Sentinel简介

#### 3.1.1.Sentinel是什么？

随着微服务的流行，服务和服务之间的稳定性变得越来越重要。Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

#### 3.1.2.Sentinel的特性

- **丰富的应用场景**：Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、集群流量控制、实时熔断下游不可用应用等。
- **完备的实时监控**：Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。
- **广泛的开源生态**：Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。
- **完善的 SPI 扩展点**：Sentinel 提供简单易用、完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理、适配动态数据源等。

### 3.2.Sentinel下载安装运行

```shell
# 1、下载地址：https://github.com/alibaba/Sentinel/releases/

# 2、下载下来Sentine是jar，不需要解压安装 但是需要有java环境并且8080端口不能被占用！！！

# 3、后台启动Sentinel
java -jar sentinel-dashboard-1.7.0.jar &

# 4、访问Sentinel控制台
http://39.97.3.60:8080/
```

### 3.3.Sentinel初始化监控

> pom

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring-cloud-alibaba</artifactId>
        <groupId>com.ymy</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-cloud-alibaba-sentinel-service-8401</artifactId>

    <dependencies>
        <!--spring cloud alibaba sentinel 和服务熔断相关-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>

        <!--spring cloud alibaba nacos discovery 和服务注册相关-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--持久化会用到-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>

        <!--web-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!--actuator-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
    </dependencies>

</project>
```

> application.yml

```yaml
server:
  port: 8401
spring:
  application:
    name: cloud-alibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: 39.97.3.60:1111 # Nginx地址反向代理到Nacos集群
    sentinel:
      transport:
        dashboard: localhost:8080 # 配置Sentinel Dashboard浏览器控制台地址 8080会监控8401服务
        port: 8719 # Sentinel默认端口8719
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

> 主启动

```java
package com.ymy.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // 开启服务的发现
public class SentinelMain8401 {
    public static void main(String[] args) {
        SpringApplication.run(SentinelMain8401.class, args);
    }
}
```

> controller

```java
package com.ymy.spring.cloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        return "*********test A*********";
    }

    @GetMapping("/testB")
    public String testB() {
        return "*********test B*********";
    }
}
```

> 测试

```shell
# 1、访问 http:localhost:8401/testA http:localhost:8401/testB

# 2、访问Sentinel控制台 可以看到实时监控折线图
```

### 3.4.Sentinel流量控制规则

#### 3.4.1.流控规则基本介绍

- 资源名：唯一名称，默认请求路径。
- 针对来源：Sentinel可以针对调用者进行限流，填写微服务名，默认default（不区分来源）。
- 阈值类型/单机阈值：
  - **QPS（每秒钟的请求数量）：当调用该api的QPS达到阈值的时候，进行限流。**
  - **线程数：当调用该api的线程数达到阈值的时候，进行限流。**
- 是否集群：不需要集群。
- 流控模式：
  - 直接：api达到限流条件时，直接限流。
  - 关联：当关联的资源达到阈值时，就限流自己。
  - 链路：只记录指定链路上的流量（指定资源从入口资源进来的流量，如果达到阈值，就进行限流）【api级别的针对来源】。
- 流控效果：
  - 快速失败：直接失败，抛异常。
  - Warm Up：根据coldFactor（冷加载因子，默认3）的值，从阈值/coldFactor，经过预热时长，才达到设置的QPS阈值。
  - 排队等待：匀速排队，让请求以均匀的速度通过，阈值类型必须为QPS，否则无效。

#### 3.4.2.流控模式

> 直接（默认）

```shell
# 1、直接 ---> 快速失败 系统默认的

# 2、配置及说明
在Sentinel控制台中点击【簇点链路】，在对应的请求路径上点击【流控】，阈值类型选【QPS】，单机阈值测试填写【1】，流控模式选择【直接】，流控效果选择【快速失败】。

# 3、测试：快速点击http://localhost:8401/testA 结果 "Blocked by Sentinel (flow limiting)"

# 4、思考：直接调用默认报错信息，技术方面OK，但是，是否应该有我们自己的后续处理？
是否应该类似有fallback的兜底方法呢？？

# 5、注意：思考QPS和线程数这两种模式有什么区别？
QPS：看的是该接口的1秒被调用的次数。
线程数：看的是有多少个线程在同时调用该接口。
```

> 关联

```shell
# 1、关联是什么？
当关联的资源达到阈值时，就限流自己；当与A关联的资源B达到阈值后，就限流A自己。
一句话：B惹事，A挂了。

# 2、关联 ---> 直接失败 配置
在Sentinel控制台中点击【簇点链路】，在【/testA】上点击【流控】，阈值类型选【QPS】，单机阈值测试填写【1】，流控模式选择【关联】，关联资源写【/testB】，流控效果选择【快速失败】。

# 3、测试：用Postman等工具发送http://localhost:8401/testB请求，进行压力测试；然后刷新http://localhost:8401/testA，结果："Blocked by Sentinel (flow limiting)"。

# 4、结果发现A挂了！！！
```

> 链路

```shell
# 1、链路是什么？
链路的控制指的就是对一条链路的访问进行控制。

# 2、配置
/testA 和 /testB 都是从 sentinel_web_servlet_context（从Sentinel—Dashboard可以看到） 的节点，添加链路的时候，入口资源填写【sentinel_web_servlet_context】，就可以实现链路的限流。
```

#### 3.4.3.流控效果

> Warm Up（预热）

```shell
# 1、说明
阈值除以coldFactor（默认是3），经过预热时长后才会达到阈值。

# 2、Warm Up配置      
默认coldFactor为3，即请求QPS从(阈值/3)开始，经过多少【预热时长】才逐渐升至设定的QPS阈值。

案例：阈值为10，预热时长为5S。
系统初始化的阈值为10/3=3，即阈值刚开始为3，然后过了5S后阈值逐渐恢复到10。

# 3、应用场景
秒杀系统在开启的瞬间，会有很多流量上来，很可能把系统打死，预热方式就是为了保护系统，可以慢慢的把流量放进来，慢慢的把阈值增长到设置的阈值。
```

> 排队等待

```shell
# 1、说明
匀速排队，让请求以均匀的速度通过，阈值类型必须为QPS，否则无效。

# 2、设置
/testA 每秒允许只有1次请求，超过的话就排队等待，等待的超时时间为20000毫秒。
```

### 3.5.Sentinel服务降级

#### 3.5.1.降级策略基本介绍

- 平均响应时间 (`DEGRADE_GRADE_RT`)：当 1s 内持续进入 N 个请求，对应时刻的平均响应时间（秒级）均超过阈值（`count`，以 ms 为单位），那么在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 `DegradeException`）。注意 Sentinel 默认统计的 RT 上限是 4900 ms，**超出此阈值的都会算作 4900 ms**，若需要变更此上限可以通过启动配置项 `-Dcsp.sentinel.statistic.max.rt=xxx` 来配置。
- 异常比例 (`DEGRADE_GRADE_EXCEPTION_RATIO`)：当资源的每秒请求量 >= N（可配置），并且每秒异常总数占通过量的比值超过阈值（`DegradeRule` 中的 `count`）之后，资源进入降级状态，即在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地返回。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。
- 异常数 (`DEGRADE_GRADE_EXCEPTION_COUNT`)：当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别的，若 `timeWindow` 小于 60s，则结束熔断状态后仍可能再进入熔断状态。

#### 3.5.2.対服务降级的进一步说明

- `Sentinel`熔断降级会在调用链路中某个资源出现不稳定状态时（例如调用超时或异常比例升高），対这个资源的调用进行限制，让请求快速失败，避免影响到其他的资源而导致级联错误。
- 当资源被降级后，在接下来的【降级时间窗】内，対该资源的调用都是自动熔断（默认行为是抛出`DegradeException`）。
- `Sentinel`的断路器是没有半开的状态的，只有以下两种情况：
  - 保险丝合上，可以通过。
  - 保险丝关闭，不能通过。

#### 3.5.3.服务降级策略

> RT(平均响应时间)

```shell
# 1、是什么？
1秒持续进入5个请求 ---> 触发降级(断路器打开) ---> 时间窗口结束 ---> 关闭降级

# 2、配置
降级策略【RT】，RT写【200】(单位：毫秒)，时间窗口写【1】(单位：秒)

我们希望资源的处理速度在200ms内正常，当平均处理时长超过200ms，开启断路器，且该资源在【时间窗口】时间范围内不可用。
```

> 异常比例

```shell
# 1、是什么？
QPS >= 5 && 异常比例(秒级统计)超过阈值 ---> 触发降级(断路器打开) ---> 时间窗口结束 ---> 关闭降级

# 2、配置
降级策略【异常比例】，异常比例写【0.2】(代表20%)，时间窗口写【1】(单位：秒)
```

> 异常数

```shell
# 1、是什么
异常数(分钟统计)超过阈值 ---> 触发降级(断路器打开) ---> 时间窗口结束 ---> 关闭降级
```

### 3.6.热点参数限流

#### 3.6.1.热点基本介绍

何为热点？热点即经常访问的数据。很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制。比如：

- 商品 ID 为参数，统计一段时间内最常购买的商品 ID 并进行限制
- 用户 ID 为参数，针对一段时间内频繁访问的用户 ID 进行限制

**热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效。**

#### 3.6.2.热点限流基本配置

> @SentinelResource注解基本使用

```java
/**
* 热点参数限流业务代码，出问题时，会到testHotKeyHandler()方法中返回友好提示
* value = "testHotKey" 和 @GetMapping("/testHotKey") 区分！！！
* 如果资源名是/testHotKey 那么表示是从URL获取资源！
* 如果资源名是testHotKey 那么表示是热点参数限流了！
*/
@GetMapping("/testHotKey")
@SentinelResource(value = "testHotKey", blockHandler = "testHotKeyHandler")
public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                         @RequestParam(value = "p2", required = false) String p2) {
    return "*********testHotKey*********";
}

/**
* 热点参数兜底方法
*/
public String testHotKeyHandler(String p1, String p2, BlockException e) {
    return "*********testHotKeyHandler o(╥﹏╥)o*********";
}
```

> SentinelDashboard热点参数配置

```shell
# 1、资源名：testHotKey(这里注意不加/)

# 2、限流模式：只支持QPS模式

# 3、参数索引：下标从0开始，填写【0】，对参数p1限流 	这里0指的是@SentinelResource添加的方法参数列表索引

# 4、单机阈值：QPS每秒的请求次数。设为【1】。
```

> 测试

```shell
http://localhost:8401/testHotKey?p1=a&p2=b	# 触发限流

http://localhost:8401/testHotKey?p1=a	# 触发限流

http://localhost:8401/testHotKey	# 不会触发限流

http://localhost:8401/testHotKey?p2=a	# 不会触发限流
```

#### 3.6.3.热点限流参数例外项

> 特例情况

- 普通情况：対`/testHotKey?p1=a`访问超过QPS阈值后，参数`p1`马上被限流。**但是我期望`p1`参数当它是某个特定值时，它的限流和平时不一样。**
- 特例情况：假如当`p1`的值等于5时，它的阈值可以达到200。

> SentinelDashboard参数例外项设置

```shell
# 1、打开【编辑热点规则】，展开高级设置。

# 2、参数类型：java.lang.String

# 3、参数值：5

# 4、限流阈值：200

上述配置的意思是：当参数 p1=5 时，阈值时200，是其他值，阈值是1，请求的URL不带p1参数，不会触发限流。
```

> 测试

```shell
http://localhost:8401/testHotKey?p1=5	# 阈值就变成了200

http://localhost:8401/testHotKey?p1=1	# 阈值是1
```

#### 3.6.4.需要注意的问题

- **@SentinelResource**处理的是Sentinel控制台配置的违规情况，有blockHandler()方法配置的兜底处理。
- **RuntimeException**是Java运行时异常，@SentinelResource不管。
- **一句话：@SentinelResource主管SentinelDashboard配置出错，Java代码运行出错该走异常就走异常。**

### 3.7.系统自适应限流

#### 3.7.1.系统规则的基本介绍

系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。

### 3.8.@SentinelResource注解

#### 3.8.1.按资源名称进行限流

> 测试代码

```java
@GetMapping("/byResource")
@SentinelResource(value = "byResource", blockHandler = "byResourceHandler")
public SimpleResponse byResource() {
    return new SimpleResponse(200, "按资源名称限流OK！", new Payment("1", "serial01"));
}

/**
* 这个就是兜底方法,自定义的限流友好提示
*/
public SimpleResponse byResourceHandler(BlockException e) {
    return new SimpleResponse(444, e.getClass().getName() + "\t 服务不可用！");
}
```

> SentinelDashboard配置

- 资源名：byResource。
- 阈值类型：QPS。
- 单机阈值：1。

> 额外问题

关闭8401服务，刷新SentinelDashboard，发现流控规则消失了。？

#### 3.8.2.按URL地址进行限流

> 测试代码

```java
/**
* 没有兜底方法 将采用系统默认的
*/
@GetMapping("/rateLimit/byUrl")
@SentinelResource(value = "byUrl")
public SimpleResponse byUrl() {
    return new SimpleResponse(200, "按url限流OK！", new Payment("2", "serial02"));
}
```

> SentinelDashboard配置

- 资源名：/rateLimit/byUrl。
- 阈值类型：QPS。
- 单机阈值：1。

#### 3.8.3.上面两种兜底方案面临的问题

- 系统默认的，没有体现出我们自己的业务要求。
- 依照现有条件，我们自定义的处理方法又和业务代码耦合在一起不直观。
- 每个业务方法都添加一个兜底的，那代码膨胀加剧。
- 全局统一的处理方法没有体现。

#### 3.8.4.自定义限流处理逻辑

> 写一个全局兜底类

```java
package com.ymy.spring.cloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ymy.spring.cloud.entity.SimpleResponse;

public class CustomerBlockHandler {
    /**
     * 全局兜底方案
     * 这里一定要static
     */
    public static SimpleResponse handlerException(BlockException e) {
        return new SimpleResponse(444, "按用户自定义,全局兜底方案");
    }
}
```

> 测试代码

```java
/**
* 用户自定义限流处理逻辑
* 限流的时候会去全局兜底的类找指定的方法
*/
@GetMapping("/rateLimit/customerBlockHandler")
@SentinelResource(value = "customerBlockHandler",
                  blockHandlerClass = CustomerBlockHandler.class,
                  blockHandler = "handlerException")
public SimpleResponse customerBlockHandler() {
    return new SimpleResponse(200, "按用户自定义限流OK！", new Payment("3", "serial03"));
}
```

#### 3.8.5.@SentinelSource注解属性说明

`@SentinelResource` 用于定义资源，并提供可选的异常处理和 fallback 配置项。 `@SentinelResource` 注解包含以下属性：

- `value`：资源名称，必需项（不能为空）。
- `entryType`：entry 类型，可选项（默认为 `EntryType.OUT`）。
- `blockHandler` / `blockHandlerClass`: `blockHandler` 对应处理 `BlockException` 的函数名称，可选项。blockHandler 函数访问范围需要是 `public`，返回类型需要与原方法相匹配，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 `BlockException`。blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `blockHandlerClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
- `fallback`/`fallbackClass`：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了`exceptionsToIgnore`里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
  - 返回值类型必须与原函数返回值类型一致；
  - 方法参数列表需要和原函数一致，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
  - fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
- `defaultFallback`（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了`exceptionsToIgnore`里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
  - 返回值类型必须与原函数返回值类型一致；
  - 方法参数列表需要为空，或者可以额外多一个 `Throwable` 类型的参数用于接收对应的异常。
  - defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 `fallbackClass` 为对应的类的 `Class` 对象，注意对应的函数必需为 static 函数，否则无法解析。
- `exceptionsToIgnore`（since 1.6.0）：用于指定哪些异常被排除掉，不会计入异常统计中，也不会进入 fallback 逻辑中，而是会原样抛出。

### 3.9.Sentinel服务熔断

#### 3.9.1.Ribbon系列

> 环境准备

- `spring-cloud-alibaba-nacos-provider-9003` 和`spring-cloud-alibaba-nacos-provider-9004`模块提供服务。
- `spring-cloud-alibaba-nacos-consumer-84`模块使用Ribbon负载均衡来调用下游Provider服务。

> 84业务代码无任何配置会返回ErrorPage

```java
package com.ymy.spring.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${service-url.nacos-user-service}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    @SentinelResource(value = "fallback")
    public SimpleResponse<Payment> consumer(@PathVariable("id") String id) {
        SimpleResponse<Payment> ret = restTemplate.getForObject(url + "/payment/" + id, SimpleResponse.class);
        if ("4".equals(id)) {
            throw new IllegalArgumentException("非法参数异常" + id);
        } else if (ret.getData() == null) {
            throw new NullPointerException("空指针异常");
        }
        return ret;
    }
}
```

> 84业务代码只配置fallback会返回友好提示

```java
package com.ymy.spring.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${service-url.nacos-user-service}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    @SentinelResource(value = "fallback", fallback = "fallbackHandler")   // fallback只负责业务异常
    public SimpleResponse<Payment> consumer(@PathVariable("id") String id) {
        SimpleResponse<Payment> ret = restTemplate.getForObject(url + "/payment/" + id, SimpleResponse.class);
        if ("4".equals(id)) {
            throw new IllegalArgumentException("非法参数异常" + id);
        } else if (ret.getData() == null) {
            throw new NullPointerException("空指针异常");
        }
        return ret;
    }

    /**
     * 兜底异常处理方法
     */
    public SimpleResponse fallbackHandler(@PathVariable("id") String id, Throwable t) {
        Payment payment = new Payment(id, null);
        return new SimpleResponse<>(444, "兜底fallback方法fallbackHandler" + t.getMessage(), payment);
    }
}
```

> 84业务代码只配置blockHandler会返回ErrorPage不能处理业务异常

```java
package com.ymy.spring.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${service-url.nacos-user-service}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler只负责SentinelDashboard控制台配置
    public SimpleResponse<Payment> consumer(@PathVariable("id") String id) {
        SimpleResponse<Payment> ret = restTemplate.getForObject(url + "/payment/" + id, SimpleResponse.class);
        if ("4".equals(id)) {
            throw new IllegalArgumentException("非法参数异常" + id);
        } else if (ret.getData() == null) {
            throw new NullPointerException("空指针异常");
        }
        return ret;
    }

    /**
    * 兜底blockHandler方法
    */
    public SimpleResponse blockHandler(@PathVariable("id") String id, BlockException e) {
        return new SimpleResponse(444, "兜底blockHandler方法 " + id);
    }
}
```

> 84业务代码fallback和blockHandler都配置各回各家 java异常找fallback 流控规则找blockHandler 

```java
package com.ymy.spring.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Value("${service-url.nacos-user-service}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/{id}")
    @SentinelResource(value = "fallback", blockHandler = "blockHandler", fallback = "fallbackHandler")
    public SimpleResponse<Payment> consumer(@PathVariable("id") String id) {
        SimpleResponse<Payment> ret = restTemplate.getForObject(url + "/payment/" + id, SimpleResponse.class);
        if ("4".equals(id)) {
            throw new IllegalArgumentException("非法参数异常" + id);
        } else if (ret.getData() == null) {
            throw new NullPointerException("空指针异常");
        }
        return ret;
    }

    // 兜底SentinelDashboard配置处理方法
    public SimpleResponse blockHandler(@PathVariable("id") String id, BlockException e) {
        return new SimpleResponse(445, "限流 " + id);
    }

    //兜底异常处理方法
    public SimpleResponse fallbackHandler(@PathVariable("id") String id, Throwable t) {
        Payment payment = new Payment(id, null);
        return new SimpleResponse<>(444, "异常处理" + t.getMessage(), payment);
    }
}




```

#### 3.9.2.OpenFeign系列

> 依赖

```xml
<!--open feign-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

> application.yml 激活Feign対Sentinel的支持

````yaml
# 激活Sentinel対Feign的支持
feign:
  sentinel:
    enabled: true
````

> 主启动类添加@EnableFeignClients注解

```java
package com.ymy.spring.cloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ConsumerMain84 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerMain84.class, args);
    }
}
```

> 写OpenFeign接口

```java
package com.ymy.spring.cloud.alibaba.service;

import com.ymy.spring.cloud.alibaba.service.impl.FeignServiceFallback;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-alibaba-provider", fallback = FeignServiceFallback.class)
public interface FeignService {

    // 直接复制Provider的方法即可
    @GetMapping("/payment/{id}")
    public SimpleResponse<Payment> payment(@PathVariable("id") String id);
}
```

> OpenFeign接口实现类fallback

```java
package com.ymy.spring.cloud.alibaba.service.impl;

import com.ymy.spring.cloud.alibaba.service.FeignService;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.stereotype.Component;

@Component
public class FeignServiceFallback implements FeignService {
    @Override
    public SimpleResponse payment(String id) {
        return new SimpleResponse(444444, "openFeign fallback");
    }
}
```

> Controller调用OpenFeign接口

```java
package com.ymy.spring.cloud.alibaba.controller;

import com.ymy.spring.cloud.alibaba.service.FeignService;
import com.ymy.spring.cloud.entity.Payment;
import com.ymy.spring.cloud.entity.SimpleResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OpenFeignController {

    @Resource
    private FeignService feignService;

    @GetMapping("/openFeign/consumer/{id}")
    public SimpleResponse<Payment> consumer(@PathVariable("id") String id) {
        SimpleResponse<Payment> ret = feignService.payment(id);
        return ret;
    }
}
```

> 测试

当服务提供者9003、9004全挂了会触发服务降级兜底的方法fallback。

### 3.10.Sentinel持久化

> 依赖

```xml
<!--sentinel持久化相关-->
<dependency>
    <groupId>com.alibaba.csp</groupId>
    <artifactId>sentinel-datasource-nacos</artifactId>
</dependency>
```

> application.yml添加Nacos数据源

```yaml
server:
  port: 8401
spring:
  application:
    name: cloud-alibaba-sentinel-service
  cloud:
    nacos:
      discovery:
        server-addr: 39.97.3.60:1111 # Nginx地址反向代理到Nacos集群
    sentinel:
      transport:
        dashboard: localhost:8080 # 配置Sentinel Dashboard浏览器控制台地址 8080会监控8401服务
        port: 8719 # Sentinel默认端口8719
      # Sentinel持久化配置
      datasource:
        ds1:
          nacos:
            server-addr: 39.97.3.60:1111
            data-id: ${spring.application.name}
            group-id: DEFAULT_GROUP # 默认设置好了这里可以不写
            data-type: json # 默认设置好了这里可以不写
            rule-type: flow
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

> Nacos的配置

```json
[
    {
        "resource": "/rateLimit/byUrl",
        "limitApp": "default",  
        "grade": 1,
        "count": 1,
        "strategy": 0,
        "controlBehavior": 0,
        "clusterMode": false
    }
]
```

- **resource**：资源名称。
- **limitApp**：来源应用。
- **grade**：阈值类型，0表示线程数，1表示QPS。
- **count**：单机阈值。
- **strategy**：流控模式，0表示直接，1表示关联，2表示链路。
- **controlBehavior**：流控效果，0表示快速失败，1表示Warm Up，2表示排队等待。
- **clusterMode**：是否集群。

> 测试启动8401服务

- 访问`http://localhost:8401/rateLimit/byUrl`SentinelDashboard就有流控规则了！！！

## 4.Seata处理分布式事务

### 4.1.分布式事务的由来

- 分布式之前：单机单库没有这个问题，o(╥﹏╥)o。
- 分布式之后：单体应用被拆分成微服务应用，原来的三个模块被拆分成三个独立的应用，分别使用三个独立的数据源，业务操作需要调用三个服务来完成。此时每个服务内部的数据一致性由本地事务来保证，但是全局的数据一致性问题没办法保证。
- **一句话：一次业务操作需要跨多个数据源或需要跨多个系统进行远程调用，就产生分布式事务。**

### 4.2.Seata简介

#### 4.2.1.Seata是什么？

- Seata是具有高性能和易用性的分布式事务解决方案。
- **官网地址：http://seata.io。**
- Seata术语：
  - `Transaction ID`(XID)：全局唯一事务ID。
  - `TransactionCoordinator(TC)`：事务协调器，维护全局和分支事务的状态，驱动全局事务提交或回滚。
  - `TransactionManager(TM)`：事务管理器，定义全局事务的范围：开始全局事务、提交或回滚全局事务。
  - `ResourceManager(RM)`：资源管理器，管理分支事务处理的资源，与TC交谈以注册分支事务和报告分支事务的状态，并驱动分支事务提交或回滚。
- 处理过程：
  - TM向TC申请开启一个全局事务，全局事务创建成功并生成一个全局唯一的XID。
  - XID在微服务调用链路的上下文中传播。
  - RM向TC注册分支服务，将其纳入XID对应全局事务的管辖。
  - TM向TC发起针对XID的全局提交或回滚决议。
  - TC调度XID下管辖的全部分支事务完成提交或回滚请求。

![Seata分布式事务处理过程](https://camo.githubusercontent.com/0384806afd7c10544c258ae13717e4229942aa13/68747470733a2f2f63646e2e6e6c61726b2e636f6d2f6c61726b2f302f323031382f706e672f31383836322f313534353239363931373838312d32366661626562392d373166612d346633652d386137612d6663333137643333383966342e706e67)

#### 4.2.2.去哪下载？怎么玩？

- **下载地址：https://github.com/seata/seata/releases**
- 怎么玩？
  - 本地`@Transactional`。
  - 全局`@GlobalTransactional`。

- Seata的分布式交易解决方案，我们只需要使用`@GlobalTransactional`注解在业务方法上！

![Seata的分布式交易解决方案](https://camo.githubusercontent.com/b3a71332ae0a91db7f8616286a69b879fcbea672/68747470733a2f2f63646e2e6e6c61726b2e636f6d2f6c61726b2f302f323031382f706e672f31383836322f313534353239363739313037342d33626365376263652d303235652d343563332d393338362d3762393531333564616465382e706e67)

### 4.3.Seata-Server安装

```shell
# 1、seata-server-0.9.0.tar.gz 解压到指定目录并修改conf目录下的file.conf文件
# 主要修改：自定义事务组名称 + 事务日志存储模式为db + 数据库连接信息。
# 连接mySQL5.7把 lib目录下的 mysql驱动包换成 mysql-connector-java-8.0.18.jar

# service模块：自定义事务组名称
 29 service {
 30   #vgroup->rgroup
 31   vgroup_mapping.my_test_tx_group = "fsp_tx_group"	# 这里默认是default改成工作中的工程名字
 32   #only support single node
 33   default.grouplist = "127.0.0.1:8091"
 34   #degrade current not support
 35   enableDegrade = false
 36   #disable
 37   disable = false
 38   #unit ms,s,m,h,d represents milliseconds, seconds, minutes, hours, days, default permanent
 39   max.commit.retry.timeout = "-1"
 40   max.rollback.retry.timeout = "-1"
 41 }

 # store模块：事务日志存储模式为db + 数据库连接信息
  55 store {
 56   ## store mode: file、db
 57   mode = "db"	# 这里默认是file改成db
 58 
 59   ## file store
 60   file {
 61     dir = "sessionStore"
 62 
 63     # branch session size , if exceeded first try compress lockkey, still exceeded throws exceptions
 64     max-branch-session-size = 16384
 65     # globe session size , if exceeded throws exceptions
 66     max-global-session-size = 512
 67     # file buffer size , if exceeded allocate new buffer
 68     file-write-buffer-cache-size = 16384
 69     # when recover batch read size
 70     session.reload.read_size = 100
 71     # async, sync
 72     flush-disk-mode = async
 73   }
 74 
 75   ## database store
 76   db {
 77     ## the implement of javax.sql.DataSource, such as DruidDataSource(druid)/BasicDataSource(dbcp) etc.
 78     datasource = "dbcp"
 79     ## mysql/oracle/h2/oceanbase etc.
 80     db-type = "mysql"
 81     driver-class-name = "com.mysql.cj.jdbc.Driver"	# 连接mySQL5.7需要修改一下
 82     url = "jdbc:mysql://127.0.0.1:3306/seata?useSSL=false&characterEncoding=utf-8&serverTimezone=UTC" 
 83     user = "root"		# 修改数据库的用户名
 84     password = "333"	# 修改数据库的密码
 85     min-conn = 1
 86     max-conn = 3
 87     global.table = "global_table"
 88     branch.table = "branch_table"
 89     lock-table = "lock_table"
 90     query-limit = 100
 91   }

# 2、MySQL5.7数据库新建database名字是seata

# 3、在seata库里建表 建表SQL路径conf/db_store.sql

# 4、修改conf下的 registry.conf 配置文件
  1 registry {
  2   # file 、nacos 、eureka、redis、zk、consul、etcd3、sofa
  3   type = "nacos"	# 默认是file 修改注册进Nacos
  4 
  5   nacos {
  6     serverAddr = "localhost:1111"	# Nacos地址
  7     namespace = ""
  8     cluster = "default"
  9   }
  
# 5、先启动Nacos再启动Seata-Server
# 如果因为内存原因启动失败可以参考：https://www.cnblogs.com/kingxiaozi/p/8321025.html
sh seata-server.sh -p 8091 -h 39.97.3.60
```

### 4.4.订单—库存—账户业务数据库准备

**注：以下步骤都需要先启动Nacos再启动Seata，保证两个都OK！！**

#### 4.4.1.分布式事务业务说明

- 这里我们会创建三个微服务，一个订单服务，一个库存服务，一个账户服务。
- 当用户下单时，会在订单服务中创建一个订单，然后通过远程调用库存服务来扣减下单商品的库存，再通过远程调用账户服务来扣减用户账户里面的余额，最后在订单服务中修改订单状态为已完成。
- 该操作跨越三个数据库，有两次远程调用，很明显会有分布式事务问题。

#### 4.4.2.创建业务数据库

- seata_order：存储订单的数据库。

- seata_storage：存储库存的数据库。

- seata_account：存储账户信息的数据库。

```sql
create database seata_order;
create database seata_storage;
create database seata_account;
```

#### 4.4.3.按照上述的三个库分别建立对应的业务表

> seata_order库下建t_order表

```sql
CREATE TABLE `t_order`(
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
    `count` INT(11) DEFAULT NULL COMMENT '数量',
    `money` DECIMAL(11,0) DEFAULT NULL COMMENT '金额',
    `status` INT(1) DEFAULT NULL COMMENT '订单状态:0:创建中；1:已完结'
)ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
```

> seata_storage库下建t_storage表

```sql
CREATE TABLE `t_storage`(
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_id` BIGINT(11) DEFAULT NULL COMMENT '产品id',
    `total` INT(11) DEFAULT NULL COMMENT '总库存',
    `used` INT(11) DEFAULT NULL COMMENT '已用库存',
    `residue` INT(11) DEFAULT NULL COMMENT '剩余库存'
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

> seata_account库下建t_account表

```sql
CREATE TABLE `t_account`(
	`id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT(11) DEFAULT NULL COMMENT '用户id',
    `total` DECIMAL(10,0) DEFAULT NULL COMMENT '总额度',
    `used` DECIMAL(10,0) DEFAULT NULL COMMENT '已用余额',
    `residue` DECIMAL(10,0) DEFAULT NULL COMMENT '剩余可用额度'
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
```

#### 4.4.4.按照上述的三个库分别建立对应的回滚日志表

```sql
'订单-库存-账户3个库下都需要建立各自的回滚日志表'

'seata的conf目录下db_undo_log.sql 就是回滚日志表的SQL'

drop table `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```

### 4.5.订单—库存—账户业务微服务准备

#### 4.5.1.业务需求

下订单—>减库存—>扣余额—>改订单状态。

#### 4.5.2.订单-库存-账户模块步骤

- 添加POM。

- 修改application.yml。
- 在resource路径下添加`file.conf`。
- 在resource路径下添加`registry.conf`。
- 写数据库映射的实体类。
- Mapper接口和实现。
- Service接口和实现。
- Controller。
- 配置类。
- 主启动类。

#### 4.5.3.Seata之@GlobalTransaction验证

**http://localhost:2001/order/create?userId=1&productId=10&count=5&money=30**

详情请看：spring-cloud-alibaba-seata-order-service-2001模块

#### 4.5.4.Seata原理简介

- TC：Seata服务器。
- TM：`@GlobalTransactional`事务的发起方。
- RM：一个数据库就是一个RM，事务的参与方。