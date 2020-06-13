## 1.主流消息中间件的介绍

### 1.1.ActiveMQ

> ActiveMQ的特点

- ActiveMQ是Apache出品，最流行的，能力强劲的开源消息总线，并且完全支持JMS规范的消息中间件。
- 有丰富的API，多种集群架构模式使得ActiveMQ成为业界老牌消息中间件，在中小型企业中应用广泛。
- 面对超大规模并发的时候ActiveMQ会造成阻塞等问题。。？？

### 1.2.Kafka

> Kafka的特点

- Kafka是开源的分布式发布—订阅消息系统，目前属于Apache的顶级项目。
- Kafka追求高吞吐量，一开始的目的就是用于日志收集和传输。
- 0.8版本开始支持复制，不支持事务，対消息重复、丢失、错误没有严格的要求。
- Kafka适合产生大量数据的互联网服务的数据收集业务。

### 1.3.RocketMQ

> RocketMQ特点

- RocketMQ是阿里开源的消息中间件，纯Java开发，有高吞吐量、高可用性、适合大规模分布式系统应用的特点。
- RocketMQ思路取源于Kafka，対消息的可靠传输及事务性做了优化，在阿里内部被广泛用于交易、充值、流计算、消息推送、日志流式处理等场景。
- 但是RocketMQ收费。。？？？？



### 1.4.RabbitMQ

> RabbitMQ特点

- RabbitMQ是使用Erlang语言开发的开源消息队列系统，基于AMQP协议来实现。
- AMQP的主要特征是面向消息、队列、路由（包括点对点和发布—订阅）、可靠性、安全。
- AMQP协议更多用在企业系统内，対数据一致性、稳定性和可靠性要求很高的场景，対性能和吞吐量的要求排在其次。性能和Kafka没发比但是比ActiveMQ要好很多。

## 2.RabbitMQ核心概念以及AMQP协议

### 2.1.互联网大厂为什么选择RabbitMQ？

- RabbitMQ使用Erlang语言来编写的，并且是基于AMQP协议的。
- 开源、性能优秀，稳定性保障。
- 提供 可靠性消息投递模式(confirm)、返回模式(return)。
- 与SpringAMQP完美的整合、API丰富。
- 集群模式丰富，表达式配置，HA模式，镜像队列模型。
- 保障数据不丢失的前提下做到高可靠性、可用性。

### 2.2.RabbitMQ的高性能是如何做到的？

- Erlang语言最初在于交换机领域的架构模式，这样使得RabbitMQ在Broker之间进行数据交互的性能是非常优秀的。
- Erlang的优点：Erlang有着和原生Socket一样的延迟。

### 2.3.什么是AMQP高级协议？

> AMQP协议简介

- AMQP全称：Advanced Message Queuing Protocol 高级消息队列协议。
- AMQP定义：是具有现代特征的二进制协议。是一个提供统一消息服务的应用层标准高级消息队列协议，是应用层协议的一个开放标准，为面向消息的中间件设计。

> AMQP协议模型

<img src="D:\git_workspace\LearningNote\RabbitMQ\image\2.3-1-AMQP协议模型.jpg" alt="AMQP协议模型"  />

### 2.4.AMQP核心概念是什么？

- **Server：**又称作Broker，接受客户端的连接，实现AMQP实体服务。
- **Connection：**连接，应用程序与Broker的网络连接。
- **Channel：**网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可以建立多个Channel，每个Channel代表一个会话任务。
- **Message：**消息。服务器和应用程序之间传送的数据，由Properties和Body组成。Properties可以对消息进行修饰，比如消息的优先级、延迟等高级特性；Body就是消息体内容。
- **Virtual Host：**虚拟主机，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有若干个Exchange和Queue，同一个Virtual Host里面不能有相同名称的Exchange和Queue。
- **Exchange：**交换机，接收消息。根据Routing Key转发消息到绑定的队列。
- **Binding：**Exchange和Queue之间的虚拟连接，Binding中可以包含Routing Key。
- **Routing Key：**一个路由规则，虚拟机可以用它来确定如何路由一个特点消息。
- **Queue：**也成为了Message Queue，消息队列，保存消息并转发给消费者。

### 2.5.RabbitMQ整体架构模型

![RabbitMQ的整体架构图](D:\git_workspace\LearningNote\RabbitMQ\image\2.5-1-RabbitMQ的整体架构图.jpg)





### 2.6.RabbitMQ消息是如何进行流转的？

![RabbitMQ消息流转图](D:\git_workspace\LearningNote\RabbitMQ\image\2.6-1-RabbitMQ消息流转图.jpg)



### 2.7.RabbitMQ安装和使用

**官网地址：**https://www.rabbitmq.com/

**Erlang和RabbitMQ版本对照表：**https://www.rabbitmq.com/which-erlang.html

> 安装Erlang环境

```shell
# 1、安装gcc gcc-c+ Openssl等模块
yum -y install make gcc gcc-c++ kernel-devel m4 ncurses-devel openssl-devel

# 2、安装ncurses
yum -y install ncurses-devel

# 3、下载Erlang安装包
wget http://erlang.org/download/otp_src_18.2.1.tar.gz

# 4、解压


```



### 2.8.命令行和管控台



### 2.9.RabbitMQ消息生产与消费



### 2.10.RabbitMQ交换机详解



### 2.11.RabbitMQ队列、绑定、虚拟主机、消息