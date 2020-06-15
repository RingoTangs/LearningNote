[TOC]

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

- Server：又称作Broker，接受客户端的连接，实现AMQP实体服务。
- Connection：连接，应用程序与Broker的网络连接。
- Channel：网络信道，几乎所有的操作都在Channel中进行，Channel是进行消息读写的通道。客户端可以建立多个Channel，每个Channel代表一个会话任务。
- Message：消息。服务器和应用程序之间传送的数据，由Properties和Body组成。Properties可以对消息进行修饰，比如消息的优先级、延迟等高级特性；Body就是消息体内容。
- Virtual Host：虚拟主机，用于进行逻辑隔离，最上层的消息路由。一个Virtual Host里面可以有若干个Exchange和Queue，同一个Virtual Host里面不能有相同名称的Exchange和Queue。
- Exchange：交换机，接收消息。根据Routing Key转发消息到绑定的队列。
- Binding：Exchange和Queue之间的虚拟连接，Binding中可以包含Routing Key。
- Routing Key：一个路由规则，虚拟机可以用它来确定如何路由一个特点消息。
- Queue：也成为了Message Queue，消息队列，保存消息并转发给消费者。

### 2.5.RabbitMQ整体架构模型

![RabbitMQ的整体架构图](D:\git_workspace\LearningNote\RabbitMQ\image\2.5-1-RabbitMQ的整体架构图.jpg)





### 2.6.RabbitMQ消息是如何进行流转的？

![RabbitMQ消息流转图](D:\git_workspace\LearningNote\RabbitMQ\image\2.6-1-RabbitMQ消息流转图.jpg)



### 2.7.RabbitMQ安装和使用

**官网地址：https://www.rabbitmq.com/**

**Erlang和RabbitMQ版本对照表：https://www.rabbitmq.com/which-erlang.html**

**相关软件包和资料：链接：https://pan.baidu.com/s/1uFP2YU7xPK2KBkaP4gXvLw** 
**提取码：s6z8**

> 安装Rabbitmq

```shell
# 1、将需要的安装包下载到Linux系统
erlang-18.3-1.el7.centos.x86_64.rpm # Erlang语言安装包
rabbitmq-server-3.6.5-1.noarch.rpm # RabbitMQ安装包
socat-1.7.3.2-1.1.el7.x86_64.rpm # 秘钥

# 2、安装Erlang环境
rpm -ivh erlang-18.3-1.el7.centos.x86_64.rpm

# 3、安装socat
rpm -ivh socat-1.7.3.2-1.1.el7.x86_64.rpm

# 4、安装RabbitMQ
rpm -ivh rabbitmq-server-3.6.5-1.noarch.rpm

# 5、RabbitMQ配置文件的修改
路径： vim /usr/lib/rabbitmq/lib/rabbitmq_server-3.6.5/ebin/rabbit.app  # rabbit.app是json格式的配置文件

42          {loopback_users, [guest]}, # 修改42行 去掉guest两边的引号和尖括号，最后结果和左边代码保持一致
```

> Rabbitmq服务的启动和停止

```shell
# 1、查看Rabbitmq可以使用的命令 
[root@centos-7-test1 ~]# rabbitmq
rabbitmqctl       rabbitmq-plugins  rabbitmq-server

# 2、启动Rabbitmq服务
rabbitmq-server start &  # & 代表后台启动服务

# 启动RabbitMQ服务
[root@centos-7-test1 ~]# rabbitmq-server start &
[1] 3293
[root@centos-7-test1 ~]# 
              RabbitMQ 3.6.5. Copyright (C) 2007-2016 Pivotal Software, Inc.
  ##  ##      Licensed under the MPL.  See http://www.rabbitmq.com/
  ##  ##
  ##########  Logs: /var/log/rabbitmq/rabbit@centos-7-test1.log
  ######  ##        /var/log/rabbitmq/rabbit@centos-7-test1-sasl.log
  ##########
              Starting broker...
 completed with 0 plugins.

# 3、查看Rabbitmq是否启动成功？ 看到下面的结果就证明RabbitMQ启动成功了！  
[root@centos-7-test1 ~]# lsof -i:5672
COMMAND   PID     USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
beam.smp 2586 rabbitmq   52u  IPv6  36396      0t0  TCP *:amqp (LISTEN)

# 4、Rabbitmq服务的停止
rabbitmqctl stop_app

# 5、安装Rabbitmq管理插件，可以通过浏览器控制台访问
rabbitmq-plugins list # 可以查看Rabbitmq提供的插件

rabbitmq-plugins enable rabbitmq_management # 安装Rabbitmq控制台插件 控制台默认端口是15672

# 6、浏览器访问Rabbitmq控制台 默认用户名和密码都是guest
http://192.168.110.133:15672
```

### 2.8.命令行和管控台

> 基础操作

```shell
# 1、启动应用
rabbitmqctl start_app

# 2、关闭应用
rabbitmqctl stop_app

# 3、节点状态
rabbitmqctl status

# 4、添加用户
rabbitmqctl add_user [username] [password]

# 4、列出所有的用户列表
rabbitmqctl list_users

# 5、删除用户
rabbitmqctl delete_user [username]

# 6、清除用户权限
rabbitmqctl clear_permissions -p [vhostpath] [username]

# 7、列出用户权限
rabbitmqctl list_user_permissions [username]

# 8、修改密码
rabbitmqctl change_password [username] [newpassword]

# 9、设置用户权限
rabbitmqctl set_permissions -p [vhostpath] [username] ".*" ".*" ".*"

# 10、创建虚拟主机
rabbitmqctl add_vhost [vhostpath]

# 11、列出所有的虚拟主机
rabbitmqctl list_vhosts

# 12、列出虚拟主机上所有权限
rabbitmqctl list_permissions -p [vhostpath]

# 13、删除虚拟主机
rabbitmqctl delete_vhost [vhostpath]

# 14、查看所有队列消息
rabbitmqctl list_queues

# 15、清除队列里的消息
rabbitmqctl -p [vhostpath] purge_queue blue
```

> 高级操作

```shell
# 1、移除所有数据，要在 rabbitmqctl stop_app 之后使用
rabbitmqctl reset

# 2、组成集群命令
rabbitmqctl join_cluster <clusternode> [--ram]

# 3、查看集群状态
rabbitmqctl cluster_status

# 4、修改集群节点的存储形式
rabbitmqctl change_cluster_node_type disc | ram

# 5、摘除节点
rabbitmqctl forget_cluster_node [--offline]

# 6、修改节点名称
rabbitmqctl rename_cluster_node [oldnode1] [newnode1]...
```

### 2.9.RabbitMQ消息生产与消费

> Pom依赖

```java
<dependency>
	<groupId>com.rabbitmq</groupId>
	<artifactId>amqp-client</artifactId>
    <version>3.6.5</version>
</dependency>
```

> 消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * Rabbitmq 消费者
 * 先启动消费者创建队列，生产者再投递。
 */
public class Consumer {
    public static final String HOST = "192.168.110.133";
    public static final String QUEUE_NAME = "test001";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         * queue：队列的名字。
         * durable：是否持久化。
         * exclusive：是否独占 独占的意思就是这个Queue只能有我这一个Channel监听。
         * autoDelete：设置为true,没有消费者监听该队列，该队列就会自动删除。
         * arguments：扩展参数。
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // 5、创建消费者，在RabbitMQ中不需要定义QueueingProducer 只需要定义QueueingConsumer即可
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 6、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         * queue：队列的名字
         * autoAck：是否自动签收
         * callback：具体的消费者对象
         */
        String consumerTag = channel.basicConsume(QUEUE_NAME, true, queueingConsumer);
        System.out.println("******consumerTag*****" + consumerTag);

        while (true) {
            /**
             * 7、获取消息
             * nextDelivery()：如果获取不到消息就一直阻塞
             */
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            // 8、获得消息体
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Rabbitmq 生产者
 */
public class Producer {

    public static final String HOST = "192.168.110.133";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、通过Channel发送数据
        // basicPublish(String exchange,String routingKey,BasicProperties props,byte[] body)
        channel.basicPublish("", "test001", null, "hello rabbitmq".getBytes());

        // 5、关闭连接
        channel.close();
        connection.close();

        System.out.println("*****生产者消息发布成功！****");
    }
}
```

> 问题：生产者发送消息，没有指定交换机但是routingKey为"test001"就可以路由到"test001"队列，为什么？

RabbitMQ生产者投递消息如果**不指定Exchange**，那么就会默认使用**AMQP.default**这个Exchange，它的路由规则就是根据生产者指定的RoutingKey和队列的名字去对比，如果名字一致就将生产者的消息发送给该队列。

### 2.10.RabbitMQ交换机详解

#### 2.10.1.Exchange的概念

Exchange：接收消息，并根据RoutingKey转发消息所绑定的队列。

#### 2.10.2.Exchange属性

- Name：Exchange名称。
- Type：Exchange的类型。direct、topic、fanout、headers。
- Durability：是否需要持久化，true为持久化。
- Auto Delete：当最后一个绑定到Exchange上的队列删除后，自动删除该Exchange。
- Internal：当前Exchange是否用于RabbitMQ内部使用，默认为false。**(很少使用)**
- Arguments：扩展参数，用于扩展AMQP协议自制定化使用。

#### 2.10.3.Direct Exchange

> Direct Exchange基本概念

- **所有发送到Direct Exchange的消息被转发到Routing key中指定的Queue。**
- **一句话：直连的方式，生产者发送消息的Routing Key和Direct Exchange的Routing Key必须完全匹配，才会路由到绑定的Queue。**

> 消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收和Direct Exchange绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 */
public class ConsumerDirectExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.direct";
    public static final String QUEUE_NAME = "test_direct_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         * exchange：交换机名字。
         * type：交换机类型。
         * durable：是否持久化。
         * autoDelete：true表示当没有队列绑定到Exchange上时，自动删除该Exchange。
         * internal：当前Exchange是否用于RabbitMQ内部使用，一般是false。
         * arguments：其他参数。
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            //9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者 向Direct Exchange发送消息
 */
public class ProducerDirectExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_direct_exchange";
    public static final String ROUTING_KEY = "test.direct";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        // 4、发送消息
        String msg = "Hello RabbitMQ Direct Exchange Message...";
        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***ProducerDirectExchange 消息发送成功***");
    }
}
```

#### 2.10.4.Topic Exchange

> Topic Exchange基本概念

- 所有发送到Topic Exchange的消息被转发到所有关心Routing Key中指定Topic的Queue上。
- Exchange将Routing Key和某个Topic进行模糊匹配，此时队列需要绑定一个Topic。
- **一句话：Topic Exchange和Queue绑定Routing Key可以使用通配符，生产者发送消息的Routing Key只要和Topic Exchange的Routing Key匹配就能路由到Topic Exchange绑定的队列。**

> 模糊匹配可以使用通配符

- **符号 "#" 匹配一个或多个词。**
- **符号 "*" 匹配不多不少一个词。**
- **例如："log.#" 能够匹配到 "log.info.aa"。"log.*" 只能匹配到 "log.err"。**

> 消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收Topic Exchange绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 */
public class ConsumerTopicExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_topic_exchange";
    public static final String EXCHANGE_TYPE = "topic";
    public static final String ROUTING_KEY = "user.#";
    public static final String QUEUE_NAME = "test_topic_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         *  4、声明Exchange
         *  exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            // 9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者向 Topic Exchange发送消息
 */
public class ProducerTopicExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_topic_exchange";
    public static final String ROUTING_KEY = "user.name.zs";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Topic Exchange Message...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Producer Topic Exchange 消息发送成功***");
    }
}
```

#### 2.10.5.Fanout Exchange

> Fanout Exchange基本概念

- **Fanout Exchange不处理Routing Key，只需要简单的将Queue绑定到Exchange上。**
- **发送到Exchange的消息都会被转发到与该Exchange绑定的所有Queue上。**
- **Fanout Exchange转发消息是最快的。**

> 消费者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

/**
 * 消费者 接收Fanout Exchange 绑定的Queue的消息
 * 消费者先启动：声明Exchange、Queue和绑定关系
 * 注意：Fanout Exchange的Routing Key不能设置为null
 */
public class ConsumerFanoutExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_fanout_exchange";
    public static final String EXCHANGE_TYPE = "fanout";
    public static final String ROUTING_KEY = "";
    public static final String QUEUE_NAME = "test_fanout_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete,Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            // 9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("*******消费端接收到的消息为=====>" + msg);
        }
    }
}
```

> 生产者

```java
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 生产者 向Fanout Exchange发送消息 根本就不走Routing Key的！
 * Routing Key不能为null，随便写一个字符串即可。
 */
public class ProducerFanoutExchange {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_fanout_exchange";
    public static final String ROUTING_KEY = "TangShi";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        String msg = "Hello Fanout Exchange Message...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Producer Fanout Exchange 消息发送成功***");
    }
}
```

### 2.11.RabbitMQ队列、绑定、虚拟主机、消息

#### 2.11.1.Binding(绑定)

> Binding基本概念

- Exchange和Exchange、Queue之间的绑定关系。
- Binding中可以包含Routing Key或者参数。

#### 2.11.2.Queue(队列)

> Queue基本概念

- 消息队列，实际存储消息数据。
- Durability：是否持久化。Durable：是，Transient：否。
- Auto Delete：如果选true，代表当最后一个监听被移除之后，该Queue会自动删除。

#### 2.11.3.Message(消息)

> Message基本概念

- Message是服务器和应用程序之前传送的数据。
- **Message本质上就是一段数据，由Properties和Payload(Body)组成。**

> Message的属性

```java
private String contentType;	
private String contentEncoding;
private Map<String,Object> headers;	// 自定义属性
private Integer deliveryMode;	// 消息的送达模式
private Integer priority;	// 消息的优先级
private String correlationId;	// 消息的唯一ID
private String replyTo;	// 消息失败返回的队列
private String expiration;	// 消息的过期时间
private String messageId;	// 消息的ID
private Date timestamp;	// 时间戳
private String type; 
private String userId;
private String appId;
private String clusterId;
```

> 消费者接收消息Properties

```java
import com.rabbitmq.client.*;

/**
 * 消费者 测试获得消息的Properties
 */
public class MessageConsumer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_message_exchange";
    public static final String EXCHANGE_TYPE = "direct";
    public static final String ROUTING_KEY = "test.message";
    public static final String QUEUE_NAME = "test_message_queue";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、声明Exchange
         * exchangeDeclare(String exchange,String type,boolean durable,boolean autoDelete,boolean internal,Map<String, Object> arguments)
         */
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true, false, false, null);

        /**
         * 5、声明队列
         * queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
         */
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        /**
         * 6、Exchange和Queue的绑定关系
         * queueBind(String queue, String exchange, String routingKey, Map<String, Object> arguments)
         */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY, null);

        // 7、创建消费者
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);

        /**
         * 8、channel的设置
         * basicConsume(String queue, boolean autoAck, Consumer callback)
         */
        channel.basicConsume(QUEUE_NAME, true, queueingConsumer);

        while (true) {
            //9、接收消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            //接收消息体
            String msg = new String(delivery.getBody());
            //接收消息的Properties
            AMQP.BasicProperties properties = delivery.getProperties();
            System.out.println("*******消费端接收到的消息为=====>" + msg);
            System.out.println("*****自定义属性1*******" + properties.getHeaders().get("my1"));
            System.out.println("*****自定义属性2*******" + properties.getHeaders().get("my2"));
            System.out.println("*****DeliveryMode*********" + properties.getDeliveryMode());
            System.out.println("*****Expiration********" + properties.getExpiration());
            System.out.println("*****ContentEncoding********" + properties.getContentEncoding());
        }
    }
}
```

> 生产者设置消息Properties

```java
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 生产者 测试给消息添加Properties
 */
public class MessageProducer {
    public static final String HOST = "192.168.110.133";
    public static final String EXCHANGE_NAME = "test_message_exchange";
    public static final String ROUTING_KEY = "test.message";

    public static void main(String[] args) throws Exception {
        // 1、创建ConnectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // ConnectionFactory 默认端口是5672 virtualHost是"/"

        // 2、通过创建ConnectionFactory创建Connection
        Connection connection = connectionFactory.newConnection();

        // 3、通过Connection创建Channel
        Channel channel = connection.createChannel();

        /**
         * 4、发送消息
         * basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
         */
        // 设置消息的Properties
        Map<String, Object> headers = new HashMap<>();
        headers.put("my1", "111");
        headers.put("my2", "222");
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)    //消息的投递模式 2代表持久化消息 1代表非持久化消息
                .contentEncoding("UTF-8")   //字符集
                .expiration("20000")    //如果20S没有被消费者消费 就会被自动清除
                .headers(headers)  // 自定义属性
                .build();
        String msg = "Hello Message Properties Test...";
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, properties, msg.getBytes());

        // 5、关闭资源
        channel.close();
        connection.close();

        System.out.println("***Set Message Properties 消息发送成功***");
    }
}
```

#### 2.11.4.Virtual Host(虚拟主机)

> Virtual Host基本概念

- 虚拟地址，用于进行逻辑隔离，最上层的消息路由。
- 一个Virtual Host里面可以有若干个Exchange和Queue。
- 同一个Virtual Host里面不能有相同名称的Exchange和Queue。



## 3.RabbitMQ的高级特性

### 3.1.如何保障消息100%的投递成功？

#### 3.1.1.什么是消息的可靠性传递？

- 保障消息的成功发出。
- 保障MQ节点的成功接收。
- 发送端收到MQ节点（Broker）的应答。
- 完善的消息补偿机制。

#### 3.1.2.生产端—可靠性投递解决方案

##### 3.1.2.1.方案一：消息落库，対消息状态进行打标。

> 消息落库图示

![消息落库](D:\git_workspace\LearningNote\RabbitMQ\image\3.1.2-1-消息落库.jpg)



> 思考：如果使用消息落库的可靠性投递，在高并发场景是否合适？



##### 3.1.2.2.方案二：消息的延迟投递，做二次确认，回调检查。

> 消息回调检查图示

![消息延迟投递回调检查](D:\git_workspace\LearningNote\RabbitMQ\image\3.1.2-2-消息的回调检查.jpg)



### 3.2.消息幂等性概念

> 幂等性是什么？

- **一句话：用户对于同一操作发起的一次或者多次请求，最后的结果都是相同的，这就是幂等性。在MQ中就是保障消息不要被重复消费。**

### 3.3.在海量订单产生的业务高峰期，如何避免消息的重复消费？

#### 3.3.1.消费端的幂等性

- **消费端实现幂等性，就意味着，我们的消息永远不会消费多次，即使我们收到了多条一样的消息。**

#### 3.3.2.业界主流的幂等性操作

##### 3.3.2.1.方案一：唯一ID + 指纹码机制，利用数据库主键去重。

- select count(1) from t_order where id = 唯一ID+指纹码
- 好处：实现简单
- 坏处：高并发下数据库写入性能瓶颈。
- 解决方案：跟进ID进行分库分表进行算法路由。

##### 3.3.2.2.方案二：利用Redis的原子性去实现。

> 使用Redis进行幂等，需要考虑的问题

1. 我们是否要进行数据落库，如果落库的话，关键解决的问题是数据库和缓存如何做到原子性？
2. 如果不进行落库，那么都存储到Redis缓存中，如何设置定时同步策略。

### 3.4.两种消息投递方式：Confirm确认消息、Return返回消息



### 3.5.自定义消费者



### 3.6.消息的ACK与重回队列



### 3.7.消息的限流



### 3.8.TTL消息



### 3.9.死信队列