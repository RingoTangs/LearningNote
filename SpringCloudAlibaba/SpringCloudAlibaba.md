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
 42         listen       80;
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

# 8、测试 http://39.97.3.60:80/nacos 通过nginx访问nacos集群
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